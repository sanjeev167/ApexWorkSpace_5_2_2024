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
import com.nus.fileupload.entities.HprojectMonthlyRrrr;
import com.nus.fileupload.entities.ProjectMonthlyRrrr;
import com.nus.fileupload.loader.DataLoader;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.fileupload.repo.HprojectMonthlyRrrrRepo;
import com.nus.fileupload.repo.MonthlyRRRRepo;
import com.nus.pvt.master.entities.ProjectCodeMstr;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:42:34 pm<br>
 * @Objective: <br>
 */
@Service
public class MonthlyRRRServiceImpl extends UserLoginBaseService implements MonthlyRRRService{
	private final Logger logger = LoggerFactory.getLogger(MonthlyRRRServiceImpl.class);
	
	@Autowired
	MonthlyRRRRepo monthlyRRRRepo;
	
	@Autowired
	HprojectMonthlyRrrrRepo hprojectMonthlyRrrrRepo;
	
	@Autowired 
    DataLoader dataLoader;
	
	@Autowired
	FileStorageService fileStorageService;
	

	@Override
	public List<ProjectMonthlyRrrr> readExcel(FileUploadPayload fileUploadPayload) throws IOException {
		
		int expectedRateCellNo = 5;// Double		
		int projectCodeCellNo = 3; // String => checked

		List<ProjectMonthlyRrrr> monthlyRRRRListReadFromExcel = new ArrayList<ProjectMonthlyRrrr>();
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
					ProjectMonthlyRrrr projectMonthlyRrrr;	ProjectCodeMstr projectCodeMstr;				
					for (Row row : sheet) {// Now, start reading each cell one by one in a selected row.
						if (index++ == 0)
							continue;
						projectMonthlyRrrr = new ProjectMonthlyRrrr(fileUploadPayload.getFileUploadDate(), getCurrentLoginUserId(),"Y" );
						
						if (row.getCell(expectedRateCellNo) != null && row.getCell(expectedRateCellNo).getCellType() == CellType.NUMERIC) {
							projectMonthlyRrrr.setExpectedRate(row.getCell(expectedRateCellNo).getNumericCellValue());							
						}
												
						if (row.getCell(projectCodeCellNo) != null && row.getCell(projectCodeCellNo).getCellType() == CellType.STRING) {
							projectCodeMstr =dataLoader.getProjectCodeMstrMap().get(row.getCell(projectCodeCellNo).getStringCellValue().trim());
							//System.out.println("Project code = > "+row.getCell(projectCodeCellNo).getStringCellValue().trim());
							projectMonthlyRrrr.setProjectCodeId(projectCodeMstr.getId());
						}
						monthlyRRRRListReadFromExcel.add(projectMonthlyRrrr);
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
		return monthlyRRRRListReadFromExcel;
	}	

	@Override
	public void generateExcel(HttpServletResponse response, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public List<ProjectMonthlyRrrr> saveExcel(List<ProjectMonthlyRrrr> monthlyRRRRListReadFromExcel, FileUploadPayload fileUploadPayload) throws DataIntegrityViolationException, IOException, Exception {
		
		List<ProjectMonthlyRrrr> savedProjectMonthlyRrrr = null;
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
			List<ProjectMonthlyRrrr> updatedMonthlyRRRRListReadFromExcel = updateFileReferenceOfAllRecordsInList(fileReference.getId(),monthlyRRRRListReadFromExcel);			
			//Start file movement				
				List<ProjectMonthlyRrrr> readExistingMonthlyRRRRList = monthlyRRRRepo.getAllByMonthAndYear(fileUploadPayload.getFileUploadDate());	
				if(readExistingMonthlyRRRRList.size()!=0) {	//Check whether file movement is required or not				
					savedProjectMonthlyRrrr = moveFileFromMainToHistoryTable(readExistingMonthlyRRRRList,updatedMonthlyRRRRListReadFromExcel);
			}//End of records availability check
			else {//When no record is found in the main table, then insert this first time uploaded excel
				savedProjectMonthlyRrrr = monthlyRRRRepo.saveAll(updatedMonthlyRRRRListReadFromExcel);	
			}
		}}catch(Exception ex) {
			logger.info("A new RRRR file created at server has been deleted");
			fileStorageService.deleteFiles(fileStorageService.getTargetLocation());//If any exception is caught then the currently created file must be deleted.
		    throw ex;
		}
		return savedProjectMonthlyRrrr;
	}


