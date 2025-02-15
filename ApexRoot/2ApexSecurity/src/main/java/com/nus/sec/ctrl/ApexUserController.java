package com.nus.sec.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.nus.sec.entities.ApexUser;
import com.nus.sec.model.ApexUserModel;
import com.nus.sec.service.ApexUserService;

import jakarta.persistence.PersistenceException;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 09-Jan-2025<br>
 * @Time: 8:03:23 am<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/sec/v1")
public class ApexUserController extends ApexBaseCtrl{
	@Autowired
	private ApexUserService apexUserService;

	@PostMapping(value = "/users",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> addApexUser(@RequestBody ApexUserModel apexUserModel) throws Exception {
		ApexUser apexUser = apexUserService.addApexUser(apexUserModel);
		if(apexUser!=null) {
			apiReq=makeApiMetaData();			
			apiReq.setPayLoad(apexUserModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apiUser,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new PersistenceException("No ApexUser is added.");
		}
	}

	@GetMapping(value = "/users/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getApexUserById(@PathVariable("id") Integer id) throws Exception {
		ApexUser apexUser = apexUserService.getApexUserByRecordId(id);
		if(apexUser!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexUser,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ApexUser with id("+id+") is found.");
		}
	}

	@GetMapping(value = "/users",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAllApexUsers() throws Exception {
		List<ApexUser> apexUserList = apexUserService.getAllApexUsers();
		if(apexUserList!=null) {
			apiReq=makeApiMetaData();			
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexUserList,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {			
			throw new ResourceNotFoundException("No ApexUser is yet defined!");
		}		 
	}

	@DeleteMapping(value = "/users/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> deleteApexUserById(@PathVariable("id") Integer id) throws Exception {
		boolean isDeleted = apexUserService.deleteApexUserByRecordId(id);
		if(isDeleted) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(isDeleted,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ApiUser with id("+id+") is found.");
		}
	}

	@PutMapping(value = "/users",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> updateApexUser(@RequestBody ApexUserModel apexUserModel) throws Exception {
		
		ApexUser apexUser = apexUserService.updateApexUser(apexUserModel);
		if(apexUser!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(apexUserModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apiUser,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ApexUser with id("+apexUserModel.getId()+") is found.");
		}		 
	}

}// End of ApexUserController

