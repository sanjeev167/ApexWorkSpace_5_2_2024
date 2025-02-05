package com.nus.pvt.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.pvt.admin.repo.MenuManagerRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:36:00 pm<br>
 * @Objective: <br>
 */
@Service
public class MenuManagerServiceImpl implements MenuManagerService {

	@Autowired
	MenuManagerRepo menuManagerRepo;
}
