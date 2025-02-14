package com.nus.fileupload.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.fileupload.entities.FileReference;
import com.nus.fileupload.model.FileUploadPayload;
import com.nus.fileupload.repo.FileReferenceRepo;
import com.nus.util.Utility;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 30-Jan-2025<br>
 * @Time: 5:07:36 am<br>
 * @Objective: <br>
 */
@Service
public class FileStorageService extends UserLoginBaseService{
	
	@Autowired
	FileReferenceRepo fileReferenceRepo;

	private Path fileStorageLocation;
	private Path targetLocation;
	private String fileStorageBaseLocation;

	public FileStorageService(Environment env) {
		this.fileStorageBaseLocation= env.getProperty("apex.fileServerLocation", "./uploads/files");
		
	}
	
	public void createDirectories(FileUploadPayload fileUploadPayload) {		
		String fileUploadDate[]=dateFormatter.format(fileUploadPayload.getFileUploadDate()).split("-");
		this.fileStorageLocation = Paths.get(fileStorageBaseLocation+"/"+ fileUploadPayload.getFileType()
		                                                            +"/"+ fileUploadDate[1]
		                                                            +"/"+ fileUploadDate[2])
				                                                    .toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
		}	
	}

	
	private String getFileExtension(String fileName) {
		if (fileName == null) {
			return null;
		}
		String[] fileNameParts = fileName.split("\\.");

		return fileNameParts[fileNameParts.length - 1];
	}

	public String storeFile(FileUploadPayload fileUploadPayload) {		
		// Normalize file name
		String fileName = (new Utility()).formattedDateTime + "-"+fileUploadPayload.getFile().getOriginalFilename();

		try {
			// Check if the filename contains invalid characters
			if (fileName.contains("..")) {
				throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
			}
            createDirectories(fileUploadPayload);			
			targetLocation = this.fileStorageLocation.resolve(fileName);			
			Files.copy(fileUploadPayload.getFile().getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return fileName;
		} catch (IOException ex) {
			throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}
	
	
	public FileReference saveFileReference(FileUploadPayload fileUploadPayload) {
		
		FileReference fileReference =new FileReference();
		String fileUploadDate[]=dateFormatter.format(fileUploadPayload.getFileUploadDate()).split("-");
		fileReference.setFileMonth(Integer.parseInt(fileUploadDate[1]));
		fileReference.setFileYear(Integer.parseInt(fileUploadDate[2]));		
		fileReference.setCreatedBy(fileUploadPayload.getUserId());
		fileReference.setFileTypeId(fileUploadPayload.getFileTypeId());
		fileReference.setFileName(fileUploadPayload.getFileName());
		fileReference.setFilePath(fileUploadPayload.getFileServerPath().toString());	
		fileReference.setCreatedBy(getCurrentLoginUserId());
		fileReference.setCreatedOn(currentDate);
		fileReference.setActiveC("Y");		
		return fileReferenceRepo.save(fileReference);	
	}
	
	public void deleteFiles( Path fileReferencePath) throws IOException {		
		Files.delete(fileReferencePath);		
	}
	
	/**
	 * @return the targetLocation
	 */
	public Path getTargetLocation() {
		return targetLocation;
	}

	/**
	 * @param targetLocation the targetLocation to set
	 */
	public void setTargetLocation(Path targetLocation) {
		this.targetLocation = targetLocation;
	}

	/**
	 * @return the fileStorageLocation
	 */
	public Path getFileStorageLocation() {
		return fileStorageLocation;
	}
	
	
}
