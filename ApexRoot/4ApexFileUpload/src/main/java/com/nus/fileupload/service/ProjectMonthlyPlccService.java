package com.nus.fileupload.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.entities.ProjectMonthlyPlcc;
import com.nus.fileupload.model.FileUploadPayload;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 25-Jan-2025<br>
 * @Time: 7:05:22 pm<br>
 * @Objective: <br>
 */
public interface ProjectMonthlyPlccService {

    public List<ProjectMonthlyPlcc> readExcel(FileUploadPayload fileUploadPayload) throws IOException;
	
	public List<ProjectMonthlyPlcc> saveExcel(List<ProjectMonthlyPlcc> projectMonthlyPlccList,FileUploadPayload fileUploadPayload)throws DataIntegrityViolationException, IOException, Exception;
		
	public List<ProjectMonthlyPlcc> getMonthlyPlccBetweenMonths(int projectCodeId,Date fFileUploadDate,Date tFileUploadDate);
	
	public void generateExcel(HttpServletResponse response, String fileName);
}
