package com.nus.pvt.master.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nus.base.ctrl.ApexBaseCtrl;
import com.nus.pvt.master.service.TreeMenuTypeMstrService;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:12:34 pm<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/mstr/v1")
public class TreeMenuTypeMstrCpntroller extends ApexBaseCtrl {

	@Autowired
	TreeMenuTypeMstrService treeMenuTypeMstrService;
}
