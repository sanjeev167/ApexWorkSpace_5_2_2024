package com.nus.pvt.reports.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nus.api.struct.ApiResponse;
import com.nus.base.ctrl.ApexBaseCtrl;
import com.nus.exception.ResourceNotFoundException;
import com.nus.pvt.reports.model.PmProjectReportModel;
import com.nus.pvt.reports.service.PmAssignedProjectReportService;
import com.nus.util.Utility;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 08-Feb-2025<br>
 * @Time: 11:44:41 am<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/pvt/reports/v1")
public class PmProjectReportCtrl extends ApexBaseCtrl{
	
	@Autowired
	PmAssignedProjectReportService pMAssignedProjectReportService;
	
	
	@GetMapping(value = "/pm",produces="application/json")	
	@PreAuthorize("hasAnyAuthority('ROLE_PM')")
    public ResponseEntity<ApiResponse> getPmReports(@Validated @RequestParam("fFileUploadDate") String fFileUploadDate,
    		@RequestParam("tFileUploadDate") String tFileUploadDate) throws Exception {  
		
		PmProjectReportModel pmProjectReportModel;		
			try {
				pmProjectReportModel = pMAssignedProjectReportService.getPmReports(dateFormatter.parse(fFileUploadDate),dateFormatter.parse(tFileUploadDate)); 
			if(pmProjectReportModel!=null) {				
				apiReq=makeApiMetaData();
				
				apiReq.setPayLoad(Utility.getDateInMMYYYY(dateFormatter.parse(fFileUploadDate))+" to "
				+Utility.getDateInMMYYYY(dateFormatter.parse(tFileUploadDate)));
				//Return response in a pre-defined format	       	
				apiResponse=makeSuccessResponse(pmProjectReportModel,apiReq);		
				return ResponseEntity.ok().body(apiResponse);
			}else {
				throw new ResourceNotFoundException("No project is assigned to him.");
			}	
			} catch (Exception ex) {
				throw ex;
			}
    }

}
