package com.nus.pvt.admin.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nus.base.ctrl.ApexBaseCtrl;
import com.nus.pvt.admin.repo.MenuManagerRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:17:44 pm<br>
 * @Objective: <br>
 */
@RestController
public class MenuManagerController extends ApexBaseCtrl {

	@Autowired
	MenuManagerRepo menuManagerRepo;
}
