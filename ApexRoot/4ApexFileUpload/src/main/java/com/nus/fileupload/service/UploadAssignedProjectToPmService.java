package com.nus.fileupload.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nus.exception.DataIntegrityViolationException;
import com.nus.pvt.admin.entities.AssignProjectToPm;
import com.nus.pvt.admin.model.AssignProjectToPmModel;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 08-Feb-2025<br>
 * @Time: 12:09:46 am<br>
 * @Objective: <br>
 */
public interface UploadAssignedProjectToPmService {
	//These methods are temporary for uploading project assignment to PM. It will be removed once the front will be developed.
		//In fact, these assignment will be made from front-end only.
		public List<AssignProjectToPm> readExcel(MultipartFile file, String date) throws IOException, ParseException;		
		public List<AssignProjectToPmModel> saveExcel(List<AssignProjectToPm> assignProjectToPmListReadFromExcel)throws DataIntegrityViolationException, IOException, Exception;
		public void generateExcel(HttpServletResponse response, String fileName)throws Exception;
}
