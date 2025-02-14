package com.nus.pvt.admin.ctrl;

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
import com.nus.pvt.admin.entities.AssignPmProjectToDm;
import com.nus.pvt.admin.model.AssignPmProjectToDmModel;
import com.nus.pvt.admin.service.AssignPmProjectToDmService;

import jakarta.persistence.PersistenceException;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:20:52 pm<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/assignProjects/v1")
public class AssignPmProjectToDmController extends ApexBaseCtrl {

	@Autowired
	AssignPmProjectToDmService assignPmProjectToDmService;
	
	@PostMapping(value = "/toDms",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> addAssignPmProjectToDm(@RequestBody AssignPmProjectToDmModel assignPmProjectToDmModel) throws Exception {		
		AssignPmProjectToDm assignPmProjectToDm = assignPmProjectToDmService.addAssignPmProjectToDm(assignPmProjectToDmModel);
		if(assignPmProjectToDm!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(assignPmProjectToDmModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(assignPmProjectToDm,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new PersistenceException("No AssignPmProjectToDm is added.");
		}
	}

	@GetMapping(value = "/toDms",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAllAssignPmProjectToDms() throws Exception {		
		List<AssignPmProjectToDm> assignPmProjectToDmList = assignPmProjectToDmService.getAllAssignPmProjectToDms();
		if(assignPmProjectToDmList!=null) {
			apiReq=makeApiMetaData();			
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(assignPmProjectToDmList,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {			
			throw new ResourceNotFoundException("No AssignPmProjectToDm is yet defined!");
		}
	}

	@GetMapping(value = "/toDms/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAssignPmProjectToDmById(@PathVariable("id") Integer id) throws Exception {
		AssignPmProjectToDm assignPmProjectToDm = assignPmProjectToDmService.getAssignPmProjectToDmByRecordId(id);
		if(assignPmProjectToDm!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(assignPmProjectToDm,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No AssignPmProjectToDm with id("+id+") is found.");
		} 
	}

	@DeleteMapping(value = "/toDms/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> deleteAssignPmProjectToDmById(@PathVariable("id") Integer id) throws Exception {
		boolean isDeleted = assignPmProjectToDmService.deleteAssignPmProjectToDmByRecordId(id);
		if(isDeleted) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(isDeleted,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No AssignPmProjectToDm with id("+id+") is found.");
		}
	}

	@PutMapping(value = "/toDms",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> updateAssignPmProjectToDm(@RequestBody AssignPmProjectToDmModel assignPmProjectToDmModel) throws Exception {		
		AssignPmProjectToDm assignPmProjectToDm = assignPmProjectToDmService.updateAssignPmProjectToDm(assignPmProjectToDmModel);
		if(assignPmProjectToDm!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(assignPmProjectToDmModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(assignPmProjectToDm,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No AssignPmProjectToDm with id("+assignPmProjectToDmModel.getId()+") is found.");
		} 
	}		
}
