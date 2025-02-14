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
import com.nus.fileupload.repo.ProjectCodeMstrUploadRepo;
import com.nus.pvt.master.entities.ProjectCodeMstr;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 27-Jan-2025<br>
 * @Time: 9:49:35 pm<br>
 * @Objective: <br>
 */
@Service
public class ProjectCodeMstrUploadServiceImpl extends UserLoginBaseService implements ProjectCodeMstrUploadService{

private final Logger logger = LoggerFactory.getLogger(ProjectMonthlyPlccServiceImpl.class);
	
	@Autowired
	ProjectCodeMstrUploadRepo projectCodeMstrUploadRepo;
	
	
	@Override
	public List<ProjectCodeMstr> readExcel(FileUploadPayload fileUploadPayload) throws IOException {		
		
		int projectCodeCellNo = 0;// String => Checked	
		int projectNameCellNo = 1;// String => Checked	

		List<ProjectCodeMstr> projectCodeMstrUploadList = new ArrayList<ProjectCodeMstr>();
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
					ProjectCodeMstr projectCodeMstr;				
					for (Row row : sheet) {// Now, start reading each cell one by one in a selected row.
						if (index++ == 0)
							continue;
						projectCodeMstr = new ProjectCodeMstr(getCurrentLoginUserId(),activeC );
						
						if (row.getCell(projectCodeCellNo) != null && row.getCell(projectCodeCellNo).getCellType() == CellType.STRING) {							
							projectCodeMstr.setProjectCode(row.getCell(projectCodeCellNo).getStringCellValue().trim());
						}
						
						if (row.getCell(projectNameCellNo) != null && row.getCell(projectNameCellNo).getCellType() == CellType.STRING) {
							projectCodeMstr.setProjectName(row.getCell(projectNameCellNo).getStringCellValue().trim());							
						}		
						projectCodeMstrUploadList.add(projectCodeMstr);
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
		return projectCodeMstrUploadList;
	}

	@Override
	public List<ProjectCodeMstr> saveExcel(List<ProjectCodeMstr> projectCodeMstrUploadList) throws DataIntegrityViolationException {
		List<ProjectCodeMstr> savedProjectCodeMstrUploadList = null;		
		savedProjectCodeMstrUploadList = projectCodeMstrUploadRepo.saveAll(projectCodeMstrUploadList);		
		return savedProjectCodeMstrUploadList;
	}

	@Override
	public void generateExcel(HttpServletResponse response, String fileName) {
		// TODO Auto-generated method stub
		
	}


}
