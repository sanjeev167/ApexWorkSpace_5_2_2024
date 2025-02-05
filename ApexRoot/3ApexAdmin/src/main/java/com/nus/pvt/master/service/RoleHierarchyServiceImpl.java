package com.nus.pvt.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.pvt.master.repo.RoleHierarchyRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:30:07 pm<br>
 * @Objective: <br>
 */
@Service
public class RoleHierarchyServiceImpl implements RoleHierarchyService {

	@Autowired
	RoleHierarchyRepo roleHierarchyRepo;
}
