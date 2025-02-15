package com.nus.base.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.nus.sec.repo.ApexUserRepository;
import com.nus.sec.service.CustomUserDetails;
import com.nus.util.Utility;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 03-Feb-2025<br>
 * @Time: 1:01:00 pm<br>
 * @Objective: <br>
 */
public abstract class UserLoginBaseService extends Utility{
	@Autowired
	ApexUserRepository apexUserRepository;
	protected Date currentDate = new Date();
	protected String activeC = "Y";
	protected static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
	

	protected Integer getCurrentLoginUserId() {
		String loginId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getEmail();
		return apexUserRepository.findByEmail(loginId).get().getId();
	}

}
