package com.nus.fileupload.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.entities.CustomerSatisfaction;
import com.nus.fileupload.model.FileUploadPayload;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:48:55 pm<br>
 * @Objective: <br>
 */
public interface MonthlyCustomerSatisfactionService {
	
    public List<CustomerSatisfaction> readExcel(FileUploadPayload fileUploadPayload) throws IOException;	
	
	public List<CustomerSatisfaction> saveExcel(List<CustomerSatisfaction> customerSatisfactionListReadFromExcel,FileUploadPayload fileUploadPayload)throws DataIntegrityViolationException, IOException, Exception;
	
	public List<CustomerSatisfaction> getMonthlyCustomerSatisfactionBetweenMonths(int projectCodeId,Date fFileUploadDate,Date tFileUploadDate);
	
	public void generateExcel(HttpServletResponse response, String fileName);

}//End of MonthlyCustomerSatisfactionService
