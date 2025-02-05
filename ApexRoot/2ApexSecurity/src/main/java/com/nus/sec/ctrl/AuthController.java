package com.nus.sec.ctrl;

import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nus.base.ctrl.ApexBaseCtrl;
import com.nus.sec.entities.ApexRefreshToken;
import com.nus.sec.jwt.JwtUtils;
import com.nus.sec.jwt.RefreshTokenService;
import com.nus.sec.model.AuthRequest;
import com.nus.sec.model.JwtResponse;
import com.nus.sec.model.RefreshTokenRequest;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 09-Jan-2025<br>
 * @Time: 7:27:39 am<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/auth/v1")
public class AuthController extends ApexBaseCtrl {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	RefreshTokenService refreshTokenService;

	@PostMapping(value = "/login",produces="application/json",consumes = "application/json")
	public ResponseEntity<Object> AuthenticateAndGetToken(@Valid @RequestBody AuthRequest authRequest){	 
		
		try {
		Authentication authentication = authenticationManager.authenticate(
				                   new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		
		if(authentication.isAuthenticated()){			
			JwtResponse jwtResponse = new JwtResponse();		     	        
	        jwtResponse.setAccessToken(jwtUtils.GenerateToken(authentication.getName()));
	        jwtResponse.setRefreshToken((refreshTokenService.createRefreshToken(authentication.getName()).getRefreshToken()));	 
	        //Return response in a pre-defined format	  
	        apiReq = makeApiMetaDataWhileLogin(authentication.getName());
			apiReq.setPayLoad(authRequest);
			apiResponse=makeSuccessResponse(jwtResponse,apiReq);
			return ResponseEntity.ok().body(apiResponse);
	    } else {
	        throw new UsernameNotFoundException("invalid user request..!!");
	    }
		}catch(BadCredentialsException ex) {throw new BadCredentialsException("Invalid-Credential !");}
	}//End of AuthenticateAndGetToken
		
	@PostMapping(value = "/refreshToken",produces="application/json",consumes = "application/json")
	public ResponseEntity<Object> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
		
		Optional<ApexRefreshToken> apiRefreshTokenOptional = refreshTokenService.findByRefreshToken(refreshTokenRequest.getRefreshToken());
		JwtResponse jwtResponse;
		ApexRefreshToken apexRefreshToken;
		
		if(apiRefreshTokenOptional!=null) {
			apexRefreshToken = apiRefreshTokenOptional.get();			
			refreshTokenService.verifyExpiration(apexRefreshToken);			
			jwtResponse=new JwtResponse();
			jwtResponse.setAccessToken(jwtUtils.GenerateToken(apexRefreshToken.getApexUser().getEmail()));			 
			jwtResponse.setRefreshToken(refreshTokenRequest.getRefreshToken());	
			//Return response in a pre-defined format			
			apiReq = makeApiMetaDataWhileLogin(apexRefreshToken.getApexUser().getEmail());
			apiReq.setPayLoad(refreshTokenRequest);
			apiResponse=makeSuccessResponse(jwtResponse,apiReq);				        
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new RuntimeException("Refresh Token is not in DB..!!");
		}
	}//End of refreshToken	

}// End of AuthController

