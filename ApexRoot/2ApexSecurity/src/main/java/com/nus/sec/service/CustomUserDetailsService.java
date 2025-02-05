package com.nus.sec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nus.sec.entities.ApexUser;
import com.nus.sec.repo.ApexUserRepository;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 4:13:49 pm<br>
 * @Objective: <br>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private ApexUserRepository apexUserRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<ApexUser> apexUser = apexUserRepository.findByEmail(email);

		// Converting apiUser to UserDetails. UserInfoDetails is project specific
		// implementation
		return apexUser.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException(email + " not found in database"));
	}

}// End of CustomUserDetailsService
