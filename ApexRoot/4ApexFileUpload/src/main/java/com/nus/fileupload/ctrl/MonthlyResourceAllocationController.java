package com.nus.fileupload.ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import com.nus.fileupload.entities.ProjectMonthlyResourceAllocation;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.fileupload.service.MonthlyResourceAllocationService;
import com.nus.sec.service.ApexUserService;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:29:16 pm<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/file/v1")
public class MonthlyResourceAllocationController extends ApexBaseCtrl {
	@Autowired
	ApexUserService apexUserService;

	@Autowired
	MonthlyResourceAllocationService monthlyResourceAllocationService;

	@RequestMapping("/resAlloc")
	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<ApiResponse> upload(@Validated @RequestParam("file") MultipartFile file,
			@RequestParam("fileUploadDate") String fileUploadDate)
			throws DataIntegrityViolationException, IOException, Exception {
		
		FileUploadPayload fileUploadPayload = new FileUploadPayload(dateFormatter.parse(fileUploadDate), file, file.getName(), 3);
				
		
		List<ProjectMonthlyResourceAllocation> projectMonthlyResourceAllocationList = new ArrayList<ProjectMonthlyResourceAllocation>();
		try {
			projectMonthlyResourceAllocationList = monthlyResourceAllocationService.readExcel(fileUploadPayload);
		} catch (IOException e) {
			throw new ExcelFileReadingException("Problem in reading excel file.");
		}
		if (projectMonthlyResourceAllocationList != null) {
			projectMonthlyResourceAllocationList = monthlyResourceAllocationService
					.saveExcel(projectMonthlyResourceAllocationList, fileUploadPayload);
			apiReq = makeApiMetaData();
			apiReq.setPayLoad(fileUploadPayload);
			// Return response in a pre-defined format
			apiResponse = makeSuccessResponse(projectMonthlyResourceAllocationList, apiReq);
			return ResponseEntity.ok().body(apiResponse);
		} else {
			throw new DataIntegrityViolationException(
					"No ProjectMonthlyResourceAllocation is added due to data intigrity issues.");
		}
	}

}// End of MonthlyResourceAllocationController
