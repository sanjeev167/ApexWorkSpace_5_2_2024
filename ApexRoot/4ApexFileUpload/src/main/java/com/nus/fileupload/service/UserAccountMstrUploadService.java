package com.nus.fileupload.service;

import java.io.IOException;
import java.util.List;

import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.sec.entities.ApexUser;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 06-Feb-2025<br>
 * @Time: 6:30:58 am<br>
 * @Objective: <br>
 */
public interface UserAccountMstrUploadService {

    public List<ApexUser> readExcel(FileUploadPayload fileUploadPayload) throws IOException;
	
	public List<ApexUser> saveExcel(List<ApexUser> usertAccountMstrUploadList)throws DataIntegrityViolationException;
	
	public void generateExcel(HttpServletResponse response, String fileName)throws Exception;
}
