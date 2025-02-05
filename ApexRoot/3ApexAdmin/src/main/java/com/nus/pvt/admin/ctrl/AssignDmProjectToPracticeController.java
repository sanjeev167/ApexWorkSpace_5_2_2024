package com.nus.pvt.admin.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nus.base.ctrl.ApexBaseCtrl;
import com.nus.pvt.admin.service.AssignDmProjectToPracticeService;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:21:35 pm<br>
 * @Objective: <br>
 */
@RestController
public class AssignDmProjectToPracticeController extends ApexBaseCtrl {

	@Autowired
	AssignDmProjectToPracticeService assignDmProjectToPracticeService;
}
