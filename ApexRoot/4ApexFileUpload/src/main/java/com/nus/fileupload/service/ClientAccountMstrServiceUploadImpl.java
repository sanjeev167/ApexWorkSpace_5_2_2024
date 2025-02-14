package com.nus.fileupload.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.fileupload.repo.ClientAccountMstrUploadRepo;
import com.nus.pvt.master.entities.ClientAccountMstr;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 28-Jan-2025<br>
 * @Time: 11:06:30 am<br>
 * @Objective: <br>
 */
@Service
public class ClientAccountMstrServiceUploadImpl extends UserLoginBaseService implements ClientAccountMstrUploadService{

	private final Logger logger = LoggerFactory.getLogger(ClientAccountMstrServiceUploadImpl.class);
	
	@Autowired
	ClientAccountMstrUploadRepo clientAccountMstrUploadRepo;
	
	@Override
	public List<ClientAccountMstr> readExcel(FileUploadPayload fileUploadPayload) throws IOException {
		int clientAccountCellNo = 0;// String => Checked
		

		List<ClientAccountMstr> clientAccountMstrList = new ArrayList<ClientAccountMstr>();
		Workbook workbook = null;
		String fileName = fileUploadPayload.getFile().getOriginalFilename();

		if (fileName.substring(fileName.length() - 5, fileName.length()).equals(".xlsx")) {
			InputStream excelInputStream = fileUploadPayload.getFile().getInputStream();
			try {
				workbook = WorkbookFactory.create(excelInputStream);
				logger.info("Number of sheets: " + workbook.getNumberOfSheets());
				workbook.forEach(sheet -> {
					logger.info("Title of sheet => " + sheet.getSheetName());					
					int index = 0;
					ClientAccountMstr clientAccountMstr; 				
					for (Row row : sheet) {// Now, start reading each cell one by one in a selected row.
						if (index++ == 0)
							continue;
						clientAccountMstr = new ClientAccountMstr(getCurrentLoginUserId(),currentDate,activeC );
						
						if (row.getCell(clientAccountCellNo) != null && row.getCell(clientAccountCellNo).getCellType() == CellType.STRING) {							
							clientAccountMstr.setClientAccount(row.getCell(clientAccountCellNo).getStringCellValue().trim());
						}						
						clientAccountMstrList.add(clientAccountMstr);
					}//End of for loop
				});
			} catch (EncryptedDocumentException | IOException e) {
				logger.error(e.getMessage(), e);
			} finally {
				try {
					if (workbook != null)
						workbook.close();
					if (excelInputStream != null)
						excelInputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return clientAccountMstrList;
	}

	@Override
	public List<ClientAccountMstr> saveExcel(List<ClientAccountMstr> clientAccountMstrUploadList) throws DataIntegrityViolationException {
		List<ClientAccountMstr> savedclientAccountMstrUploadList = null;
		
		savedclientAccountMstrUploadList = clientAccountMstrUploadRepo.saveAll(clientAccountMstrUploadList);
	
	return savedclientAccountMstrUploadList;
	}

	@Override
	public void generateExcel(HttpServletResponse response, String fileName) throws Exception{
		// TODO Auto-generated method stub
		
	}

}
