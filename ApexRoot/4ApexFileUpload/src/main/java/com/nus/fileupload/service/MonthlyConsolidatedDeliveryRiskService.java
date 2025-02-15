package com.nus.fileupload.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.entities.ConsolidatedDeliveryRisk;
import com.nus.fileupload.model.FileUploadPayload;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:50:52 pm<br>
 * @Objective: <br>
 */
public interface MonthlyConsolidatedDeliveryRiskService {

    public List<ConsolidatedDeliveryRisk> readExcel(FileUploadPayload fileUploadPayload) throws IOException;	
	
	public List<ConsolidatedDeliveryRisk> saveExcel(List<ConsolidatedDeliveryRisk> consolidatedDeliveryRiskListReadFromExcel,FileUploadPayload fileUploadPayload)throws DataIntegrityViolationException, IOException, Exception;
		
	public List<ConsolidatedDeliveryRisk> getMonthlyConsolidatedDeliveryRiskBetweenMonths(int projectCodeId,
			Date fFileUploadDate,Date tFileUploadDate);
	
	public void generateExcel(HttpServletResponse response, String fileName);
	
}//End of MonthlyConsolidatedDeliveryRiskService
