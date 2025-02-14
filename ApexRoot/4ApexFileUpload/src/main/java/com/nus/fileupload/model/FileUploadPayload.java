
package com.nus.fileupload.model;

import java.nio.file.Path;
import java.util.Date;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nus.sec.service.CustomUserDetails;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 25-Jan-2025<br>
 * @Time: 5:52:37 pm<br>
 * @Objective: <br>
 */
public class FileUploadPayload {	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date    fileUploadDate ;
	@JsonIgnore
	private MultipartFile file;	
	@JsonIgnore
	private int userId;
	private String userName;
	private String loginId;
	@JsonIgnore
	private int fileTypeId;
	private String fileType;
	private String fileName;
	private Path fileServerPath;
	private int userTypeId;
	
	public FileUploadPayload(Date fileUploadDate, MultipartFile file) {
		this.fileUploadDate = fileUploadDate;		
		this.file = file;
		this.userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		this.userName = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsernameInDb();
		this.loginId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
	}
	public FileUploadPayload(MultipartFile file) {
		this.file = file;
		this.userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		this.userName = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsernameInDb();
		this.loginId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
	}
	public FileUploadPayload(MultipartFile file,int userTypeId) {
		this.file = file;
		this.userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		this.userName = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsernameInDb();
		this.loginId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
	    this.userTypeId = userTypeId;
	}
	public FileUploadPayload(Date fileUploadDate, MultipartFile file, String fileName, int fileTypeId) {
		this.fileUploadDate = fileUploadDate;		
		this.file = file;
		this.userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		this.fileName = fileName;
		this.fileTypeId = fileTypeId;
		this.userName = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsernameInDb();
		this.loginId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
		
	
	}
	
	public Date getFileUploadDate() {
		return fileUploadDate;
	}
	public void setFileUploadDate(Date fileUploadDate) {
		this.fileUploadDate = fileUploadDate;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public int getFileTypeId() {
		return fileTypeId;
	}
	public void setFileTypeId(int fileTypeId) {
		this.fileTypeId = fileTypeId;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Path getFileServerPath() {
		return fileServerPath;
	}
	public void setFileServerPath(Path fileServerPath) {
		this.fileServerPath = fileServerPath;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}	
}
