package com.nus.fileupload.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.transaction.annotation.Transactional;

import com.nus.base.service.UserLoginBaseService;
import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.entities.CustomerSatisfaction;
import com.nus.fileupload.entities.FileReference;
import com.nus.fileupload.entities.FileType;
import com.nus.fileupload.entities.HcustomerSatisfaction;
import com.nus.fileupload.loader.DataLoader;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.fileupload.repo.HcustomerSatisfactionRepo;
import com.nus.fileupload.repo.MonthlyCustomerSatisfactionRepo;
import com.nus.pvt.master.entities.ProjectCodeMstr;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:49:11 pm<br>
 * @Objective: <br>
 */
@Service
public class MonthlyCustomerSatisfactionServiceImpl extends UserLoginBaseService implements MonthlyCustomerSatisfactionService{

	private final Logger logger = LoggerFactory.getLogger(MonthlyCustomerSatisfactionServiceImpl.class);
	@Autowired
	MonthlyCustomerSatisfactionRepo monthlyCustomerSatisfactionRepo;
	
	@Autowired
	HcustomerSatisfactionRepo hcustomerSatisfactionRepo;	
	
	@Autowired 
    DataLoader dataLoader;

	@Autowired
	FileStorageService fileStorageService;
	
	@Override
	public List<CustomerSatisfaction> readExcel(FileUploadPayload fileUploadPayload) throws IOException {		
		//checked		
		        int projectCodeCellNo = 2;//String
				int csatDueDateCellNo = 12;//Date		
				int csatClientMailInitiatedCellNo = 13;//Date
				int csatReceivedDateCellNo = 14;//Date		
				int ratingCellNo = 15;//Double
				

				List<CustomerSatisfaction> customerSatisfactionListReadFromExcel = new ArrayList<CustomerSatisfaction>();
				Workbook workbook = null;
				String fileName = fileUploadPayload.getFile().getOriginalFilename();	
				
				if (fileName.substring(fileName.length() - 5, fileName.length()).equals(".xlsx")) {
					InputStream excelInputStream = fileUploadPayload.getFile().getInputStream();
					try {
						workbook = WorkbookFactory.create(excelInputStream);
						logger.info("Number of sheets: " + workbook.getNumberOfSheets());
						
						workbook.forEach(sheet -> {
							logger.info("Title of sheet => " + sheet.getSheetName());	
							//DataFormatter dataFormatter = new DataFormatter();
							//Cell dateCell;
							int index = 0;
							ProjectCodeMstr projectCodeMstr;CustomerSatisfaction customerSatisfaction;
							for (Row row : sheet) {// Now, start reading each cell one by one in a selected row.
								if (index++ == 0)
									continue;
								customerSatisfaction = new CustomerSatisfaction(fileUploadPayload.getFileUploadDate(), getCurrentLoginUserId(),"Y" );
															
								if (row.getCell(projectCodeCellNo) != null && row.getCell(projectCodeCellNo).getCellType() == CellType.STRING) {
									projectCodeMstr =dataLoader.getProjectCodeMstrMap().get(row.getCell(projectCodeCellNo).getStringCellValue().trim());
									//System.out.println("Code = "+row.getCell(projectCodeCellNo).getStringCellValue());
									customerSatisfaction.setProjectCodeId(projectCodeMstr.getId());
								}				
																								
								if (row.getCell(csatDueDateCellNo) != null && row.getCell(csatDueDateCellNo).getCellType() == CellType.STRING) {	
									customerSatisfaction.setCsatDueDate(row.getCell(csatDueDateCellNo).getStringCellValue().trim());
									
								}
								
								if (row.getCell(csatClientMailInitiatedCellNo) != null && row.getCell(csatClientMailInitiatedCellNo).getCellType() == CellType.STRING) {	
									customerSatisfaction.setCsatClientMailInitiated(row.getCell(csatClientMailInitiatedCellNo).getStringCellValue().trim());
								}
								
								if (row.getCell(csatReceivedDateCellNo) != null && row.getCell(csatReceivedDateCellNo).getCellType() == CellType.STRING) {	
									customerSatisfaction.setCsatReceivedDate(row.getCell(csatReceivedDateCellNo).getStringCellValue().trim());
								}																
								
								if (row.getCell(ratingCellNo) != null && row.getCell(ratingCellNo).getCellType() == CellType.NUMERIC) {	
									customerSatisfaction.setRating(row.getCell(ratingCellNo).getNumericCellValue());
								}					
								customerSatisfactionListReadFromExcel.add(customerSatisfaction);
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
				return customerSatisfactionListReadFromExcel;
	}

	@Transactional
	@Override
	public List<CustomerSatisfaction> saveExcel(List<CustomerSatisfaction> customerSatisfactionListReadFromExcel,FileUploadPayload fileUploadPayload) throws DataIntegrityViolationException, IOException, Exception{
		
       List<CustomerSatisfaction> savedCustomerSatisfactionList = null;      
		
		FileType fileType = dataLoader.getFileTypeMap().get(fileUploadPayload.getFileTypeId());
		fileUploadPayload.setFileType(fileType.getFileType());
		String uploadedFileName = fileStorageService.storeFile(fileUploadPayload);
		try {		
		if(uploadedFileName!= null) {//Ensures that a file has been uploaded at server
			//Saving file reference
			fileUploadPayload.setFileServerPath(fileStorageService.getTargetLocation());
			FileReference fileReference = fileStorageService.saveFileReference(fileUploadPayload);
			fileUploadPayload.setFileName(uploadedFileName);			
			//Before saving new record, must update each record of the list with this file-reference-id.
			List<CustomerSatisfaction> updatedCustomerSatisfactionListReadFromExcel = updateFileReferenceOfAllRecordsInList(fileReference.getId(),customerSatisfactionListReadFromExcel);			
			//Start file movement				
				List<CustomerSatisfaction> readExistingCustomerSatisfactionList = monthlyCustomerSatisfactionRepo.getAllByMonthAndYear(fileUploadPayload.getFileUploadDate());	
				if(readExistingCustomerSatisfactionList.size()!=0) {	//Check whether file movement is required or not				
					savedCustomerSatisfactionList = moveFileFromMainToHistoryTable(readExistingCustomerSatisfactionList,updatedCustomerSatisfactionListReadFromExcel);
			}//End of records availability check
			else {//When no record is found in the main table, then insert this first time uploaded excel
				savedCustomerSatisfactionList = monthlyCustomerSatisfactionRepo.saveAll(updatedCustomerSatisfactionListReadFromExcel);	
			}
		}}catch(Exception ex) {
			logger.info("A new customer-satisfaction-file created at server has been deleted");
			fileStorageService.deleteFiles(fileStorageService.getTargetLocation());//If any exception is caught then the currently created file must be deleted.
		    throw ex;
		}
		return savedCustomerSatisfactionList;
	}

	
	private List<CustomerSatisfaction> moveFileFromMainToHistoryTable(List<CustomerSatisfaction> readExistingCustomerSatisfactionList, List<CustomerSatisfaction> updatedCustomerSatisfactionListReadFromExcel) {
		saveAllInHistoryTable(readExistingCustomerSatisfactionList);				
		monthlyCustomerSatisfactionRepo.saveAll(updatedCustomerSatisfactionListReadFromExcel);		
		monthlyCustomerSatisfactionRepo.deleteAllInBatch(readExistingCustomerSatisfactionList);//Deleting from the main table
		return updatedCustomerSatisfactionListReadFromExcel;
	}

	private void saveAllInHistoryTable(List<CustomerSatisfaction> readExistingCustomerSatisfactionList) {
		List<HcustomerSatisfaction> preparedHcustomerSatisfactionList = new ArrayList<HcustomerSatisfaction>();
		
		HcustomerSatisfaction hcustomerSatisfaction;
		
		for(CustomerSatisfaction customerSatisfaction :readExistingCustomerSatisfactionList) {				
			hcustomerSatisfaction = new HcustomerSatisfaction();			
			hcustomerSatisfaction.setCsatDueDate(customerSatisfaction.getCsatDueDate());
			hcustomerSatisfaction.setCsatClientMailInitiated(customerSatisfaction.getCsatClientMailInitiated());			
			hcustomerSatisfaction.setCsatReceivedDate(customerSatisfaction.getCsatReceivedDate());			
			hcustomerSatisfaction.setRating(customerSatisfaction.getRating());			
			hcustomerSatisfaction.setProjectCodeId(customerSatisfaction.getProjectCodeId());
			hcustomerSatisfaction.setFileUploadDate(customerSatisfaction.getFileUploadDate());
			
			hcustomerSatisfaction.setCreatedBy(customerSatisfaction.getCreatedBy());			   
			hcustomerSatisfaction.setCreatedOn(customerSatisfaction.getCreatedOn());			   
			hcustomerSatisfaction.setModifiedBy(customerSatisfaction.getModifiedBy());
			hcustomerSatisfaction.setModifiedOn(customerSatisfaction.getModifiedOn());
			hcustomerSatisfaction.setActiveC(customerSatisfaction.getActiveC());
			hcustomerSatisfaction.setFileReferenceId(customerSatisfaction.getFileReferenceId()); 			   
			preparedHcustomerSatisfactionList.add(hcustomerSatisfaction);   
			}
		hcustomerSatisfactionRepo.saveAll(preparedHcustomerSatisfactionList);//Saving in history table
		
	}

	private List<CustomerSatisfaction> updateFileReferenceOfAllRecordsInList(Integer fileReferenceId, List<CustomerSatisfaction> customerSatisfactionListReadFromExcel) {
		for(CustomerSatisfaction customerSatisfaction:customerSatisfactionListReadFromExcel) {
			customerSatisfaction.setFileReferenceId(fileReferenceId);
		}		
		return customerSatisfactionListReadFromExcel;
	}

	@Override
	public void generateExcel(HttpServletResponse response, String fileName) {		
	}

	@Override
	public List<CustomerSatisfaction> getMonthlyCustomerSatisfactionBetweenMonths(int projectCodeId,Date fFileUploadDate,Date tFileUploadDate) {
		List<CustomerSatisfaction> customerSatisfactionBetweenMonthsList = 
		monthlyCustomerSatisfactionRepo.getMonthlyCustomerSatisfactionBetweenMonths(projectCodeId, fFileUploadDate,tFileUploadDate);
		
		return customerSatisfactionBetweenMonthsList;
	}
	
}//End of MonthlyCustomerSatisfactionServiceImpl
