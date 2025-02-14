package com.nus.fileupload.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
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
import org.springframework.web.multipart.MultipartFile;

import com.nus.base.service.UserLoginBaseService;
import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.loader.DataLoader;
import com.nus.pvt.admin.entities.AssignProjectToPm;
import com.nus.pvt.admin.model.AssignProjectToPmModel;
import com.nus.pvt.admin.repo.AssignProjectToPmRepo;
import com.nus.pvt.master.entities.ClientAccountMstr;
import com.nus.pvt.master.entities.ProjectCodeMstr;
import com.nus.pvt.master.entities.ProjectTypeMstr;
import com.nus.pvt.master.entities.ProjectVerticalMstr;
import com.nus.sec.entities.ApexUser;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 08-Feb-2025<br>
 * @Time: 12:10:00 am<br>
 * @Objective: These have been written temporarily. Once the front-end is
 *             developed, it will be removed.<br>
 */
@Service
public class UploadAssignedProjectToPmServiceImpl extends UserLoginBaseService
		implements UploadAssignedProjectToPmService {
	private final Logger logger = LoggerFactory.getLogger(UploadAssignedProjectToPmServiceImpl.class);

	@Autowired
	DataLoader dataLoader;
	
	@Autowired
	AssignProjectToPmRepo assignProjectToPmRepo;
	
	List<AssignProjectToPmModel> assignProjectToPmModelList = new ArrayList<AssignProjectToPmModel>();

	@Override
	public List<AssignProjectToPm> readExcel(MultipartFile file, String dateStringComing) throws IOException, ParseException {
		
		int clientAccountCellValue = 0;// String => Checked
		int projectVerticalCellValue = 1;//String => Checked		
		int projectCodeCellNo = 2;//String => Checked
		int projectTypeCellNo = 4;//String => Checked
		int pmUserCellNo = 5;		
		
		Date assignedOn = dateFormatter.parse(dateStringComing);
		List<AssignProjectToPm> projectAssignedListReadFromExcel = new ArrayList<>();
		Workbook workbook = null;
		
		String fileName = file.getOriginalFilename();

		if (fileName.substring(fileName.length() - 5, fileName.length()).equals(".xlsx")) {
			InputStream excelInputStream = file.getInputStream();
			try {
				workbook = WorkbookFactory.create(excelInputStream);
				logger.info("Number of sheets: " + workbook.getNumberOfSheets());
				workbook.forEach(sheet -> {
					logger.info("Title of sheet => " + sheet.getSheetName());				       
					int index = 0; 
					ProjectCodeMstr projectCodeMstr; ClientAccountMstr clientAccountMstr;
					AssignProjectToPm assignProjectToPm; ProjectVerticalMstr projectVerticalMstr;
					ProjectTypeMstr projectTypeMstr; ApexUser apexUser; AssignProjectToPmModel assignProjectToPmModel;
					for (Row row : sheet) {// Now, start reading each cell one by one in a selected row.
						if (index++ == 0)
							continue;
						assignProjectToPmModel = new AssignProjectToPmModel();
						assignProjectToPmModel.setAssignedon(dateStringComing);
						
						assignProjectToPm = new AssignProjectToPm(getCurrentLoginUserId(),currentDate,activeC, assignedOn);
						
						if (row.getCell(clientAccountCellValue) != null && row.getCell(clientAccountCellValue).getCellType() == CellType.STRING) {
							clientAccountMstr =dataLoader.getClientAccountMstrMap().get(row.getCell(clientAccountCellValue).getStringCellValue().trim());	
							//System.out.println("Client Account =>"+row.getCell(clientAccountCellValue).getStringCellValue());							
							assignProjectToPmModel.setClientAccount(clientAccountMstr.getClientAccount());
							assignProjectToPm.setClientAccountId(clientAccountMstr.getId());
						}
				
						if (row.getCell(projectVerticalCellValue) != null && row.getCell(projectVerticalCellValue).getCellType() == CellType.STRING) {
							projectVerticalMstr =dataLoader.getProjectVerticalMstrMap().get(row.getCell(projectVerticalCellValue).getStringCellValue().trim());	
							//System.out.println("ProjectVertical =>"+row.getCell(projectVerticalCellValue).getStringCellValue());							
							assignProjectToPmModel.setProjectVertical(projectVerticalMstr.getVerticalName());
							assignProjectToPm.setProjectVerticalId(projectVerticalMstr.getId());
						}	
						
						if (row.getCell(projectCodeCellNo) != null && row.getCell(projectCodeCellNo).getCellType() == CellType.STRING) {
							projectCodeMstr =dataLoader.getProjectCodeMstrMap().get(row.getCell(projectCodeCellNo).getStringCellValue().trim());	
							//System.out.println("Project Code =>"+row.getCell(projectCodeCellNo).getStringCellValue());							
							assignProjectToPmModel.setProjectCode(projectCodeMstr.getProjectCode());
							assignProjectToPmModel.setProjectName(projectCodeMstr.getProjectName());
							assignProjectToPm.setProjectCodeId(projectCodeMstr.getId());
						}
						
						if (row.getCell(projectTypeCellNo) != null && row.getCell(projectTypeCellNo).getCellType() == CellType.STRING) {
							
							projectTypeMstr =dataLoader.getProjectTypeMstrMap().get(row.getCell(projectTypeCellNo).getStringCellValue().trim());	
							//System.out.println("Project Type =>"+row.getCell(projectTypeCellNo).getStringCellValue());							
							assignProjectToPmModel.setProjectType(projectTypeMstr.getProjectType());
							assignProjectToPm.setProjectTypeId(projectTypeMstr.getId());
						}
						
                       if (row.getCell(pmUserCellNo) != null && row.getCell(pmUserCellNo).getCellType() == CellType.STRING) {
                    	 
                    	   apexUser =dataLoader.getApexUserMap().get(row.getCell(pmUserCellNo).getStringCellValue().trim());	
							//System.out.println("Pm user =>"+row.getCell(pmUserCellNo).getStringCellValue());							
							assignProjectToPmModel.setProjectManagerName(apexUser.getName());
							assignProjectToPmModel.setEmpGrade(apexUser.getUserGrade().getUserGrade());
							assignProjectToPm.setPmUserId(apexUser.getId());
						}
                       assignProjectToPmModelList.add(assignProjectToPmModel);
						projectAssignedListReadFromExcel.add(assignProjectToPm);
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
		return projectAssignedListReadFromExcel;
	}

	@Override
	public List<AssignProjectToPmModel> saveExcel(List<AssignProjectToPm> assignProjectToPmListReadFromExcel)
			throws DataIntegrityViolationException, IOException, Exception {
		assignProjectToPmRepo.saveAll(assignProjectToPmListReadFromExcel);
		return assignProjectToPmModelList;
	}

	@Override
	public void generateExcel(HttpServletResponse response, String fileName) throws Exception {
		// TODO Auto-generated method stub

	}
}
