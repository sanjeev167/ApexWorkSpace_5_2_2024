package com.nus.fileupload.service;

import java.io.IOException;
import java.util.List;

import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.entities.ProjectMonthlyRrrr;
import com.nus.fileupload.model.FileUploadPayload;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:42:19 pm<br>
 * @Objective: <br>
 */
public interface MonthlyRRRService {
	
public List<ProjectMonthlyRrrr> readExcel(FileUploadPayload fileUploadPayload) throws IOException;	
	
	public List<ProjectMonthlyRrrr> saveExcel(List<ProjectMonthlyRrrr> projectMonthlyRrrrList,FileUploadPayload fileUploadPayload)throws DataIntegrityViolationException, IOException, Exception;
		
	public void generateExcel(HttpServletResponse response, String fileName);

}//End of MonthlyRRRService
