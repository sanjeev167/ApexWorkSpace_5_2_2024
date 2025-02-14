package com.nus.pvt.master.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nus.base.ctrl.ApexBaseCtrl;
import com.nus.exception.ResourceNotFoundException;
import com.nus.fileupload.entities.FileType;
import com.nus.pvt.master.model.FileTypeModel;
import com.nus.pvt.master.service.FileTypeService;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 30-Jan-2025<br>
 * @Time: 12:31:12 pm<br>
 * @Objective: <br>
 */
@Validated
@RestController
@RequestMapping("/mstr/v1")
public class FileTypeCtrl extends ApexBaseCtrl{
	
	@Autowired
	FileTypeService fileTypeService;
	
	@PostMapping(value = "/fileTypes",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> addFileType(@RequestBody FileTypeModel fileTypeModel) throws Exception {		
		FileType fileType = fileTypeService.addFileType(fileTypeModel);
		if(fileType!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(fileTypeModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(fileType,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No FileType is added.");
		}
	}

	@GetMapping(value = "/fileTypes",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAllfileTypes() throws Exception {		
		List<FileType> fileTypeList = fileTypeService.getAllFileTypes();
		if(fileTypeList!=null) {
			apiReq=makeApiMetaData();			
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(fileTypeList,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {			
			throw new ResourceNotFoundException("No FileType is yet defined!");
		}
	}

	@GetMapping(value = "/fileTypes/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getFileTypeById(@PathVariable("id") Integer id) throws Exception {
		FileType fileType = fileTypeService.getFileTypeByRecordId(id);
		if(fileType!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(fileType,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No FileType with id("+id+") is found.");
		} 
	}

	@DeleteMapping(value = "/fileTypes/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> deleteFileTypeById(@PathVariable("id") Integer id) throws Exception {
		boolean isDeleted = fileTypeService.deleteFileTypeByRecordId(id);
		if(isDeleted) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(isDeleted,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No FileType with id("+id+") is found.");
		}
	}

	@PutMapping(value = "/fileTypes",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> updateFileType(@RequestBody FileTypeModel fileTypeModel) throws Exception {		
		FileType fileType = fileTypeService.updateFileType(fileTypeModel);
		if(fileType!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(fileTypeModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(fileType,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No FileType with id("+fileTypeModel.getId()+") is found.");
		} 
	}
	
}
