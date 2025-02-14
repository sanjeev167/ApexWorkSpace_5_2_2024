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
import com.nus.sec.entities.ApexRole;
import com.nus.sec.model.ApexRoleModel;
import com.nus.sec.service.ApexRoleService;

import jakarta.persistence.PersistenceException;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 09-Jan-2025<br>
 * @Time: 7:55:53 am<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/sec/v1")
public class ApexRoleController extends ApexBaseCtrl{

	@Autowired
	private ApexRoleService apexRoleService;

	@PostMapping(value = "/roles",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> addApexRole(@RequestBody ApexRoleModel apexRoleModel) throws Exception {		
		ApexRole apexRole = apexRoleService.addApexRole(apexRoleModel);
		if(apexRole!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(apexRoleModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexRole,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new PersistenceException("No ApexRole is added.");
		}
	}

	@GetMapping(value = "/roles",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAllApexRoles() throws Exception {		
		List<ApexRole> apexRoleList = apexRoleService.getAllApexRoles();
		if(apexRoleList!=null) {
			apiReq=makeApiMetaData();			
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexRoleList,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {			
			throw new ResourceNotFoundException("No ApexRole is yet defined!");
		}
	}

	@GetMapping(value = "/roles/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getApexRoleById(@PathVariable("id") Integer id) throws Exception {
		ApexRole apexRole = apexRoleService.getApexRoleByRecordId(id);
		if(apexRole!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexRole,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ApexRole with id("+id+") is found.");
		} 
	}

	@DeleteMapping(value = "/roles/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> deleteApexRoleById(@PathVariable("id") Integer id) throws Exception {
		boolean isDeleted = apexRoleService.deleteApexRoleByRecordId(id);
		if(isDeleted) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(isDeleted,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ApexRole with id("+id+") is found.");
		}
	}

	@PutMapping(value = "/roles",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> updateApexRole(@RequestBody ApexRoleModel apexRoleModel) throws Exception {		
		ApexRole apexRole = apexRoleService.updateApexRole(apexRoleModel);
		if(apexRole!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(apexRoleModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(apexRole,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ApexRole with id("+apexRoleModel.getId()+") is found.");
		} 
	}

}// End of ApexRoleController
