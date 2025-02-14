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
import com.nus.fileupload.entities.HprojectMonthlyResourceAllocation;
import com.nus.fileupload.entities.ProjectMonthlyResourceAllocation;
import com.nus.fileupload.loader.DataLoader;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.fileupload.model.ResourceAllocationModel;
import com.nus.fileupload.repo.HprojectMonthlyResourceAllocationRepo;
import com.nus.fileupload.repo.MonthlyResourceAllocationRepo;
import com.nus.pvt.master.entities.ProjectCodeMstr;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:46:56 pm<br>
 * @Objective: <br>
 */
@Service
public class MonthlyResourceAllocationServiceImpl extends UserLoginBaseService implements MonthlyResourceAllocationService{
	private final Logger logger = LoggerFactory.getLogger(MonthlyResourceAllocationServiceImpl.class);
	@Autowired
	MonthlyResourceAllocationRepo monthlyResourceAllocationRepo;
	
	@Autowired
	HprojectMonthlyResourceAllocationRepo hprojectMonthlyResourceAllocationRepo;
	
    @Autowired 
    DataLoader dataLoader;
    
    @Autowired
	FileStorageService fileStorageService;
    
	@Override
	public List<ProjectMonthlyResourceAllocation> readExcel(FileUploadPayload fileUploadPayload) throws IOException {
		
		//Checked
		
		int projectCodeCellNo = 5;//String		
		int empStatusCellNo = 2;//String
		int allocationPercentageCellNo = 6;//Double		
		int billingStatusCellNo = 17;//String
		int empGradeCellNo = 33;//String
		int empCodeCellNo= 1; //String
		int empNameCellNo = 3;//String

		List<ProjectMonthlyResourceAllocation> monthlyResourceAllocationListReadFromExcel = new ArrayList<ProjectMonthlyResourceAllocation>();
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
					ProjectCodeMstr projectCodeMstr;ProjectMonthlyResourceAllocation projectMonthlyResourceAllocation;
					for (Row row : sheet) {// Now, start reading each cell one by one in a selected row.
						if (index++ == 0)
							continue;
						 projectMonthlyResourceAllocation = new ProjectMonthlyResourceAllocation(fileUploadPayload.getFileUploadDate(), getCurrentLoginUserId(),"Y" );
						
						if (row.getCell(projectCodeCellNo) != null && row.getCell(projectCodeCellNo).getCellType() == CellType.STRING) {
							projectCodeMstr =dataLoader.getProjectCodeMstrMap().get(row.getCell(projectCodeCellNo).getStringCellValue().trim());							
							//System.out.println("Project Code = "+row.getCell(projectCodeCellNo).getStringCellValue().trim());
							projectMonthlyResourceAllocation.setProjectCodeId(projectCodeMstr.getId());
						}						
												
						if (row.getCell(empCodeCellNo) != null && row.getCell(empCodeCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAllocation.setEmpCode(row.getCell(empCodeCellNo).getStringCellValue().trim());							
						}
						
						if (row.getCell(empNameCellNo) != null && row.getCell(empNameCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAllocation.setEmpName(row.getCell(empNameCellNo).getStringCellValue().trim());							
						}
						
						if (row.getCell(empStatusCellNo) != null && row.getCell(empStatusCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAllocation.setEmpStatus(row.getCell(empStatusCellNo).getStringCellValue().trim());							
						}
						
						if (row.getCell(allocationPercentageCellNo) != null && row.getCell(allocationPercentageCellNo).getCellType() == CellType.NUMERIC) {							
							projectMonthlyResourceAllocation.setAllocationPercentage(row.getCell(allocationPercentageCellNo).getNumericCellValue());
						}			
						
						if (row.getCell(billingStatusCellNo) != null && row.getCell(billingStatusCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAllocation.setBillingStatus(row.getCell(billingStatusCellNo).getStringCellValue().trim());							
						}
						
						if (row.getCell(empGradeCellNo) != null && row.getCell(empGradeCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAllocation.setEmpGrade(row.getCell(empGradeCellNo).getStringCellValue().trim());							
						}
						
						monthlyResourceAllocationListReadFromExcel.add(projectMonthlyResourceAllocation);
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
		return monthlyResourceAllocationListReadFromExcel;
	}

	@Transactional
	@Override
	public List<ProjectMonthlyResourceAllocation> saveExcel(List<ProjectMonthlyResourceAllocation> monthlyResourceAllocationListReadFromExcel,FileUploadPayload fileUploadPayload)throws DataIntegrityViolationException, IOException, Exception {
		List<ProjectMonthlyResourceAllocation> savedMonthlyResourceAllocation = null;		
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
			List<ProjectMonthlyResourceAllocation> updatedMonthlyResourceAllocationListReadFromExcel = updateFileReferenceOfAllRecordsInList(fileReference.getId(),monthlyResourceAllocationListReadFromExcel);			
			//Start file movement				
				List<ProjectMonthlyResourceAllocation> readExistingMonthlyResourceAllocationList = monthlyResourceAllocationRepo.getAllByMonthAndYear(fileUploadPayload.getFileUploadDate());	
				if(readExistingMonthlyResourceAllocationList.size()!=0) {	//Check whether file movement is required or not				
					savedMonthlyResourceAllocation = moveFileFromMainToHistoryTable(readExistingMonthlyResourceAllocationList,updatedMonthlyResourceAllocationListReadFromExcel);
			}//End of records availability check
			else {//When no record is found in the main table, then insert this first time uploaded excel
				savedMonthlyResourceAllocation = monthlyResourceAllocationRepo.saveAll(updatedMonthlyResourceAllocationListReadFromExcel);	
			}
		}}catch(Exception ex) {
			logger.info("A new resource-allocation-file created at server has been deleted");
			fileStorageService.deleteFiles(fileStorageService.getTargetLocation());//If any exception is caught then the currently created file must be deleted.
		    throw ex;
		}
		return savedMonthlyResourceAllocation;
	}

	
	private List<ProjectMonthlyResourceAllocation> moveFileFromMainToHistoryTable(
			List<ProjectMonthlyResourceAllocation> readExistingMonthlyResourceAllocationList,
			List<ProjectMonthlyResourceAllocation> updatedMonthlyResourceAllocationListReadFromExcel) {
		
		saveAllInHistoryTable(readExistingMonthlyResourceAllocationList);				
		monthlyResourceAllocationRepo.saveAll(updatedMonthlyResourceAllocationListReadFromExcel);		
		monthlyResourceAllocationRepo.deleteAllInBatch(readExistingMonthlyResourceAllocationList);//Deleting from the main table
		return updatedMonthlyResourceAllocationListReadFromExcel;
	}

	private void saveAllInHistoryTable(List<ProjectMonthlyResourceAllocation> readExistingMonthlyResourceAllocationList) {
		
		List<HprojectMonthlyResourceAllocation> preparedHprojectMonthlyResourceAllocationList = new ArrayList<HprojectMonthlyResourceAllocation>();
		
		HprojectMonthlyResourceAllocation hprojectMonthlyResourceAllocation;
		
		for(ProjectMonthlyResourceAllocation projectMonthlyResourceAllocation :readExistingMonthlyResourceAllocationList) {			
			hprojectMonthlyResourceAllocation = new HprojectMonthlyResourceAllocation();	
			
			hprojectMonthlyResourceAllocation.setEmpStatus(projectMonthlyResourceAllocation.getEmpStatus());
			hprojectMonthlyResourceAllocation.setAllocationPercentage(projectMonthlyResourceAllocation.getAllocationPercentage());			
			hprojectMonthlyResourceAllocation.setBillingStatus(projectMonthlyResourceAllocation.getBillingStatus());
			hprojectMonthlyResourceAllocation.setEmpGrade(projectMonthlyResourceAllocation.getEmpGrade());			
			
			hprojectMonthlyResourceAllocation.setProjectCodeId(projectMonthlyResourceAllocation.getProjectCodeId());			
			hprojectMonthlyResourceAllocation.setFileUploadDate(projectMonthlyResourceAllocation.getFileUploadDate());
			
			hprojectMonthlyResourceAllocation.setCreatedBy(projectMonthlyResourceAllocation.getCreatedBy());			   
			hprojectMonthlyResourceAllocation.setCreatedOn(projectMonthlyResourceAllocation.getCreatedOn());			   
			hprojectMonthlyResourceAllocation.setModifiedBy(projectMonthlyResourceAllocation.getModifiedBy());
			hprojectMonthlyResourceAllocation.setModifiedOn(projectMonthlyResourceAllocation.getModifiedOn());
			hprojectMonthlyResourceAllocation.setActiveC(projectMonthlyResourceAllocation.getActiveC());
			hprojectMonthlyResourceAllocation.setFileReferenceId(projectMonthlyResourceAllocation.getFileReferenceId());  
			   
			preparedHprojectMonthlyResourceAllocationList.add(hprojectMonthlyResourceAllocation);   
			}
		hprojectMonthlyResourceAllocationRepo.saveAll(preparedHprojectMonthlyResourceAllocationList);//Saving in history table	
	}

	private List<ProjectMonthlyResourceAllocation> updateFileReferenceOfAllRecordsInList(Integer fileReferenceId,
			List<ProjectMonthlyResourceAllocation> monthlyResourceAllocationListReadFromExcel) {
		
		for(ProjectMonthlyResourceAllocation projectMonthlyResourceAllocation:monthlyResourceAllocationListReadFromExcel) {
			projectMonthlyResourceAllocation.setFileReferenceId(fileReferenceId);
		}		
		return monthlyResourceAllocationListReadFromExcel;
	}

	@Override
	public void generateExcel(HttpServletResponse response, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ResourceAllocationModel> getMonthlyDistinctResourceAllocationBetweenMonths(int projectCodeId,
			Date fFileUploadDate,Date tFileUploadDate) {
		List<ResourceAllocationModel> monthlyResourceAllocationList = 
				monthlyResourceAllocationRepo.getMonthlyDistinctResourceAllocationBetweenMonths(projectCodeId,
						fFileUploadDate,tFileUploadDate);
		return monthlyResourceAllocationList;
	}

	@Override
	public List<ProjectMonthlyResourceAllocation> getDistinctResourceAllocationForCurrentMonth(int projectCodeId,
			Date fileUploadDate) {
		List<ProjectMonthlyResourceAllocation> distinctResourceAllocationForCurrentMonthList=
				monthlyResourceAllocationRepo.getDistinctResourceAllocationForCurrentMonth(projectCodeId,
						fileUploadDate);
		return distinctResourceAllocationForCurrentMonthList;
	}

}//End of MonthlyResourceAllocationServiceImpl
