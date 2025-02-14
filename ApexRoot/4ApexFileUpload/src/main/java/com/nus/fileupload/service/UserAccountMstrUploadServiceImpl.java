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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.loader.DataLoader;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.fileupload.repo.UserAccountMstrUploadRepo;
import com.nus.sec.entities.ApexUser;
import com.nus.sec.entities.UserGrade;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 06-Feb-2025<br>
 * @Time: 6:31:12 am<br>
 * @Objective: <br>
 */
@Service
public class UserAccountMstrUploadServiceImpl extends UserLoginBaseService implements UserAccountMstrUploadService {

	private final Logger logger = LoggerFactory.getLogger(UserAccountMstrUploadServiceImpl.class);

	@Autowired
	UserAccountMstrUploadRepo userAccountMstrUploadRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired 
    DataLoader dataLoader;

	@Override
	public List<ApexUser> readExcel(FileUploadPayload fileUploadPayload) throws IOException {
		
		int pmNameCellNo=0;// String => Checked	
		int empGradeCellNo =3;// String => Checked	
		int emailCellNo = 1; // String => Checked		

		List<ApexUser> apexUserList = new ArrayList<ApexUser>();
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
					UserGrade userGrade;
					ApexUser apexUser;				
					for (Row row : sheet) {// Now, start reading each cell one by one in a selected row.
						if (index++ == 0)
							continue;
						apexUser = new ApexUser(getCurrentLoginUserId(),encoder.encode("pass"),activeC );
							
						if (row.getCell(pmNameCellNo) != null && row.getCell(pmNameCellNo).getCellType() == CellType.STRING) {
							//System.out.println("PM Name = "+row.getCell(pmNameCellNo).getStringCellValue().trim());
							apexUser.setName(row.getCell(pmNameCellNo).getStringCellValue().trim());							
						}						
						if (row.getCell(emailCellNo) != null && row.getCell(emailCellNo).getCellType() == CellType.STRING) {
							apexUser.setEmail(row.getCell(emailCellNo).getStringCellValue());
						}				
						
						if (row.getCell(empGradeCellNo) != null && row.getCell(empGradeCellNo).getCellType() == CellType.STRING) {
							userGrade =dataLoader.getUserGradeMap().get(row.getCell(empGradeCellNo).getStringCellValue().trim());	
							System.out.println("User Grade =>"+row.getCell(empGradeCellNo).getStringCellValue());							
							apexUser.setUserGradeId(userGrade.getId());
						}	
									
						apexUserList.add(apexUser);
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
		return apexUserList;
	}

	@Override
	public List<ApexUser> saveExcel(List<ApexUser> usertAccountMstrUploadList) throws DataIntegrityViolationException {
		return userAccountMstrUploadRepo.saveAll(usertAccountMstrUploadList);		
	}

	@Override
	public void generateExcel(HttpServletResponse response, String fileName) throws Exception {
		// TODO Auto-generated method stub

	}

}
