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
import com.nus.fileupload.entities.FileReference;
import com.nus.fileupload.entities.FileType;
import com.nus.fileupload.entities.HprojectMonthlyResourceUtilization;
import com.nus.fileupload.entities.ProjectMonthlyResourceUtilization;
import com.nus.fileupload.loader.DataLoader;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.fileupload.repo.HprojectMonthlyResourceUtilizationRepo;
import com.nus.fileupload.repo.MonthlyResourceUtilizationRepo;
import com.nus.pvt.master.entities.ProjectCodeMstr;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:44:48 pm<br>
 * @Objective: <br>
 */
@Service
public class MonthlyResourceUtilizationServiceImpl extends UserLoginBaseService implements MonthlyResourceUtilizationService{
	
	private final Logger logger = LoggerFactory.getLogger(MonthlyResourceUtilizationServiceImpl.class);
	
	@Autowired
	MonthlyResourceUtilizationRepo monthlyResourceUtilizationRepo;
	
	@Autowired
	HprojectMonthlyResourceUtilizationRepo hprojectMonthlyResourceUtilizationRepo;
	
	@Autowired 
    DataLoader dataLoader;
	
	@Autowired
	FileStorageService fileStorageService;

	@Override
	public List<ProjectMonthlyResourceUtilization> readExcel(FileUploadPayload fileUploadPayload) throws IOException {
		
		int billedDaysCellValue = 7;// Integer => Checked
		int availableDaysCellValue = 6;//Integer => Checked		
		int projectCodeCellNo = 4;//String		
		
		List<ProjectMonthlyResourceUtilization> monthlyResourceUtilizationListReadFromExcel = new ArrayList<>();
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
					ProjectCodeMstr projectCodeMstr; ProjectMonthlyResourceUtilization projectMonthlyResourceUtilization;					
					for (Row row : sheet) {// Now, start reading each cell one by one in a selected row.
						if (index++ == 0)
							continue;
						projectMonthlyResourceUtilization = new ProjectMonthlyResourceUtilization(fileUploadPayload.getFileUploadDate(), getCurrentLoginUserId(),currentDate,"Y" );
						
						if (row.getCell(billedDaysCellValue) != null && row.getCell(billedDaysCellValue).getCellType() == CellType.NUMERIC) {
							projectMonthlyResourceUtilization.setBilledDays((int) row.getCell(billedDaysCellValue).getNumericCellValue());
						}
						
						if (row.getCell(availableDaysCellValue) != null && row.getCell(availableDaysCellValue).getCellType() == CellType.NUMERIC) {
							projectMonthlyResourceUtilization.setAvailableDays((int) row.getCell(availableDaysCellValue).getNumericCellValue());
						}				
						
						if (row.getCell(projectCodeCellNo) != null && row.getCell(projectCodeCellNo).getCellType() == CellType.STRING) {
							projectCodeMstr =dataLoader.getProjectCodeMstrMap().get(row.getCell(projectCodeCellNo).getStringCellValue().trim());	
							//System.out.println("Project Code =>"+row.getCell(projectCodeCellNo).getStringCellValue());							
							projectMonthlyResourceUtilization.setProjectCodeId(projectCodeMstr.getId());
						}					
						
						monthlyResourceUtilizationListReadFromExcel.add(projectMonthlyResourceUtilization);
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
		return monthlyResourceUtilizationListReadFromExcel;
	}	

	@Override
	public void generateExcel(HttpServletResponse response, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public List<ProjectMonthlyResourceUtilization> saveExcel(List<ProjectMonthlyResourceUtilization> monthlyResourceUtilizationListReadFromExcel,FileUploadPayload fileUploadPayload) throws DataIntegrityViolationException, IOException, Exception {
		List<ProjectMonthlyResourceUtilization> savedProjectMonthlyResourceUtilizationList = null;      
		
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
			List<ProjectMonthlyResourceUtilization> updatedMonthlyResourceUtilizationListReadFromExcel = updateFileReferenceOfAllRecordsInList(fileReference.getId(),monthlyResourceUtilizationListReadFromExcel);			
			//Start file movement				
				List<ProjectMonthlyResourceUtilization> readExistingMonthlyResourceUtilizationList = monthlyResourceUtilizationRepo.getAllByMonthAndYear(fileUploadPayload.getFileUploadDate());	
				if(readExistingMonthlyResourceUtilizationList.size()!=0) {	//Check whether file movement is required or not				
					savedProjectMonthlyResourceUtilizationList = moveFileFromMainToHistoryTable(readExistingMonthlyResourceUtilizationList,updatedMonthlyResourceUtilizationListReadFromExcel);
			}//End of records availability check
			else {//When no record is found in the main table, then insert this first time uploaded excel
				savedProjectMonthlyResourceUtilizationList = monthlyResourceUtilizationRepo.saveAll(updatedMonthlyResourceUtilizationListReadFromExcel);	
			}
		}}catch(Exception ex) {
			logger.info("A new resource-utilization-file created at server has been deleted");
			fileStorageService.deleteFiles(fileStorageService.getTargetLocation());//If any exception is caught then the currently created file must be deleted.
		    throw ex;
		}
		return savedProjectMonthlyResourceUtilizationList;
	}

	
	private List<ProjectMonthlyResourceUtilization> moveFileFromMainToHistoryTable(List<ProjectMonthlyResourceUtilization> readExistingMonthlyResourceUtilizationList,
			List<ProjectMonthlyResourceUtilization> updatedMonthlyResourceUtilizationListReadFromExcel) {
		
		saveAllInHistoryTable(readExistingMonthlyResourceUtilizationList);				
		monthlyResourceUtilizationRepo.saveAll(updatedMonthlyResourceUtilizationListReadFromExcel);		
		monthlyResourceUtilizationRepo.deleteAllInBatch(readExistingMonthlyResourceUtilizationList);//Deleting from the main table
		return updatedMonthlyResourceUtilizationListReadFromExcel;
	}

