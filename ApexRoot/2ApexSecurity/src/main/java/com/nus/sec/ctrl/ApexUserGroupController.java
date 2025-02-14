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
import com.nus.sec.entities.ApexUserGroup;
import com.nus.sec.model.ApexUserGroupModel;
import com.nus.sec.service.ApexUserGroupService;

import jakarta.persistence.PersistenceException;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 09-Jan-2025<br>
 * @Time: 8:14:26 am<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/sec/v1")
public class ApexUserGroupController extends ApexBaseCtrl{
	@Autowired
	private ApexUserGroupService apexUserGroupService;

	@PostMapping(value = "/usergroups",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> addApexUserGroup(@RequestBody ApexUserGroupModel apexUserGroupModel) throws Exception {
		ApexUserGroup apexUserGroup = apexUserGroupService.addApexUserGroup(apexUserGroupModel);
		if(apexUserGroup!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(apexUserGroupModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexUserGroup,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new PersistenceException("No ApexUserGroup is added.");
		}
	}

	@GetMapping(value = "/usergroups/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getApexUserGroupById(@PathVariable("id") Integer id) throws Exception {
		ApexUserGroup apexUserGroup = apexUserGroupService.getApexUserGroupByRecordId(id);
		if(apexUserGroup!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexUserGroup,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ApexUserGroup with id("+id+") is found.");
		} 
	}

	@GetMapping(value = "/usergroups",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAllApexUserGroups() throws Exception {
		List<ApexUserGroup> apexUserGroupList = apexUserGroupService.getAllApexUserGroups();
		if(apexUserGroupList!=null) {
			apiReq=makeApiMetaData();			
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexUserGroupList,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {			
			throw new ResourceNotFoundException("No ApexUserGroup is yet defined!");
		} 
	}

	@DeleteMapping(value = "/usergroups/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> deleteApexUserGroupById(@PathVariable("id") Integer id) throws Exception {		
		boolean isDeleted = apexUserGroupService.deleteApexUserGroupByRecordId(id);
		if(isDeleted) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(isDeleted,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ApexUserGroup with id("+id+") is found.");
		}
	}

	@PutMapping(value = "/usergroups",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> updateApexUserGroup(@RequestBody ApexUserGroupModel apexUserGroupModel) throws Exception {		
		ApexUserGroup apexUserGroup = apexUserGroupService.updateApexUserGroup(apexUserGroupModel);
		if(apexUserGroup!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(apexUserGroupModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexUserGroup,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ApexUserGroup with id("+apexUserGroupModel.getId()+") is found.");
		}		 
	}

}// End of ApexUserGroupController

