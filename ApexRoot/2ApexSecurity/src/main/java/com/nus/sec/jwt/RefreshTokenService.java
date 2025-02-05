package com.nus.sec.jwt;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nus.exception.TokenRefreshException;
import com.nus.sec.entities.ApexRefreshToken;
import com.nus.sec.repo.ApexUserRepository;
import com.nus.sec.repo.RefreshTokenRepository;


/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 9:09:40 pm<br>
 * @Objective: <br>
 */
@Service
public class RefreshTokenService {

	@Value("${apex.api.jwtRefreshExpirationInMin}")
	private Long jwtRefreshExpirationInMin;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private ApexUserRepository apexUserRepository;

	

	public ApexRefreshToken createRefreshToken(String username) {
       // System.out.println("username while creating refresh token = "+username);
		ApexRefreshToken refreshToken = new ApexRefreshToken();

		refreshToken.setUserId(apexUserRepository.findByEmail(username).get().getId());		
		Date javaDate = Date.from(Instant.now().plusMillis(jwtRefreshExpirationInMin*60000));

		refreshToken.setExpiryDate(javaDate);

		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setCreatedOn(new Date());
		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}
	
	public Optional<ApexRefreshToken> findByRefreshToken(String token) {
		return refreshTokenRepository.findByRefreshToken(token);
	}

	public ApexRefreshToken verifyExpiration(ApexRefreshToken token) {
		Date currentDateTime = new Date();      
		if (token.getExpiryDate().compareTo(currentDateTime) < 0) {
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getRefreshToken(), "Refresh token is expired. Please make a new signin request");
		}

		return token;
	}	
}// End of RefreshTokenService
