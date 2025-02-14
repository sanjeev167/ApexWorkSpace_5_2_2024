package com.nus.pub.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nus.base.ctrl.ApexBaseCtrl;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 09-Jan-2025<br>
 * @Time: 6:56:35 am<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/pub/v1")
public class PublicApiController extends ApexBaseCtrl {

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome! this endpoint is not secured";
	}

}// End of PublicApiController
