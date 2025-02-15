package com.nus.sec.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.sec.entities.ApexUser;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 9:04:38 pm<br>
 * @Objective: <br>
 */
@Repository
public interface ApexUserRepository extends JpaRepository<ApexUser, Integer>{

	Optional<ApexUser> findByEmail(String email);

	List<ApexUser> findAllByActiveC(String string);	
}//End of ApexUserRepository

