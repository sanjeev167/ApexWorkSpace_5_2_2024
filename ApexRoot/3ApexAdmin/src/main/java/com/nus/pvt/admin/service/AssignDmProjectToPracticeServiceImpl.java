package com.nus.pvt.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.pvt.admin.repo.AssignDmProjectToPracticeRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:44:54 pm<br>
 * @Objective: <br>
 */
@Service
public class AssignDmProjectToPracticeServiceImpl implements AssignDmProjectToPracticeService {

	@Autowired
	AssignDmProjectToPracticeRepo assignDmProjectToPracticeRepo;
}
