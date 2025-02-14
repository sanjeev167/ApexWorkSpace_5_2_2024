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
import com.nus.sec.entities.ApexGroup;

import com.nus.sec.model.ApexGroupModel;
import com.nus.sec.service.ApexGroupService;

import jakarta.persistence.PersistenceException;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 09-Jan-2025<br>
 * @Time: 7:38:08 am<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/sec/v1")

public class ApexGroupController extends ApexBaseCtrl{
	@Autowired
	private ApexGroupService apexGroupService;

	@PostMapping(value = "/groups",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> addApexGroup(@RequestBody ApexGroupModel apexGroupModel) throws Exception {
		ApexGroup apexGroup = apexGroupService.addApexGroup(apexGroupModel);
		if(apexGroup!=null) {			
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(apexGroupModel);			
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexGroup,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new PersistenceException("No Apex-Group is added");
		}
	}

	@GetMapping(value = "/groups",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAllApexGroups() throws Exception {
		List<ApexGroup> apexGroupList = apexGroupService.getAllApexGroups();
		if (apexGroupList != null) {
			apiReq=makeApiMetaData();						
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexGroupList,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		} else {
			throw new ResourceNotFoundException("No ApexGroup is yet defined!");
		}
	}

	@GetMapping(value = "/groups/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getApexGroupById(@PathVariable("id") Integer id) throws Exception {
		ApexGroup apexGroup = apexGroupService.getApexGroupByRecordId(id);
		if (apexGroup != null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexGroup,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		} else {
			throw new ResourceNotFoundException("No ApexGroup with id ("+id+") is found.");
		}
	}

	@DeleteMapping(value = "/groups/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> deleteApexGroupById(@PathVariable("id") Integer id) throws Exception {
		boolean isDeleted = apexGroupService.deleteApexGroupByRecordId(id);
		if (isDeleted) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(isDeleted,apiReq);		
			return ResponseEntity.ok().body(apiResponse);			
		} else {
			throw new ResourceNotFoundException("No ApexGroup with id ("+id+") is found.");
		}
	}

	@PutMapping(value = "/groups",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> updateApexGroup(@RequestBody ApexGroupModel apexGroupModel) throws Exception {
		
		ApexGroup apexGroup = apexGroupService.updateApexGroup(apexGroupModel);
		if (apexGroup != null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(apexGroupModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexGroup,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		} else {			
			throw new ResourceNotFoundException("No ApexGroup with id("+apexGroupModel.getId()+") is found.");
		}
	}

}// End of ApexGroupController
