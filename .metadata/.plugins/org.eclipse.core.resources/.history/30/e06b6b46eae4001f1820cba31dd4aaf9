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
import com.nus.pvt.master.entities.ClientAccountMstr;
import com.nus.pvt.master.model.ClientAccountMstrModel;
import com.nus.pvt.master.service.ClientAccountMstrService;

import jakarta.persistence.PersistenceException;


/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:18:21 pm<br>
 * @Objective: <br>
 */
@RestController
@RequestMapping("/mstr/v1")
public class ClientAccountMstrController extends ApexBaseCtrl {
	@Autowired
	ClientAccountMstrService clientAccountMstrService;
	
	@PostMapping(value = "/clientAccounts",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> addClientAccountMstr(@RequestBody ClientAccountMstrModel clientAccountMstrModel) throws Exception {		
		ClientAccountMstr clientAccountMstr = clientAccountMstrService.addClientAccountMstr(clientAccountMstrModel);
		if(clientAccountMstr!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(clientAccountMstrModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(clientAccountMstr,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new PersistenceException("No ClientAccountMstr is added.");
		}
	}

	@GetMapping(value = "/clientAccounts",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getAllClientAccountMstrs() throws Exception {		
		List<ClientAccountMstr> clientAccountMstrList = clientAccountMstrService.getAllClientAccountMstrs();
		if(clientAccountMstrList!=null) {
			apiReq=makeApiMetaData();			
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(clientAccountMstrList,apiReq);		
			return ResponseEntity.ok().body(apiResponse);
		}else {			
			throw new ResourceNotFoundException("No ClientAccountMstr is yet defined!");
		}
	}

	@GetMapping(value = "/clientAccounts/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> getClientAccountMstrById(@PathVariable("id") Integer id) throws Exception {
		ClientAccountMstr clientAccountMstr = clientAccountMstrService.getClientAccountMstrByRecordId(id);
		if(clientAccountMstr!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(clientAccountMstr,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ClientAccountMstr with id("+id+") is found.");
		} 
	}

	@DeleteMapping(value = "/clientAccounts/{id}",produces="application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> deleteClientAccountMstrById(@PathVariable("id") Integer id) throws Exception {
		boolean isDeleted = clientAccountMstrService.deleteClientAccountMstrByRecordId(id);
		if(isDeleted) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(id);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(true,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ClientAccountMstr with id("+id+") is found.");
		}
	}

	@PutMapping(value = "/clientAccounts",produces="application/json",consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
	public ResponseEntity<Object> updateClientAccountMstr(@RequestBody ClientAccountMstrModel clientAccountMstrModel) throws Exception {		
		ClientAccountMstr clientAccountMstr = clientAccountMstrService.updateClientAccountMstr(clientAccountMstrModel);
		if(clientAccountMstr!=null) {
			apiReq=makeApiMetaData();
			apiReq.setPayLoad(clientAccountMstrModel);
			//Return response in a pre-defined format	       	
			apiResponse=makeSuccessResponse(clientAccountMstr,apiReq);
			return ResponseEntity.ok().body(apiResponse);
		}else {
			throw new ResourceNotFoundException("No ClientAccountMstr with id("+clientAccountMstrModel.getId()+") is found.");
		} 
	}
	
	
}
