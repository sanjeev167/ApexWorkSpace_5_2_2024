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
import com.nus.pvt.master.model.UserGradeModel;
import com.nus.pvt.master.service.UserGradeService;
import com.nus.sec.entities.UserGrade;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:11:14 pm<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/mstr/v1")
public class UserGradeController extends ApexBaseCtrl {
	
	@Autowired
	UserGradeService userGradeService;
	
	@PostMapping(value = "/userGrades",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> addUserGrade(@RequestBody UserGradeModel userGradeModel) throws Exception {		
		UserGrade UserGrade = userGradeService.addUserGrade(userGradeModel);
		if(UserGrade!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(userGradeModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(UserGrade,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No UserGrade is added.");
		}
	}

	@GetMapping(value = "/userGrades",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAllUserGrade() throws Exception {		
		List<UserGrade> userGradeList = userGradeService.getAllUserGrades();
		if(userGradeList!=null) {
			apiReq=makeApiMetaData();			
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(userGradeList,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {			
			throw new ResourceNotFoundException("No UserGrade is yet defined!");
		}
	}

	@GetMapping(value = "/userGrades/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getUserGradeById(@PathVariable("id") Integer id) throws Exception {
		UserGrade userGrade = userGradeService.getUserGradeByRecordId(id);
		if(userGrade!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(userGrade,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No UserGrade with id("+id+") is found.");
		} 
	}

	@DeleteMapping(value = "/userGrades/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> deleteUserGradeById(@PathVariable("id") Integer id) throws Exception {
		boolean isDeleted = userGradeService.deleteUserGradeByRecordId(id);
		if(isDeleted) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(isDeleted,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No UserGrade with id("+id+") is found.");
		}
	}

	@PutMapping(value = "/userGrades",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> updateUserGrade(@RequestBody UserGradeModel userGradeModel) throws Exception {		
		UserGrade userGrade = userGradeService.updateUserGrade(userGradeModel);
		if(userGrade!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(userGradeModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(userGrade,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No UserGrade with id("+userGradeModel.getId()+") is found.");
		} 
	}
	
}
