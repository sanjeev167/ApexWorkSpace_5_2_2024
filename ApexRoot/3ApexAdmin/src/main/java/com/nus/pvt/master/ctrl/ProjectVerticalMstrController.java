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
import com.nus.pvt.master.entities.ProjectVerticalMstr;
import com.nus.pvt.master.model.ProjectVerticalMstrModel;
import com.nus.pvt.master.service.ProjectVerticalMstrService;
/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:13:57 pm<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/mstr/v1")
public class ProjectVerticalMstrController extends ApexBaseCtrl {

	
	@Autowired
	ProjectVerticalMstrService projectVerticalMstrService;
    
	@PostMapping(value = "/verticals",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> addProjectVerticalMstr(@RequestBody ProjectVerticalMstrModel projectVerticalMstrModel) throws Exception {		
		ProjectVerticalMstr projectVerticalMstr = projectVerticalMstrService.addProjectVerticalMstr(projectVerticalMstrModel);
		if(projectVerticalMstr!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(projectVerticalMstrModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(projectVerticalMstr,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ProjectVerticalMstr is added.");
		}
	}

	@GetMapping(value = "/verticals",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAllProjectVerticalMstrs() throws Exception {		
		List<ProjectVerticalMstr> projectVerticalMstrList = projectVerticalMstrService.getAllProjectVerticalMstrs();
		if(projectVerticalMstrList!=null) {
			apiReq=makeApiMetaData();			
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(projectVerticalMstrList,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {			
			throw new ResourceNotFoundException("No ProjectVerticalMstr is yet defined!");
		}
	}

	@GetMapping(value = "/verticals/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getProjectVerticalMstrById(@PathVariable("id") Integer id) throws Exception {
		ProjectVerticalMstr projectVerticalMstr = projectVerticalMstrService.getProjectVerticalMstrByRecordId(id);
		if(projectVerticalMstr!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(projectVerticalMstr,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ProjectVerticalMstr with id("+id+") is found.");
		} 
	}

	@DeleteMapping(value = "/verticals/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> deleteProjectVerticalMstrById(@PathVariable("id") Integer id) throws Exception {
		boolean isDeleted = projectVerticalMstrService.deleteProjectVerticalMstrByRecordId(id);
		if(isDeleted) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(isDeleted,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ProjectVerticalMstr with id("+id+") is found.");
		}
	}

	@PutMapping(value = "/verticals",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> updateProjectVerticalMstr(@RequestBody ProjectVerticalMstrModel projectVerticalMstrModel) throws Exception {		
		ProjectVerticalMstr projectVerticalMstr = projectVerticalMstrService.updateProjectVerticalMstr(projectVerticalMstrModel);
		if(projectVerticalMstr!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(projectVerticalMstrModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(projectVerticalMstr,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ProjectVerticalMstr with id("+projectVerticalMstrModel.getId()+") is found.");
		} 
	}	
}
