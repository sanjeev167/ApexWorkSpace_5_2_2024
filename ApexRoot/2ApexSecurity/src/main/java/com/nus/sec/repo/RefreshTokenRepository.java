package com.nus.sec.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.sec.entities.ApexRefreshToken;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 9:05:53 pm<br>
 * @Objective: <br>
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<ApexRefreshToken, Long>{
	
	Optional<ApexRefreshToken> findByRefreshToken(String token);

}//End of RefreshTokenRepository
