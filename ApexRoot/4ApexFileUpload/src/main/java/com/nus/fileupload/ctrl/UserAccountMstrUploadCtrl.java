package com.nus.fileupload.ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nus.api.struct.ApiResponse;
import com.nus.base.ctrl.ApexBaseCtrl;
import com.nus.exception.DataIntegrityViolationException;
import com.nus.exception.ExcelFileReadingException;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.fileupload.service.UserAccountMstrUploadService;
import com.nus.sec.entities.ApexUser;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 06-Feb-2025<br>
 * @Time: 6:23:03 am<br>
 * @Objective: <br>
 */
@Validated
@RestController
@RequestMapping("/file/v1")
public class UserAccountMstrUploadCtrl extends ApexBaseCtrl{
	
	@Autowired
	UserAccountMstrUploadService userAccountMstrUploadService;
	
	@RequestMapping("/userAccounts")
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<ApiResponse> upload( @Validated @RequestParam("file") MultipartFile file) {
      
		FileUploadPayload fileUploadPayload = new FileUploadPayload(file);
	    List<ApexUser> userAccountMstrUploadList = new ArrayList<ApexUser>();
		
			try {
				userAccountMstrUploadList = userAccountMstrUploadService.readExcel(fileUploadPayload);
			} catch (IOException e) {
				throw new ExcelFileReadingException("Problem is in reading excel file.");
			}
			if(userAccountMstrUploadList!=null) {
				userAccountMstrUploadList = userAccountMstrUploadService.saveExcel(userAccountMstrUploadList);
				apiReq=makeApiMetaData();
				apiReq.setPayLoad(fileUploadPayload);
				//Return response in a pre-defined format	       	
				apiResponse=makeSuccessResponse(userAccountMstrUploadList,apiReq);		
				return ResponseEntity.ok().body(apiResponse);
			}else {
				throw new DataIntegrityViolationException("No UserAccount is added due to data intigrity violation.");
			}			
    }
	
}
