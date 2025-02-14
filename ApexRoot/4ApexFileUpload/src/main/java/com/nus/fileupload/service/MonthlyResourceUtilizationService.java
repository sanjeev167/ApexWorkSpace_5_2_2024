package com.nus.fileupload.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.entities.ProjectMonthlyResourceUtilization;
import com.nus.fileupload.model.FileUploadPayload;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:44:31 pm<br>
 * @Objective: <br>
 */
public interface MonthlyResourceUtilizationService {
	
    public List<ProjectMonthlyResourceUtilization> readExcel(FileUploadPayload fileUploadPayload) throws IOException;
	
	public List<ProjectMonthlyResourceUtilization> saveExcel(List<ProjectMonthlyResourceUtilization> projectMonthlyResourceUtilizationList,FileUploadPayload fileUploadPayload)throws DataIntegrityViolationException, IOException, Exception;
	
	public List<ProjectMonthlyResourceUtilization> getMonthlyResourceUtilizationBetweenMonths(int projectCodeId,
			Date fFileUploadDate,Date tFileUploadDate);
	
	public void generateExcel(HttpServletResponse response, String fileName);

	public ProjectMonthlyResourceUtilization getAMonthSpecificResourceUtilization(int projectCodeId,Date twoMonthBackFileUploadDate);

}//End of MonthlyResourceUtilizationService
