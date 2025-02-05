package com.nus.pvt.admin.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nus.base.ctrl.ApexBaseCtrl;
import com.nus.pvt.admin.service.AssignPmProjectToDmService;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:20:52 pm<br>
 * @Objective: <br>
 */
@RestController
public class AssignPmProjectToDmController extends ApexBaseCtrl {

	@Autowired
	AssignPmProjectToDmService assignPmProjectToDmService;
}
