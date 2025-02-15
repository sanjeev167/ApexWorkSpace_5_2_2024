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
import com.nus.fileupload.service.ProjectCodeMstrUploadService;
import com.nus.pvt.master.entities.ProjectCodeMstr;
import com.nus.sec.service.ApexUserService;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 27-Jan-2025<br>
 * @Time: 10:08:36 pm<br>
 * @Objective: <br>
 */
@Validated
@RestController
@RequestMapping("/file/v1")
public class ProjectCodeMstrUploadCtrl extends ApexBaseCtrl{

	@Autowired
	ApexUserService apexUserService;
	
	@Autowired
	ProjectCodeMstrUploadService projectCodeMstrUploadService;	

	@RequestMapping("/projectCodes")
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<ApiResponse> upload( @Validated @RequestParam("file") MultipartFile file, @RequestParam("month") Integer month,@RequestParam("year") Integer year) {
	   	    		    
		FileUploadPayload fileUploadPayload = new FileUploadPayload(file);
		
	    List<ProjectCodeMstr> projectCodeMstrList = new ArrayList<ProjectCodeMstr>();		
			try {
				projectCodeMstrList = projectCodeMstrUploadService.readExcel(fileUploadPayload);				
			} catch (IOException e) {				
				throw new ExcelFileReadingException("Problem in reading excel file.");
			}
			if(projectCodeMstrList!=null) {
				try {
				projectCodeMstrList = projectCodeMstrUploadService.saveExcel(projectCodeMstrList);				
				apiReq=makeApiMetaData();
				apiReq.setPayLoad(fileUploadPayload);
				//Return response in a pre-defined format	       	
				apiResponse=makeSuccessResponse(projectCodeMstrList,apiReq);
				}catch(DataIntegrityViolationException ex) {					
					throw new DataIntegrityViolationException("Data integrity is violated.");
					}
				return ResponseEntity.ok().body(apiResponse);
			}else {
                throw new DataIntegrityViolationException("No ProjectCode is added due to data intigrity issues.");
			}			
    }

}
