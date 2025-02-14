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
import com.nus.fileupload.entities.FileReference;
import com.nus.fileupload.entities.FileType;
import com.nus.fileupload.entities.HprojectMonthlyPlcc;
import com.nus.fileupload.entities.ProjectMonthlyPlcc;
import com.nus.fileupload.loader.DataLoader;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.fileupload.repo.HprojectMonthlyPlccRepo;
import com.nus.fileupload.repo.ProjectMonthlyPlccRepo;
import com.nus.pvt.master.entities.ProjectCodeMstr;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 25-Jan-2025<br>
 * @Time: 7:06:24 pm<br>
 * @Objective: <br>
 */
@Service
public class ProjectMonthlyPlccServiceImpl extends UserLoginBaseService implements ProjectMonthlyPlccService{

    private final Logger logger = LoggerFactory.getLogger(ProjectMonthlyPlccServiceImpl.class);
	
	@Autowired
	ProjectMonthlyPlccRepo projectMonthlyPlccRepo;
	@Autowired
	HprojectMonthlyPlccRepo hprojectMonthlyPlccRepo;
	
	@Autowired 
    DataLoader dataLoader;
	
	@Autowired
	FileStorageService fileStorageService;
	
	@Override
	public List<ProjectMonthlyPlcc> readExcel(FileUploadPayload fileUploadPayload) throws IOException {
		
		int totalRevenueAmountCellNo=14;// Double => Checked	
		int marginAmountCellNo =28;// Double => Checked
		int fteCellNo = 7; // Double
		int projectCodeCellNo= 0; // String => Checked
		
		List<ProjectMonthlyPlcc> projectMonthlyPlccList = new ArrayList<ProjectMonthlyPlcc>();
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
					ProjectMonthlyPlcc projectMonthlyPlcc;	ProjectCodeMstr projectCodeMstr;
					
					
					for (Row row : sheet) {// Now, start reading each cell one by one in a selected row.
						if (index++ == 0)
							continue;
						
						projectMonthlyPlcc = new ProjectMonthlyPlcc(fileUploadPayload.getFileUploadDate(), getCurrentLoginUserId(),  activeC);
												
						if (row.getCell(totalRevenueAmountCellNo) != null && row.getCell(totalRevenueAmountCellNo).getCellType() == CellType.NUMERIC) {
							projectMonthlyPlcc.setTotalRevenueAmount(row.getCell(totalRevenueAmountCellNo).getNumericCellValue());							
						}
						
						if (row.getCell(marginAmountCellNo) != null && row.getCell(marginAmountCellNo).getCellType() == CellType.NUMERIC) {
							projectMonthlyPlcc.setMarginAmount(row.getCell(marginAmountCellNo).getNumericCellValue());
						}
						
						if (row.getCell(fteCellNo) != null && row.getCell(fteCellNo).getCellType() == CellType.NUMERIC) {
							projectMonthlyPlcc.setFte(row.getCell(fteCellNo).getNumericCellValue());
						}
						
						if (row.getCell(projectCodeCellNo) != null && row.getCell(projectCodeCellNo).getCellType() == CellType.STRING) {
							projectCodeMstr = dataLoader.getProjectCodeMstrMap().get(row.getCell(projectCodeCellNo).getStringCellValue().trim());
							//System.out.println("Coming project code from excel = "+row.getCell(projectCodeCellNo).getStringCellValue().trim());
							
							projectMonthlyPlcc.setProjectCodeId(projectCodeMstr.getId());
						}			
						projectMonthlyPlccList.add(projectMonthlyPlcc);
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
		return projectMonthlyPlccList;
	}
	
	@Transactional
	@Override
	public List<ProjectMonthlyPlcc> saveExcel(List<ProjectMonthlyPlcc> monthlyPlccListReadFromExcel, FileUploadPayload fileUploadPayload ) throws IOException, Exception{
		
		List<ProjectMonthlyPlcc> savedProjectMonthlyPlcc = null;
		FileType fileType = dataLoader.getFileTypeMap().get(fileUploadPayload.getFileTypeId());
		fileUploadPayload.setFileType(fileType.getFileType());
		String uploadedFileName = fileStorageService.storeFile(fileUploadPayload);
		try {		
		if(uploadedFileName!= null) {//Ensures that a file has been uploaded at server
			//Saving file reference
			fileUploadPayload.setFileServerPath(fileStorageService.getTargetLocation());
			FileReference fileReference = fileStorageService.saveFileReference(fileUploadPayload);
			fileUploadPayload.setFileName(uploadedFileName);			
			//Before saving, must update each record of the list with this file-reference-id.
			List<ProjectMonthlyPlcc> updatedMonthlyPlccListReadFromExcel = updateFileReferenceOfAllRecordsInList(fileReference.getId(),monthlyPlccListReadFromExcel);			
			//Start file movement				
				List<ProjectMonthlyPlcc> readProjectMonthlyPlccList = projectMonthlyPlccRepo.getAllByMonthAndYear(fileUploadPayload.getFileUploadDate());	
				if(readProjectMonthlyPlccList.size()!=0) {					
					savedProjectMonthlyPlcc = moveFileFromMainToHistoryTable(readProjectMonthlyPlccList,updatedMonthlyPlccListReadFromExcel);
			}//End of records availability check
			else {//When no record is found in the main table, then insert this first time uploaded excel
				savedProjectMonthlyPlcc = projectMonthlyPlccRepo.saveAll(updatedMonthlyPlccListReadFromExcel);	
			}
		}}catch(Exception ex) {
			logger.info("A new PLCC file created at server has been deleted");
			fileStorageService.deleteFiles(fileStorageService.getTargetLocation());//If any exception is caught then the currently created file must be deleted.
		    throw ex;
		}
		return savedProjectMonthlyPlcc;
	}	
	
	@Transactional
	
	@Override
	public void generateExcel(HttpServletResponse response, String fileName) {
		// TODO Auto-generated method stub		
	}
	
	@Transactional
    private List<ProjectMonthlyPlcc> moveFileFromMainToHistoryTable(List<ProjectMonthlyPlcc> readExistingMonthlyPlccList,List<ProjectMonthlyPlcc> updatedMonthlyPlccListReadFromExcel) {
		
		saveAllInHistoryTable(readExistingMonthlyPlccList);				
		projectMonthlyPlccRepo.saveAll(updatedMonthlyPlccListReadFromExcel);		
		projectMonthlyPlccRepo.deleteAllInBatch(readExistingMonthlyPlccList);//Deleting from main table
		return updatedMonthlyPlccListReadFromExcel;
	}

	private void saveAllInHistoryTable(List<ProjectMonthlyPlcc> readExistingMonthlyPlccList) {
		
		List<HprojectMonthlyPlcc> preparedHprojectMonthlyPlccList = new ArrayList<HprojectMonthlyPlcc>();
		HprojectMonthlyPlcc hprojectMonthlyPlcc;
		for(ProjectMonthlyPlcc projectMonthlyPlcc :readExistingMonthlyPlccList) {			
			   hprojectMonthlyPlcc = new HprojectMonthlyPlcc();			 
			   hprojectMonthlyPlcc.setTotalRevenueAmount(projectMonthlyPlcc.getTotalRevenueAmount());
			   hprojectMonthlyPlcc.setMarginAmount(projectMonthlyPlcc.getMarginAmount());
			   hprojectMonthlyPlcc.setFte(projectMonthlyPlcc.getFte());
			   hprojectMonthlyPlcc.setProjectCodeId(projectMonthlyPlcc.getProjectCodeId());
			   hprojectMonthlyPlcc.setFileUploadDate(projectMonthlyPlcc.getFileUploadDate());			   
			   hprojectMonthlyPlcc.setCreatedBy(projectMonthlyPlcc.getCreatedBy());			   
			   hprojectMonthlyPlcc.setCreatedOn(projectMonthlyPlcc.getCreatedOn());			   
			   hprojectMonthlyPlcc.setModifiedBy(projectMonthlyPlcc.getModifiedBy());
			   hprojectMonthlyPlcc.setModifiedOn(projectMonthlyPlcc.getModifiedOn());
			   hprojectMonthlyPlcc.setActiveC(projectMonthlyPlcc.getActiveC());
			   hprojectMonthlyPlcc.setFileReferenceId(projectMonthlyPlcc.getFileReferenceId());  
			   
			   preparedHprojectMonthlyPlccList.add(hprojectMonthlyPlcc);   
			}
			hprojectMonthlyPlccRepo.saveAll(preparedHprojectMonthlyPlccList);//Saving in history table		
	}

	private List<ProjectMonthlyPlcc> updateFileReferenceOfAllRecordsInList(int fileReferenceId, List<ProjectMonthlyPlcc> projectMonthlyPlccList) {		
		for(ProjectMonthlyPlcc projectMonthlyPlcc:projectMonthlyPlccList) {
			projectMonthlyPlcc.setFileReferenceId(fileReferenceId);
		}		
		return projectMonthlyPlccList;
	}

	@Override
	public List<ProjectMonthlyPlcc> getMonthlyPlccBetweenMonths(int projectCodeId,Date fFileUploadDate,Date tFileUploadDate) {
		
		List<ProjectMonthlyPlcc> projectMonthlyPlccList = 
				projectMonthlyPlccRepo.getMonthlyPlccDataBetweenMonths(projectCodeId,fFileUploadDate,tFileUploadDate);
		
		return projectMonthlyPlccList;
	}

}
