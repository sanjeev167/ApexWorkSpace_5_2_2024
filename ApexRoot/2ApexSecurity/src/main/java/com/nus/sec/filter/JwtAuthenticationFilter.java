package com.nus.sec.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.jboss.logging.MDC;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.nus.sec.jwt.JwtUtils;
import com.nus.sec.service.CustomUserDetails;
import com.nus.sec.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:5:06:08 pm<br>
 * @Objective: <br>
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final HandlerExceptionResolver handlerExceptionResolver;
	private final JwtUtils jwtUtils;
	private final CustomUserDetailsService customUserDetailsService;

	// Constructor for dependency injection
	public JwtAuthenticationFilter(JwtUtils jwtUtils, CustomUserDetailsService customUserDetailsService,
			HandlerExceptionResolver handlerExceptionResolver) {
		this.jwtUtils = jwtUtils;
		this.customUserDetailsService = customUserDetailsService;
		this.handlerExceptionResolver = handlerExceptionResolver;
	}

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		// Extract the Authorization header from the request
		final String authHeader = request.getHeader("Authorization");

		// Check if the header is present and starts with "Bearer "
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			// If not, continue the filter chain without authentication
			filterChain.doFilter(request, response);
			return;
		}

		try {
			// Extract the JWT from the header
			final String jwt = authHeader.substring(7);
			// Get the username (email) from the JWT
			final String userEmail = jwtUtils.extractUsername(jwt);

			// Get the current authentication from the security context
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			// If the userEmail is not null and there is no existing authentication
			if (userEmail != null && authentication == null) {
				// Load user details using the userDetailsService
				CustomUserDetails customUserDetails = (CustomUserDetails) this.customUserDetailsService.loadUserByUsername(userEmail);

				// Validate the JWT token
				if (jwtUtils.validateToken(jwt, customUserDetails)) {
					// Create an authentication token
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(customUserDetails,null, customUserDetails.getAuthorities());

					// Set the request details for the authentication token
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					// Set the authentication in the security context
					SecurityContextHolder.getContext().setAuthentication(authToken);
					doContextLoggingConfiguration(customUserDetails);
				}
			}

			// Continue the filter chain
			filterChain.doFilter(request, response);
		} catch (Exception exception) {
			// Handle any exceptions by resolving them with the exception resolver
			handlerExceptionResolver.resolveException(request, response, null, exception);
		}
	}
	
	/**
	 * @author sanjeevkumar 24-Jan-2025 1:48:26 pm
	 * @param customUserDetails 
	 * Objective:
	 */

	private void doContextLoggingConfiguration(CustomUserDetails customUserDetails) {
		String dateFormatUsed = "dd-MM-yyyy";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormatUsed);
		LocalDateTime now = LocalDateTime.now();
		String apiCallReceivedOn = dtf.format(now);
		MDC.clear();
		// System.out.println("Sanjeev UserInfoDetails while MDC setting => "+userInfoDetails.getUsername());
		MDC.put("logContextFolder",	apiCallReceivedOn + "/" + customUserDetails.getUserContext() + "/" + customUserDetails.getUsername());
	}
}
