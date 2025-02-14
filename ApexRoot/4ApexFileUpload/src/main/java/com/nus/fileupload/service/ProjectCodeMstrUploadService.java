package com.nus.fileupload.service;

import java.io.IOException;
import java.util.List;

import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.pvt.master.entities.ProjectCodeMstr;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 27-Jan-2025<br>
 * @Time: 9:49:17 pm<br>
 * @Objective: <br>
 */

public interface ProjectCodeMstrUploadService {

    public List<ProjectCodeMstr> readExcel(FileUploadPayload fileUploadPayload) throws IOException;
	
	public List<ProjectCodeMstr> saveExcel(List<ProjectCodeMstr> projectMonthlyPlccList) throws DataIntegrityViolationException;
	
	public void generateExcel(HttpServletResponse response, String fileName);
}
