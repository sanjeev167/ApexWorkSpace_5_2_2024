package com.nus.sec.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nus.exception.MalformedJwtException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 9:07:41 pm<br>
 * @Objective: <br>
 */
@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${apex.api.jwtSecret}")
	private String jwtSecret;

	@Value("${apex.api.jwtExpirationInMin}")
	private int jwtExpirationInMin;

//Extracts the username from the token
	public String extractUsername(String token){
		return extractClaim(token, Claims::getSubject);
	}

//Extracts the expiration date from the token
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

//Extracts specific claims from the token using a claims resolver
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

//Extracts all claims from the token
	private Claims extractAllClaims(String token) {
		try {
			return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
		} 		
		catch (Exception ex) {	
			logger.error("Failed to extract claims from token: {}", ex.getMessage());
			if(ex instanceof MalformedJwtException)			
			//return null; // Consider throwing a custom exception instead of returning null
			      throw new MalformedJwtException(ex.getMessage());			
			else {throw ex;}
		}		
	}

//Checks if the token is expired
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

// Validates the token against the user details
	public Boolean validateToken(String token, UserDetails userDetails){
		final String username = extractUsername(token);
		boolean isValid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
		logger.info("Token validation for user {}: {}", username, isValid);
		return isValid;
	}

// Generates a token for the given user details
	public String GenerateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}

// Create a token for the given user details
	private String createToken(Map<String, Object> claims, String username) {
		// System.out.println("username while creating an access-token = "+username);
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMin * 60000))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

// Retrieves the signing key
	private SecretKey getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}// End of JwtUtils
