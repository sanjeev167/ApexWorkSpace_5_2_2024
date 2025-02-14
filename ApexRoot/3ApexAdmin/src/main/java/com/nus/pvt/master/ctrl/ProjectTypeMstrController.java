package com.nus.pvt.master.ctrl;

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
import com.nus.pvt.master.entities.ProjectTypeMstr;
import com.nus.pvt.master.model.ProjectTypeMstrModel;
import com.nus.pvt.master.service.ProjectTypeMstrService;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:14:36 pm<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/mstr/v1")
public class ProjectTypeMstrController extends ApexBaseCtrl {

	@Autowired
	ProjectTypeMstrService projectTypeMstrService;

	@PostMapping(value = "/projectTypes",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> addProjectTypeMstr(@RequestBody ProjectTypeMstrModel projectTypeMstrModel) throws Exception {		
		ProjectTypeMstr projectTypeMstr = projectTypeMstrService.addProjectTypeMstr(projectTypeMstrModel);
		if(projectTypeMstr!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(projectTypeMstrModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(projectTypeMstr,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ProjectTypeMstr is added.");
		}
	}

	@GetMapping(value = "/projectTypes",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAllProjectTypes() throws Exception {		
		List<ProjectTypeMstr> projectTypeMstrList = projectTypeMstrService.getAllProjectTypeMstrs();
		if(projectTypeMstrList!=null) {
			apiReq=makeApiMetaData();			
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(projectTypeMstrList,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {			
			throw new ResourceNotFoundException("No ProjectTypeMstr is yet defined!");
		}
	}

	@GetMapping(value = "/projectTypes/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getProjectTypeMstrById(@PathVariable("id") Integer id) throws Exception {
		ProjectTypeMstr projectTypeMstr = projectTypeMstrService.getProjectTypeMstrByRecordId(id);
		if(projectTypeMstr!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(projectTypeMstr,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ProjectTypeMstr with id("+id+") is found.");
		} 
	}

	@DeleteMapping(value = "/projectTypes/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> deleteProjectTypeMstrById(@PathVariable("id") Integer id) throws Exception {
		boolean isDeleted = projectTypeMstrService.deleteProjectTypeMstrByRecordId(id);
		if(isDeleted) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(isDeleted,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ProjectTypeMstr with id("+id+") is found.");
		}
	}

	@PutMapping(value = "/projectTypes",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> updateProjectTypeMstr(@RequestBody ProjectTypeMstrModel projectTypeMstrModel) throws Exception {		
		ProjectTypeMstr projectTypeMstr = projectTypeMstrService.updateProjectTypeMstr(projectTypeMstrModel);
		if(projectTypeMstr!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(projectTypeMstrModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(projectTypeMstr,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ProjectTypeMstr with id("+projectTypeMstrModel.getId()+") is found.");
		} 
	}
	
}