	private void saveAllInHistoryTable(List<ProjectMonthlyResourceUtilization> readExistingMonthlyResourceUtilizationList) {
		
		List<HprojectMonthlyResourceUtilization> preparedHprojectMonthlyResourceUtilizationList = new ArrayList<HprojectMonthlyResourceUtilization>();
		HprojectMonthlyResourceUtilization hprojectMonthlyResourceUtilization;
		for(ProjectMonthlyResourceUtilization projectMonthlyResourceUtilization :readExistingMonthlyResourceUtilizationList) {			
			hprojectMonthlyResourceUtilization = new HprojectMonthlyResourceUtilization();				   
			hprojectMonthlyResourceUtilization.setBilledDays(projectMonthlyResourceUtilization.getBilledDays());
			hprojectMonthlyResourceUtilization.setAvailableDays(projectMonthlyResourceUtilization.getAvailableDays());			
			hprojectMonthlyResourceUtilization.setProjectCodeId(projectMonthlyResourceUtilization.getProjectCodeId());			
			hprojectMonthlyResourceUtilization.setFileUploadDate(projectMonthlyResourceUtilization.getFileUploadDate());
			
			hprojectMonthlyResourceUtilization.setCreatedBy(projectMonthlyResourceUtilization.getCreatedBy());			   
			hprojectMonthlyResourceUtilization.setCreatedOn(projectMonthlyResourceUtilization.getCreatedOn());			   
			hprojectMonthlyResourceUtilization.setModifiedBy(projectMonthlyResourceUtilization.getModifiedBy());
			hprojectMonthlyResourceUtilization.setModifiedOn(projectMonthlyResourceUtilization.getModifiedOn());
			hprojectMonthlyResourceUtilization.setActiveC(projectMonthlyResourceUtilization.getActiveC());
			hprojectMonthlyResourceUtilization.setFileReferenceId(projectMonthlyResourceUtilization.getFileReferenceId());  
			   
			preparedHprojectMonthlyResourceUtilizationList.add(hprojectMonthlyResourceUtilization);   
			}
		hprojectMonthlyResourceUtilizationRepo.saveAll(preparedHprojectMonthlyResourceUtilizationList);//Saving in history table	
		
	}

	private List<ProjectMonthlyResourceUtilization> updateFileReferenceOfAllRecordsInList(Integer fileReferenceId,List<ProjectMonthlyResourceUtilization> monthlyResourceUtilizationListReadFromExcel) {
		for(ProjectMonthlyResourceUtilization projectMonthlyResourceUtilization:monthlyResourceUtilizationListReadFromExcel) {
			projectMonthlyResourceUtilization.setFileReferenceId(fileReferenceId);
		}		
		return monthlyResourceUtilizationListReadFromExcel;
	}

	@Override
	public List<ProjectMonthlyResourceUtilization> getMonthlyResourceUtilizationBetweenMonths(int projectCodeId,
			Date fFileUploadDate,Date tFileUploadDate) {
		
		List<ProjectMonthlyResourceUtilization> monthlyResourceUtilizationList = 
				monthlyResourceUtilizationRepo.getMonthlyResourceUtilizationBetweenMonths(projectCodeId,fFileUploadDate,tFileUploadDate);
		
		return monthlyResourceUtilizationList;
	}

	@Override
	public ProjectMonthlyResourceUtilization getAMonthSpecificResourceUtilization(int projectCodeId,Date twoMonthBackFileUploadDate) {
		ProjectMonthlyResourceUtilization projectMonthlyResourceUtilization =
				monthlyResourceUtilizationRepo.getAMonthSpecificResourceUtilization(projectCodeId,twoMonthBackFileUploadDate);
		return projectMonthlyResourceUtilization;
	}	

}//End of MonthlyResourceUtilizationServiceImpl
