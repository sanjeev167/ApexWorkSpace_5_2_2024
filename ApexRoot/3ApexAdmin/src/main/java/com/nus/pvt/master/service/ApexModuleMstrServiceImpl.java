package com.nus.pvt.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.pvt.master.repo.ApexModuleMstrRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 5:42:51 pm<br>
 * @Objective: <br>
 */
@Service
public class ApexModuleMstrServiceImpl implements ApexModuleMstrService {

	@Autowired
	ApexModuleMstrRepo apexModuleMstrRepo;
}
