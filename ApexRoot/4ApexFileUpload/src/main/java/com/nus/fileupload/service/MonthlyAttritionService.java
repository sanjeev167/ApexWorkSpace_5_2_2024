package com.nus.fileupload.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.entities.ProjectMonthlyResourceAttrition;
import com.nus.fileupload.model.FileUploadPayload;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:52:32 pm<br>
 * @Objective: <br>
 */
public interface MonthlyAttritionService {	
	
    public List<ProjectMonthlyResourceAttrition> readExcel(FileUploadPayload fileUploadPayload) throws IOException;
	
	public List<ProjectMonthlyResourceAttrition> saveExcel(List<ProjectMonthlyResourceAttrition> monthlyResourceAttritionListReadFromExcel,FileUploadPayload fileUploadPayload)throws DataIntegrityViolationException, IOException, Exception;
	
	public List<ProjectMonthlyResourceAttrition> getMonthlyResourceAttritionDataBetweenMonths(int projectCodeId,
			Date fFileUploadDate,Date tFileUploadDate);
	
	public void generateExcel(HttpServletResponse response, String fileName)throws Exception;

	public List<ProjectMonthlyResourceAttrition> getAMonthSpecificAttrition(Integer projectCodeId, Date currentMonthYearDate);

}//End of MonthlyAttritionService
