package com.nus.fileupload.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
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
import com.nus.fileupload.entities.HprojectMonthlyResourceAttrition;
import com.nus.fileupload.entities.ProjectMonthlyResourceAttrition;
import com.nus.fileupload.loader.DataLoader;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.fileupload.repo.HprojectMonthlyResourceAttritionRepo;
import com.nus.fileupload.repo.MonthlyAttritionRepo;
import com.nus.pvt.master.entities.ProjectCodeMstr;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:52:56 pm<br>
 * @Objective: <br>
 * 
 */

@Service
public class MonthlyAttritionServiceImpl extends UserLoginBaseService implements MonthlyAttritionService {
	
	private final Logger logger = LoggerFactory.getLogger(MonthlyAttritionServiceImpl.class);
	
	@Autowired
	MonthlyAttritionRepo monthlyAttritionRepo;
	
	@Autowired
	HprojectMonthlyResourceAttritionRepo hprojectMonthlyResourceAttritionRepo;
	
	@Autowired 
    DataLoader dataLoader;
	
	@Autowired
	FileStorageService fileStorageService;
	
	@Override
	public List<ProjectMonthlyResourceAttrition> readExcel(FileUploadPayload fileUploadPayload) throws IOException {		
		
		int empCodeCellNo = 0;// String => Checked
		int projectCodeCellNo = 1;// String => Checked
		int empStatusCellNo = 2;
		int empNameCellNo = 3;
		int deptNameCellNo = 4;
	    int designationCellNo = 5;
	    int dojCellNo = 6;
		int reportinManagerCellNo = 7;
		int workLocationCellNo = 8;
		int employementStatusCellNo = 9;
		int lwdCellNo = 10;
		int unitCellNo = 11;								
		int attritionTypeCellNo=12;	
		
		List<ProjectMonthlyResourceAttrition> monthlyResourceAttritionListReadFromExcel = new ArrayList<ProjectMonthlyResourceAttrition>();
		Workbook workbook = null;
		String fileName = fileUploadPayload.getFile().getOriginalFilename();

		if (fileName.substring(fileName.length() - 5, fileName.length()).equals(".xlsx")) {
			InputStream excelInputStream = fileUploadPayload.getFile().getInputStream();
			try {
				workbook = WorkbookFactory.create(excelInputStream);
				logger.info("Number of sheets: " + workbook.getNumberOfSheets());
				workbook.forEach(sheet -> {
					logger.info("Title of sheet => " + sheet.getSheetName());					
					int index = 0;Cell dateCell;
					ProjectMonthlyResourceAttrition projectMonthlyResourceAttrition; ProjectCodeMstr projectCodeMstr;				
					for (Row row : sheet) {// Now, start reading each cell one by one in a selected row.
						if (index++ == 0)
							continue;
						projectMonthlyResourceAttrition = 
								new ProjectMonthlyResourceAttrition(fileUploadPayload.getFileUploadDate(), getCurrentLoginUserId(),currentDate,"Y" );
						
						if (row.getCell(empCodeCellNo) != null && row.getCell(empCodeCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAttrition.setEmpCode(row.getCell(empCodeCellNo).getStringCellValue());
							//System.out.println("Emp Code = "+row.getCell(empCodeCellNo));
						}												
						
						if (row.getCell(projectCodeCellNo) != null && row.getCell(projectCodeCellNo).getCellType() == CellType.STRING) {
							projectCodeMstr =dataLoader.getProjectCodeMstrMap().get(row.getCell(projectCodeCellNo).getStringCellValue().trim());
							//System.out.println("Project Code in excel => "+row.getCell(projectCodeCellNo).getStringCellValue().trim());
							projectMonthlyResourceAttrition.setProjectCodeId(projectCodeMstr.getId());	
						}
						if (row.getCell(empStatusCellNo) != null && row.getCell(empStatusCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAttrition.setEmpStatus(row.getCell(empStatusCellNo).getStringCellValue());
						}
						if (row.getCell(empNameCellNo) != null && row.getCell(empNameCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAttrition.setEmpName(row.getCell(empNameCellNo).getStringCellValue());
						}
						if (row.getCell(deptNameCellNo) != null && row.getCell(deptNameCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAttrition.setDeptName(row.getCell(deptNameCellNo).getStringCellValue());
						}
						if (row.getCell(designationCellNo) != null && row.getCell(designationCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAttrition.setDesignation(row.getCell(designationCellNo).getStringCellValue());
						}						
					    dateCell = row.getCell(dojCellNo);
						if (DateUtil.isCellDateFormatted(dateCell)) {
							projectMonthlyResourceAttrition.setDoj(dateCell.getDateCellValue());
						}						
						if (row.getCell(reportinManagerCellNo) != null && row.getCell(reportinManagerCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAttrition.setReportinManager(row.getCell(reportinManagerCellNo).getStringCellValue());
						}
						if (row.getCell(workLocationCellNo) != null && row.getCell(workLocationCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAttrition.setWorkLocation(row.getCell(workLocationCellNo).getStringCellValue());
						}
						if (row.getCell(employementStatusCellNo) != null && row.getCell(employementStatusCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAttrition.setEmployementStatus(row.getCell(employementStatusCellNo).getStringCellValue());
						}
						dateCell = row.getCell(lwdCellNo);
						if (DateUtil.isCellDateFormatted(dateCell)) {
						    projectMonthlyResourceAttrition.setLwd(dateCell.getDateCellValue());
						}
						
						if (row.getCell(unitCellNo) != null && row.getCell(unitCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAttrition.setUnit(row.getCell(unitCellNo).getStringCellValue());
						}				
						if (row.getCell(attritionTypeCellNo) != null && row.getCell(attritionTypeCellNo).getCellType() == CellType.STRING) {
							projectMonthlyResourceAttrition.setAttritionType(row.getCell(attritionTypeCellNo).getStringCellValue());
						}
						monthlyResourceAttritionListReadFromExcel.add(projectMonthlyResourceAttrition);
					}//End of for loop
				});
			} catch (EncryptedDocumentException | IOException ex) {
				logger.error(ex.getMessage(), ex);
				throw ex;
				
			} finally {
				try {
					if (workbook != null)
						workbook.close();
					if (excelInputStream != null)
						excelInputStream.close();
				} catch (IOException ex) {
					logger.error(ex.getMessage(), ex);
					throw ex;
				}
			}
		}
		return monthlyResourceAttritionListReadFromExcel;
	}//End of readExcel
	
	@Transactional
	@Override
	public List<ProjectMonthlyResourceAttrition> saveExcel(List<ProjectMonthlyResourceAttrition> monthlyResourceAttritionListReadFromExcel,FileUploadPayload fileUploadPayload)throws DataIntegrityViolationException, IOException, Exception{
		
		List<ProjectMonthlyResourceAttrition> savedMonthlyResourceAttritionList = null;
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
			List<ProjectMonthlyResourceAttrition> updatedMonthlyResourceAttritionListReadFromExcel = updateFileReferenceOfAllRecordsInList(fileReference.getId(),monthlyResourceAttritionListReadFromExcel);			
			//Start file movement				
				List<ProjectMonthlyResourceAttrition> readExistingMonthlyResourceAttritionList = monthlyAttritionRepo.getAllByMonthAndYear(fileUploadPayload.getFileUploadDate());	
				if(readExistingMonthlyResourceAttritionList.size()!=0) {	//Check whether file movement is required or not				
					savedMonthlyResourceAttritionList = moveFileFromMainToHistoryTable(readExistingMonthlyResourceAttritionList,updatedMonthlyResourceAttritionListReadFromExcel);
			}//End of records availability check
			else {//When no record is found in the main table, then insert this first time uploaded excel
				savedMonthlyResourceAttritionList = monthlyAttritionRepo.saveAll(updatedMonthlyResourceAttritionListReadFromExcel);	
			}
		}}catch(Exception ex) {
			logger.info("A new monthly-attrition-file created at server has been deleted");
			fileStorageService.deleteFiles(fileStorageService.getTargetLocation());//If any exception is caught then the currently created file must be deleted.
		    throw ex;
		}
		return savedMonthlyResourceAttritionList;
	}

	
	private List<ProjectMonthlyResourceAttrition> moveFileFromMainToHistoryTable(
			List<ProjectMonthlyResourceAttrition> readExistingProjectMonthlyResourceAttritionList,
			List<ProjectMonthlyResourceAttrition> updatedMonthlyResourceAttritionListReadFromExcel) {
		
		saveAllInHistoryTable(readExistingProjectMonthlyResourceAttritionList);				
		monthlyAttritionRepo.saveAll(updatedMonthlyResourceAttritionListReadFromExcel);		
		monthlyAttritionRepo.deleteAllInBatch(readExistingProjectMonthlyResourceAttritionList);//Deleting from the main table
		return updatedMonthlyResourceAttritionListReadFromExcel;
	}

	private void saveAllInHistoryTable(List<ProjectMonthlyResourceAttrition> readExistingProjectMonthlyResourceAttritionList) {
		
		List<HprojectMonthlyResourceAttrition> preparedHprojectMonthlyResourceAttritionList = new ArrayList<HprojectMonthlyResourceAttrition>();		
		HprojectMonthlyResourceAttrition hprojectMonthlyResourceAttrition;
		
		for(ProjectMonthlyResourceAttrition projectMonthlyResourceAttrition :readExistingProjectMonthlyResourceAttritionList) {			
			hprojectMonthlyResourceAttrition = new HprojectMonthlyResourceAttrition();		
			
			hprojectMonthlyResourceAttrition.setEmpCode(projectMonthlyResourceAttrition.getEmpCode());
			hprojectMonthlyResourceAttrition.setEmpStatus(projectMonthlyResourceAttrition.getEmpStatus());			
			hprojectMonthlyResourceAttrition.setEmpName(projectMonthlyResourceAttrition.getEmpName());
			hprojectMonthlyResourceAttrition.setDeptName(projectMonthlyResourceAttrition.getDeptName());			
			hprojectMonthlyResourceAttrition.setDesignation(projectMonthlyResourceAttrition.getDesignation());			
			hprojectMonthlyResourceAttrition.setDoj(projectMonthlyResourceAttrition.getDoj());
			hprojectMonthlyResourceAttrition.setReportinManager(projectMonthlyResourceAttrition.getReportinManager());			
			hprojectMonthlyResourceAttrition.setWorkLocation(projectMonthlyResourceAttrition.getWorkLocation());
			hprojectMonthlyResourceAttrition.setEmployementStatus(projectMonthlyResourceAttrition.getEmployementStatus());
			hprojectMonthlyResourceAttrition.setLwd(projectMonthlyResourceAttrition.getLwd());
			hprojectMonthlyResourceAttrition.setUnit(projectMonthlyResourceAttrition.getUnit());
			hprojectMonthlyResourceAttrition.setAttritionType(projectMonthlyResourceAttrition.getAttritionType());						
			
			hprojectMonthlyResourceAttrition.setProjectCodeId(projectMonthlyResourceAttrition.getProjectCodeId());			
			
			hprojectMonthlyResourceAttrition.setFileUploadDate(projectMonthlyResourceAttrition.getFileUploadDate());
			
			hprojectMonthlyResourceAttrition.setCreatedBy(projectMonthlyResourceAttrition.getCreatedBy());			   
			hprojectMonthlyResourceAttrition.setCreatedOn(projectMonthlyResourceAttrition.getCreatedOn());			   
			hprojectMonthlyResourceAttrition.setModifiedBy(projectMonthlyResourceAttrition.getModifiedBy());
			hprojectMonthlyResourceAttrition.setModifiedOn(projectMonthlyResourceAttrition.getModifiedOn());
			hprojectMonthlyResourceAttrition.setActiveC(projectMonthlyResourceAttrition.getActiveC());
			hprojectMonthlyResourceAttrition.setFileReferenceId(projectMonthlyResourceAttrition.getFileReferenceId());  
			   
			preparedHprojectMonthlyResourceAttritionList.add(hprojectMonthlyResourceAttrition);   
			}
		hprojectMonthlyResourceAttritionRepo.saveAll(preparedHprojectMonthlyResourceAttritionList);//Saving in history table	
		
	}

	private List<ProjectMonthlyResourceAttrition> updateFileReferenceOfAllRecordsInList(Integer fileReferenceId,
			List<ProjectMonthlyResourceAttrition> monthlyResourceAttritionListReadFromExcel) {		
		for(ProjectMonthlyResourceAttrition projectMonthlyResourceAttrition:monthlyResourceAttritionListReadFromExcel) {
			projectMonthlyResourceAttrition.setFileReferenceId(fileReferenceId);
		}		
		return monthlyResourceAttritionListReadFromExcel;
	}

	@Override
	public void generateExcel(HttpServletResponse response, String fileName) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProjectMonthlyResourceAttrition> getMonthlyResourceAttritionDataBetweenMonths(int projectCodeId,
			Date fFileUploadDate,Date tFileUploadDate) {
		List<ProjectMonthlyResourceAttrition> monthlyResourceAttritionList= 
				monthlyAttritionRepo.getMonthlyAttritionDataBetweenMonths(projectCodeId, fFileUploadDate, tFileUploadDate);
		return monthlyResourceAttritionList;
	}

	@Override
	public List<ProjectMonthlyResourceAttrition> getAMonthSpecificAttrition(Integer projectCodeId, Date currentMonthYearDate) {
		
		return monthlyAttritionRepo.getAMonthSpecificAttrition(projectCodeId, currentMonthYearDate);
		
	}

	

	/*
	 * if (row.getCell(1) != null) {
	 * monthlyAttritionModel.setName(dataFormatter.formatCellValue(row.getCell(1)));
	 * }
	 */

	/*
	 * Cell dateCell = row.getCell(2); if (DateUtil.isCellDateFormatted(dateCell)) {
	 * LocalDate date =
	 * dateCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault())
	 * .toLocalDate(); monthlyAttritionModel.setDate(date); } if (row.getCell(4) !=
	 * null && row.getCell(4).getCellType() == CellType.BOOLEAN) {
	 * monthlyAttritionModel.setStatus(row.getCell(4).getBooleanCellValue()); }
	 */

}// End of MonthlyAttritionServiceImpl
