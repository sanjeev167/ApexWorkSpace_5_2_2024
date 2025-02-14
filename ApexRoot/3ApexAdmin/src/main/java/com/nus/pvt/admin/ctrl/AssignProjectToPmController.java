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
import com.nus.pvt.admin.entities.AssignProjectToPm;
import com.nus.pvt.admin.model.AssignProjectToPmModel;
import com.nus.pvt.admin.service.AssignProjectToPmService;
import jakarta.persistence.PersistenceException;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:19:03 pm<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/assignProjects/v1")
public class AssignProjectToPmController extends ApexBaseCtrl {
	
	@Autowired
	AssignProjectToPmService assignProjectToPmService;

	@PostMapping(value = "/toPms",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> addAssignProjectToPm(@RequestBody AssignProjectToPmModel assignProjectToPmModel) throws Exception {		
		AssignProjectToPm assignProjectToPm = assignProjectToPmService.addAssignProjectToPm(assignProjectToPmModel);
		if(assignProjectToPm!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(assignProjectToPmModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(assignProjectToPm,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new PersistenceException("No AssignProjectToPm is added.");
		}
	}

	@GetMapping(value = "/toPms",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAllAssignProjectToPms() throws Exception {		
		List<AssignProjectToPm> assignProjectToPmList = assignProjectToPmService.getAllAssignProjectToPms();
		if(assignProjectToPmList!=null) {
			apiReq=makeApiMetaData();			
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(assignProjectToPmList,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {			
			throw new ResourceNotFoundException("No AssignProjectToPm is yet defined!");
		}
	}

	@GetMapping(value = "/toPms/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAssignProjectToPmById(@PathVariable("id") Integer id) throws Exception {
		AssignProjectToPm assignProjectToPm = assignProjectToPmService.getAssignProjectToPmByRecordId(id);
		if(assignProjectToPm!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(assignProjectToPm,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No AssignProjectToPm with id("+id+") is found.");
		} 
	}

	@DeleteMapping(value = "/toPms/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> deleteAssignProjectToPmById(@PathVariable("id") Integer id) throws Exception {
		boolean isDeleted = assignProjectToPmService.deleteAssignProjectToPmByRecordId(id);
		if(isDeleted) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(isDeleted,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No AssignProjectToPm with id("+id+") is found.");
		}
	}

	@PutMapping(value = "/toPms",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> updateAssignProjectToPm(@RequestBody AssignProjectToPmModel assignProjectToPmModel) throws Exception {		
		AssignProjectToPm assignProjectToPm = assignProjectToPmService.updateAssignProjectToPm(assignProjectToPmModel);
		if(assignProjectToPm!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(assignProjectToPmModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(assignProjectToPm,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No AssignProjectToPm with id("+assignProjectToPmModel.getId()+") is found.");
		} 
	}		
}//End of AssignProjectToPmController
