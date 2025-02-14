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
import com.nus.pvt.master.entities.ProjectCodeMstr;
import com.nus.pvt.master.model.ProjectCodeMstrModel;
import com.nus.pvt.master.service.ProjectCodeMstrService;

import jakarta.persistence.PersistenceException;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:15:13 pm<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/mstr/v1")
public class ProjectCodeMstrController extends ApexBaseCtrl {

	@Autowired
	ProjectCodeMstrService projectCodeMstrService;

	@PostMapping(value = "/projectCodes",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> addProjectCodeMstr(@RequestBody ProjectCodeMstrModel projectCodeMstrModel) throws Exception {		
		ProjectCodeMstr projectCodeMstr = projectCodeMstrService.addProjectCodeMstr(projectCodeMstrModel);
		if(projectCodeMstr!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(projectCodeMstrModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(projectCodeMstr,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new PersistenceException("No ProjectCodeMstr is added.");
		}
	}

	@GetMapping(value = "/projectCodes",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAllProjectCodeMstrs() throws Exception {		
		List<ProjectCodeMstr> projectCodeMstrList = projectCodeMstrService.getAllProjectCodeMstrs();
		if(projectCodeMstrList!=null) {
			apiReq=makeApiMetaData();			
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(projectCodeMstrList,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {			
			throw new ResourceNotFoundException("No ProjectCodeMstr is yet defined!");
		}
	}

	@GetMapping(value = "/projectCodes/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getProjectCodeMstrById(@PathVariable("id") Integer id) throws Exception {
		ProjectCodeMstr projectCodeMstr = projectCodeMstrService.getProjectCodeMstrByRecordId(id);
		if(projectCodeMstr!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(projectCodeMstr,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ProjectCodeMstr with id("+id+") is found.");
		} 
	}

	@DeleteMapping(value = "/projectCodes/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> deleteProjectCodeMstrById(@PathVariable("id") Integer id) throws Exception {
		boolean isDeleted = projectCodeMstrService.deleteProjectCodeMstrByRecordId(id);
		if(isDeleted) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(isDeleted,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ProjectCodeMstr with id("+id+") is found.");
		}
	}

	@PutMapping(value = "/projectCodes",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> updateProjectCodeMstr(@RequestBody ProjectCodeMstrModel projectCodeMstrModel) throws Exception {		
		ProjectCodeMstr projectCodeMstr = projectCodeMstrService.updateProjectCodeMstr(projectCodeMstrModel);
		if(projectCodeMstr!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(projectCodeMstrModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(projectCodeMstr,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ProjectCodeMstr with id("+projectCodeMstrModel.getId()+") is found.");
		} 
	}	
}
