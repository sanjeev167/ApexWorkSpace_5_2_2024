package com.nus.fileupload.service;

import java.io.IOException;
import java.util.List;

import com.nus.exception.DataIntegrityViolationException;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.pvt.master.entities.ClientAccountMstr;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 28-Jan-2025<br>
 * @Time: 11:06:12 am<br>
 * @Objective: <br>
 */
public interface ClientAccountMstrUploadService {

    public List<ClientAccountMstr> readExcel(FileUploadPayload fileUploadPayload) throws IOException;
	
	public List<ClientAccountMstr> saveExcel(List<ClientAccountMstr> clientAccountMstrUploadList)throws DataIntegrityViolationException;
	
	public void generateExcel(HttpServletResponse response, String fileName)throws Exception;
}
