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
import com.nus.sec.entities.ApexGroupRole;
import com.nus.sec.model.ApexGroupRoleModel;
import com.nus.sec.service.ApexGroupRoleService;

import jakarta.persistence.PersistenceException;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 09-Jan-2025<br>
 * @Time: 7:48:10 am<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/sec/v1")
public class ApexGroupRoleController extends ApexBaseCtrl{
	@Autowired
	private ApexGroupRoleService apexGroupRoleService;

	@PostMapping(value = "/grouproles",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> addApexGroupRole(@RequestBody ApexGroupRoleModel apexGroupRoleModel) throws Exception {
		ApexGroupRole apexGroupRole = apexGroupRoleService.addApexGroupRole(apexGroupRoleModel);
		if(apexGroupRole!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(apexGroupRoleModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexGroupRole,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new PersistenceException("No ApexGroupRole is added");
		}		
	}

	@GetMapping(value = "/grouproles",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAllApexGroupRoles() throws Exception {
		List<ApexGroupRole> apexGroupRoleList = apexGroupRoleService.getAllApexGroupRoles();
		if(apexGroupRoleList!=null) {
			apiReq=makeApiMetaData();			
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexGroupRoleList,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ApexGroupRole is yet defined!");
		}		
		
	}

	@GetMapping(value = "/grouproles/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getApexGroupRoleById(@PathVariable("id") Integer id) throws Exception {
		ApexGroupRole apexGroupRole = apexGroupRoleService.getApexGroupRoleByRecordId(id);
		if(apexGroupRole!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexGroupRole,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ApexGroupRole with id ("+id+") is found.");
		}
	}

	@DeleteMapping(value = "/grouproles/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> deleteApexGroupRoleById(@PathVariable("id") Integer id) throws Exception {
		boolean isDeleted = apexGroupRoleService.deleteApexGroupRoleByRecordId(id);
		if(isDeleted) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(isDeleted,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ApexGroupRole with id ("+id+") is found.");
		}
	}

	@PutMapping(value = "/grouproles",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> updateApexGroupRole(@RequestBody ApexGroupRoleModel apexGroupRoleModel) throws Exception {
		ApexGroupRole apexGroupRole = apexGroupRoleService.updateApexGroupRole(apexGroupRoleModel);
		if(apexGroupRole!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(apexGroupRoleModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexGroupRole,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {			
			throw new ResourceNotFoundException("No ApexGroupRole with id("+apexGroupRoleModel.getId()+") is found.");
		}		
	}
}// End of ApexGroupRoleController

