package com.nus.pvt.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.pvt.admin.repo.AssignPmProjectToDmRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:43:54 pm<br>
 * @Objective: <br>
 */
@Service
public class AssignPmProjectToDmServiceImpl implements AssignPmProjectToDmService {

	@Autowired
	AssignPmProjectToDmRepo assignPmProjectToDmRepo;
}
