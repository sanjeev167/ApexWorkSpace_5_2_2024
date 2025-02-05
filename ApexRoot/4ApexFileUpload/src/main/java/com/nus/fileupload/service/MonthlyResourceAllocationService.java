package com.nus.fileupload.service;

import java.io.IOException;
import java.util.List;

import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.entities.ProjectMonthlyResourceAllocation;
import com.nus.fileupload.model.FileUploadPayload;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:46:33 pm<br>
 * @Objective: <br>
 */
public interface MonthlyResourceAllocationService {
	
    public List<ProjectMonthlyResourceAllocation> readExcel(FileUploadPayload fileUploadPayload) throws IOException;
	
	public List<ProjectMonthlyResourceAllocation> saveExcel(List<ProjectMonthlyResourceAllocation> monthlyResourceAllocationListReadFromExcel,FileUploadPayload fileUploadPayload)throws DataIntegrityViolationException, IOException, Exception;
	
	public void generateExcel(HttpServletResponse response, String fileName);

}//End of MonthlyResourceAllocationService
