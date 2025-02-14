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
import com.nus.fileupload.entities.ProjectMonthlyResourceUtilization;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.fileupload.service.MonthlyResourceUtilizationService;
import com.nus.sec.service.ApexUserService;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:27:30 pm<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/file/v1")
public class MonthlyResourceUtilizationController extends ApexBaseCtrl {

	@Autowired
	ApexUserService apexUserService;

	@Autowired
	MonthlyResourceUtilizationService monthlyResourceUtilizationService;

	@RequestMapping("/resUtil")
	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<ApiResponse> upload(@Validated @RequestParam("file") MultipartFile file,
			@RequestParam("fileUploadDate") String fileUploadDate)
			throws DataIntegrityViolationException, IOException, Exception {
		
		FileUploadPayload fileUploadPayload = new FileUploadPayload(dateFormatter.parse(fileUploadDate), file, file.getName(), 4);
				
		List<ProjectMonthlyResourceUtilization> projectMonthlyResourceUtilizationList = new ArrayList<ProjectMonthlyResourceUtilization>();
		try {
			projectMonthlyResourceUtilizationList = monthlyResourceUtilizationService.readExcel(fileUploadPayload);
		} catch (IOException e) {
			throw new ExcelFileReadingException("Problem in reading excel file.");
		}
		if (projectMonthlyResourceUtilizationList != null) {
			projectMonthlyResourceUtilizationList = monthlyResourceUtilizationService
					.saveExcel(projectMonthlyResourceUtilizationList, fileUploadPayload);
			apiReq = makeApiMetaData();
			apiReq.setPayLoad(fileUploadPayload);
			// Return response in a pre-defined format
			apiResponse = makeSuccessResponse(projectMonthlyResourceUtilizationList, apiReq);
			return ResponseEntity.ok().body(apiResponse);
		} else {
			throw new DataIntegrityViolationException(
					"No ProjectMonthlyResourceUtilization is added due to data intigrity issues.");
		}
	}
}// End of MonthlyResourceUtilizationController
