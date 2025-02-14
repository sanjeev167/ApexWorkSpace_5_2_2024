package com.nus.fileupload.ctrl;

import java.io.IOException;
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
import com.nus.fileupload.service.UploadAssignedProjectToPmService;
import com.nus.pvt.admin.entities.AssignProjectToPm;
import com.nus.pvt.admin.model.AssignProjectToPmModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 08-Feb-2025<br>
 * @Time: 1:17:33 am<br>
 * @Objective: <br>
 */
@Validated
@RestController
@RequestMapping("/file/v1")
public class UploadAssignedProjectToPmCtrl extends ApexBaseCtrl{
	@Autowired
	UploadAssignedProjectToPmService uploadAssignedProjectToPmService;
	
	@RequestMapping("/assignProjectsToPm")
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<ApiResponse> upload( @Validated @RequestParam("file") MultipartFile file,@RequestParam("assignedOn") String assignedOn ) throws DataIntegrityViolationException, IOException, Exception {
      	
	    List<AssignProjectToPm> assignProjectToPmList = null;
	    List<AssignProjectToPmModel> assignProjectToPmModelList = null;		
			try {
				assignProjectToPmList = uploadAssignedProjectToPmService.readExcel(file,assignedOn);
			} catch (IOException e) {
				throw new ExcelFileReadingException("Problem is in reading excel file.");
			}
			if(assignProjectToPmList!=null) {
				assignProjectToPmModelList = uploadAssignedProjectToPmService.saveExcel(assignProjectToPmList);
				apiReq=makeApiMetaData();
				apiReq.setPayLoad(assignedOn);
				//Return response in a pre-defined format	       	
				apiResponse=makeSuccessResponse(assignProjectToPmModelList,apiReq);		
				return ResponseEntity.ok().body(apiResponse);
			}else {
				throw new DataIntegrityViolationException("No PM-Project-assignment is added due to data intigrity violation.");
			}			
    }
}
