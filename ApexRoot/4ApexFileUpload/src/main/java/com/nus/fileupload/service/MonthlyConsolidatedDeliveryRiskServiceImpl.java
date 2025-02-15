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
import com.nus.fileupload.entities.ConsolidatedDeliveryRisk;
import com.nus.fileupload.entities.FileReference;
import com.nus.fileupload.entities.FileType;
import com.nus.fileupload.entities.HconsolidatedDeliveryRisk;
import com.nus.fileupload.loader.DataLoader;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.fileupload.repo.HconsolidatedDeliveryRiskRepo;
import com.nus.fileupload.repo.MonthlyConsolidatedDeliveryRiskRepo;
import com.nus.pvt.master.entities.ProjectCodeMstr;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:51:10 pm<br>
 * @Objective: <br>
 */
@Service
public class MonthlyConsolidatedDeliveryRiskServiceImpl extends UserLoginBaseService implements MonthlyConsolidatedDeliveryRiskService{
    
	private final Logger logger = LoggerFactory.getLogger(MonthlyConsolidatedDeliveryRiskServiceImpl.class);
	@Autowired 
    DataLoader dataLoader;
	
	@Autowired
	MonthlyConsolidatedDeliveryRiskRepo monthlyConsolidatedDeliveryRiskRepo;
	
	@Autowired
	HconsolidatedDeliveryRiskRepo hconsolidatedDeliveryRiskRepo;
	
	@Autowired
	FileStorageService fileStorageService;