    @Transactional
	private List<ProjectMonthlyRrrr> moveFileFromMainToHistoryTable(List<ProjectMonthlyRrrr> readExistingMonthlyRRRRList,List<ProjectMonthlyRrrr> updatedMonthlyRRRRListReadFromExcel) {
		saveAllInHistoryTable(readExistingMonthlyRRRRList);				
		monthlyRRRRepo.saveAll(updatedMonthlyRRRRListReadFromExcel);		
		monthlyRRRRepo.deleteAllInBatch(readExistingMonthlyRRRRList);//Deleting from the main table
		return updatedMonthlyRRRRListReadFromExcel;
	}



	private void saveAllInHistoryTable(List<ProjectMonthlyRrrr> readExistingMonthlyRRRRList) {
		
		List<HprojectMonthlyRrrr> preparedHprojectMonthlyRRRRList = new ArrayList<HprojectMonthlyRrrr>();
		HprojectMonthlyRrrr hprojectMonthlyRrrr;
		for(ProjectMonthlyRrrr projectMonthlyRrrr :readExistingMonthlyRRRRList) {			
			   hprojectMonthlyRrrr = new HprojectMonthlyRrrr();		
			   
			   hprojectMonthlyRrrr.setExpectedRate(projectMonthlyRrrr.getExpectedRate());			   
			   hprojectMonthlyRrrr.setProjectCodeId(projectMonthlyRrrr.getProjectCodeId());
			   hprojectMonthlyRrrr.setFileUploadDate(projectMonthlyRrrr.getFileUploadDate());
			  
			   hprojectMonthlyRrrr.setCreatedBy(projectMonthlyRrrr.getCreatedBy());			   
			   hprojectMonthlyRrrr.setCreatedOn(projectMonthlyRrrr.getCreatedOn());			   
			   hprojectMonthlyRrrr.setModifiedBy(projectMonthlyRrrr.getModifiedBy());
			   hprojectMonthlyRrrr.setModifiedOn(projectMonthlyRrrr.getModifiedOn());
			   hprojectMonthlyRrrr.setActiveC(projectMonthlyRrrr.getActiveC());
			   hprojectMonthlyRrrr.setFileReferenceId(projectMonthlyRrrr.getFileReferenceId());  
			   
			   preparedHprojectMonthlyRRRRList.add(hprojectMonthlyRrrr);   
			}
		    hprojectMonthlyRrrrRepo.saveAll(preparedHprojectMonthlyRRRRList);//Saving in history table			
	}



	private List<ProjectMonthlyRrrr> updateFileReferenceOfAllRecordsInList(Integer fileReferenceId,List<ProjectMonthlyRrrr> monthlyRRRRListReadFromExcel) {
		for(ProjectMonthlyRrrr projectMonthlyRrrr:monthlyRRRRListReadFromExcel) {
			projectMonthlyRrrr.setFileReferenceId(fileReferenceId);
		}		
		return monthlyRRRRListReadFromExcel;
	}

	@Override
	public List<ProjectMonthlyRrrr> getMonthlyRRRRBetweenMonths(int projectCodeId, Date fFileUploadDate,Date tFileUploadDate) {
		List<ProjectMonthlyRrrr> monthlyRRRRBetweenMonthsList = 
				monthlyRRRRepo.getMonthlyRRRRBetweenMonths(projectCodeId, fFileUploadDate, tFileUploadDate);
		return monthlyRRRRBetweenMonthsList;
	}

	@Override
	public Integer getExpectedIncreasePMCountFromRRRR(int projectCodeId, Date fFileUploadDate,Date tFileUploadDate) {
		return	monthlyRRRRepo.getMonthlyExpectedIncreasePM_RRRR(projectCodeId,  fFileUploadDate, tFileUploadDate);	
	}

	@Override
	public List<ProjectMonthlyRrrr> getRRRRPotentialRevenuePerMonth(int projectCodeId,Date fFileUploadDate,Date tFileUploadDate) {
		List<ProjectMonthlyRrrr>	rRRRPotentialRevenuePerMonthList =  monthlyRRRRepo.getRRRRPotentialRevenuePerMonth(projectCodeId, fFileUploadDate,tFileUploadDate);	
		return rRRRPotentialRevenuePerMonthList;
	}

	

	
}//End of MonthlyRRRService