	@Override
	public List<ConsolidatedDeliveryRisk> readExcel(FileUploadPayload fileUploadPayload) throws IOException {
		//Following cell value needs to be confirmed with team
		
		int riskTypeCellNo=13;// String		
		int sourceCellNo =14;// String
		int riskMainCategoryCellNo =15;// String
		int riskSubCategoryCellNo =16;// String
		
		int occuringProbablityCellNo =17;// String
		int impactCellNo =18;// String
		int riskPriorityNumberCellNo =19;// Double
		int riskExposureCellNo =20;// Double
		
		int responseResolutionStartegyCellNo =21;// String
		int riskOwnerCellNo = 22;// String
		int mitigationPlanCellNo = 23;// String correct => mitigation
		int contigencyPlanCellNo =24;// String
		
		int riskStatusCellNo =25;// String
		int remarkCellNo =26;// String		
		int projectCodeCellNo= 1; // String

		List<ConsolidatedDeliveryRisk> consolidatedDeliveryRiskListReadFromExcel = new ArrayList<ConsolidatedDeliveryRisk>();
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
					ConsolidatedDeliveryRisk consolidatedDeliveryRisk;	ProjectCodeMstr projectCodeMstr;				
					for (Row row : sheet) {// Now, start reading each cell one by one in a selected row.
						if (index++ == 0)
							continue;
						consolidatedDeliveryRisk = new ConsolidatedDeliveryRisk(fileUploadPayload.getFileUploadDate(), getCurrentLoginUserId(),"Y" );
												
						if (row.getCell(riskTypeCellNo) != null && row.getCell(riskTypeCellNo).getCellType() == CellType.STRING) {
							consolidatedDeliveryRisk.setRiskType(row.getCell(riskTypeCellNo).getStringCellValue().trim());							
						}
						
						if (row.getCell(sourceCellNo) != null && row.getCell(sourceCellNo).getCellType() == CellType.STRING) {
							consolidatedDeliveryRisk.setSource(row.getCell(sourceCellNo).getStringCellValue().trim());							
						}
						
						if (row.getCell(riskMainCategoryCellNo) != null && row.getCell(riskMainCategoryCellNo).getCellType() == CellType.STRING) {
							consolidatedDeliveryRisk.setRiskMainCategory(row.getCell(riskMainCategoryCellNo).getStringCellValue().trim());							
						}
						
						if (row.getCell(riskSubCategoryCellNo) != null && row.getCell(riskSubCategoryCellNo).getCellType() == CellType.STRING) {
							consolidatedDeliveryRisk.setRiskSubCategory(row.getCell(riskSubCategoryCellNo).getStringCellValue().trim());							
						}
						
						if (row.getCell(occuringProbablityCellNo) != null && row.getCell(occuringProbablityCellNo).getCellType() == CellType.STRING) {
							consolidatedDeliveryRisk.setOccuringProbablity(row.getCell(occuringProbablityCellNo).getStringCellValue().trim());							
						}
						
						if (row.getCell(impactCellNo) != null && row.getCell(impactCellNo).getCellType() == CellType.STRING) {
							consolidatedDeliveryRisk.setImpact(row.getCell(impactCellNo).getStringCellValue().trim());							
						}
						
						if (row.getCell(riskPriorityNumberCellNo) != null && row.getCell(riskPriorityNumberCellNo).getCellType() == CellType.NUMERIC) {
							consolidatedDeliveryRisk.setRiskPriorityNumber(row.getCell(riskPriorityNumberCellNo).getNumericCellValue());							
						}
						
						if (row.getCell(riskExposureCellNo) != null && row.getCell(riskExposureCellNo).getCellType() == CellType.NUMERIC) {
							consolidatedDeliveryRisk.setRiskExposure(row.getCell(riskExposureCellNo).getNumericCellValue());							
						}
						
						if (row.getCell(responseResolutionStartegyCellNo) != null && row.getCell(responseResolutionStartegyCellNo).getCellType() == CellType.STRING) {
							consolidatedDeliveryRisk.setResponseResolutionStartegy(row.getCell(responseResolutionStartegyCellNo).getStringCellValue().trim());							
						}
						
						if (row.getCell(riskOwnerCellNo) != null && row.getCell(riskOwnerCellNo).getCellType() == CellType.STRING) {
							consolidatedDeliveryRisk.setRiskOwner(row.getCell(riskOwnerCellNo).getStringCellValue().trim());							
						}
						
						if (row.getCell(mitigationPlanCellNo) != null && row.getCell(mitigationPlanCellNo).getCellType() == CellType.STRING) {
							consolidatedDeliveryRisk.setMitigationPlan(row.getCell(mitigationPlanCellNo).getStringCellValue().trim());							
						}
						if (row.getCell(contigencyPlanCellNo) != null && row.getCell(contigencyPlanCellNo).getCellType() == CellType.STRING) {
							consolidatedDeliveryRisk.setContigencyPlan(row.getCell(contigencyPlanCellNo).getStringCellValue().trim());							
						}
						if (row.getCell(riskStatusCellNo) != null && row.getCell(riskStatusCellNo).getCellType() == CellType.STRING) {
							consolidatedDeliveryRisk.setRiskStatus(row.getCell(riskStatusCellNo).getStringCellValue().trim());							
						}
						if (row.getCell(remarkCellNo) != null && row.getCell(remarkCellNo).getCellType() == CellType.STRING) {
							consolidatedDeliveryRisk.setRemark(row.getCell(remarkCellNo).getStringCellValue().trim());							
						}
						
						if (row.getCell(remarkCellNo) != null && row.getCell(remarkCellNo).getCellType() == CellType.STRING) {
							consolidatedDeliveryRisk.setRemark(row.getCell(remarkCellNo).getStringCellValue().trim());							
						}						
						if (row.getCell(projectCodeCellNo) != null && row.getCell(projectCodeCellNo).getCellType() == CellType.STRING) {
							projectCodeMstr =dataLoader.getProjectCodeMstrMap().get(row.getCell(projectCodeCellNo).getStringCellValue().trim());
							//System.out.println("projectCode => "+row.getCell(projectCodeCellNo).getStringCellValue().trim());
							//System.out.println("projectCodeMstr => "+projectCodeMstr);
							consolidatedDeliveryRisk.setProjectCodeId(projectCodeMstr.getId());							
						}
						consolidatedDeliveryRiskListReadFromExcel.add(consolidatedDeliveryRisk);
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
		return consolidatedDeliveryRiskListReadFromExcel;
	}

	@Transactional
	@Override
	public List<ConsolidatedDeliveryRisk> saveExcel(List<ConsolidatedDeliveryRisk> consolidatedDeliveryRiskListReadFromExcel,FileUploadPayload fileUploadPayload)throws DataIntegrityViolationException, IOException, Exception{
		
		List<ConsolidatedDeliveryRisk> savedConsolidatedDeliveryRiskList = null;	
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
			List<ConsolidatedDeliveryRisk> updatedConsolidatedDeliveryRiskReadFromExcel = updateFileReferenceOfAllRecordsInList(fileReference.getId(),consolidatedDeliveryRiskListReadFromExcel);			
			//Start file movement				
				List<ConsolidatedDeliveryRisk> readExistingConsolidatedDeliveryRiskList = monthlyConsolidatedDeliveryRiskRepo.getAllByMonthAndYear(fileUploadPayload.getFileUploadDate());	
				if(readExistingConsolidatedDeliveryRiskList.size()!=0) {	//Check whether file movement is required or not				
					savedConsolidatedDeliveryRiskList = moveFileFromMainToHistoryTable(readExistingConsolidatedDeliveryRiskList,updatedConsolidatedDeliveryRiskReadFromExcel);
			}//End of records availability check
			else {//When no record is found in the main table, then insert this first time uploaded excel
				savedConsolidatedDeliveryRiskList = monthlyConsolidatedDeliveryRiskRepo.saveAll(updatedConsolidatedDeliveryRiskReadFromExcel);	
			}
		}}catch(Exception ex) {
			logger.info("A new monthly-consolidated-file created at server has been deleted");
			fileStorageService.deleteFiles(fileStorageService.getTargetLocation());//If any exception is caught then the currently created file must be deleted.
		    throw ex;
		}
		return savedConsolidatedDeliveryRiskList;
	}

	
	private List<ConsolidatedDeliveryRisk> moveFileFromMainToHistoryTable(List<ConsolidatedDeliveryRisk> readExistingConsolidatedDeliveryRiskList,
			List<ConsolidatedDeliveryRisk> updatedConsolidatedDeliveryRiskReadFromExcel) {
		
		saveAllInHistoryTable(readExistingConsolidatedDeliveryRiskList);				
		monthlyConsolidatedDeliveryRiskRepo.saveAll(updatedConsolidatedDeliveryRiskReadFromExcel);		
		monthlyConsolidatedDeliveryRiskRepo.deleteAllInBatch(readExistingConsolidatedDeliveryRiskList);//Deleting from the main table
		return updatedConsolidatedDeliveryRiskReadFromExcel;
	}

	private void saveAllInHistoryTable(List<ConsolidatedDeliveryRisk> readExistingConsolidatedDeliveryRiskList) {
		
		List<HconsolidatedDeliveryRisk> preparedHconsolidatedDeliveryRiskList = new ArrayList<HconsolidatedDeliveryRisk>();
		
		HconsolidatedDeliveryRisk hconsolidatedDeliveryRisk;
		
		for(ConsolidatedDeliveryRisk consolidatedDeliveryRisk :readExistingConsolidatedDeliveryRiskList) {	
			
			hconsolidatedDeliveryRisk = new HconsolidatedDeliveryRisk();	
			
			hconsolidatedDeliveryRisk.setRiskType(consolidatedDeliveryRisk.getRiskType());
			hconsolidatedDeliveryRisk.setSource(consolidatedDeliveryRisk.getSource());			
			hconsolidatedDeliveryRisk.setRiskMainCategory(consolidatedDeliveryRisk.getRiskMainCategory());
			hconsolidatedDeliveryRisk.setRiskSubCategory(consolidatedDeliveryRisk.getRiskSubCategory());
			hconsolidatedDeliveryRisk.setOccuringProbablity(consolidatedDeliveryRisk.getOccuringProbablity());
			hconsolidatedDeliveryRisk.setImpact(consolidatedDeliveryRisk.getImpact());
			hconsolidatedDeliveryRisk.setRiskPriorityNumber(consolidatedDeliveryRisk.getRiskPriorityNumber());
			hconsolidatedDeliveryRisk.setRiskExposure(consolidatedDeliveryRisk.getRiskExposure());
			hconsolidatedDeliveryRisk.setResponseResolutionStartegy(consolidatedDeliveryRisk.getResponseResolutionStartegy());
			hconsolidatedDeliveryRisk.setRiskOwner(consolidatedDeliveryRisk.getRiskOwner());			
			hconsolidatedDeliveryRisk.setMitigationPlan(consolidatedDeliveryRisk.getMitigationPlan());
			hconsolidatedDeliveryRisk.setContigencyPlan(consolidatedDeliveryRisk.getContigencyPlan());
			hconsolidatedDeliveryRisk.setRiskStatus(consolidatedDeliveryRisk.getRiskStatus());
			hconsolidatedDeliveryRisk.setRemark(consolidatedDeliveryRisk.getRemark());	
			
			hconsolidatedDeliveryRisk.setProjectCodeId(consolidatedDeliveryRisk.getProjectCodeId());
			hconsolidatedDeliveryRisk.setFileUploadDate(consolidatedDeliveryRisk.getFileUploadDate());
						
			hconsolidatedDeliveryRisk.setCreatedBy(consolidatedDeliveryRisk.getCreatedBy());			   
			hconsolidatedDeliveryRisk.setCreatedOn(consolidatedDeliveryRisk.getCreatedOn());			   
			hconsolidatedDeliveryRisk.setModifiedBy(consolidatedDeliveryRisk.getModifiedBy());
			hconsolidatedDeliveryRisk.setModifiedOn(consolidatedDeliveryRisk.getModifiedOn());
			hconsolidatedDeliveryRisk.setActiveC(consolidatedDeliveryRisk.getActiveC());
			hconsolidatedDeliveryRisk.setFileReferenceId(consolidatedDeliveryRisk.getFileReferenceId());  
			   
			preparedHconsolidatedDeliveryRiskList.add(hconsolidatedDeliveryRisk);   
			}
		hconsolidatedDeliveryRiskRepo.saveAll(preparedHconsolidatedDeliveryRiskList);//Saving in history table	
		
	}

	private List<ConsolidatedDeliveryRisk> updateFileReferenceOfAllRecordsInList(Integer fileReferenceId,List<ConsolidatedDeliveryRisk> consolidatedDeliveryRiskListReadFromExcel) {
		for(ConsolidatedDeliveryRisk consolidatedDeliveryRisk:consolidatedDeliveryRiskListReadFromExcel) {
			consolidatedDeliveryRisk.setFileReferenceId(fileReferenceId);
		}		
		return consolidatedDeliveryRiskListReadFromExcel;
	}

	@Override
	public void generateExcel(HttpServletResponse response, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ConsolidatedDeliveryRisk> getMonthlyConsolidatedDeliveryRiskBetweenMonths(int projectCodeId,Date fFileUploadDate,Date tFileUploadDate) {
		List<ConsolidatedDeliveryRisk> monthlyConsolidatedDeliveryRiskList = 
				monthlyConsolidatedDeliveryRiskRepo.getMonthlyConsolidatedDeliveryRiskBetweenMonths(projectCodeId,fFileUploadDate,tFileUploadDate);
		return monthlyConsolidatedDeliveryRiskList;
	}
	
	
}//End of MonthlyConsolidatedDeliveryRiskServiceImpl
