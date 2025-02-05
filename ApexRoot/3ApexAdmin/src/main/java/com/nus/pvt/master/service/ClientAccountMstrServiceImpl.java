package com.nus.pvt.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.pvt.master.entities.ClientAccountMstr;
import com.nus.pvt.master.model.ClientAccountMstrModel;
import com.nus.pvt.master.repo.ClientAccountMstrRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:37:03 pm<br>
 * @Objective: <br>
 */
@Service
public class ClientAccountMstrServiceImpl extends UserLoginBaseService implements ClientAccountMstrService {

	@Autowired
	ClientAccountMstrRepo clientAccountMstrRepo;

	@Override
	public ClientAccountMstr addClientAccountMstr(ClientAccountMstrModel clientAccountMstrModel) throws Exception{
		ClientAccountMstr savedClientAccountMstrEntity = null;
		try {
			ClientAccountMstr clientAccountMstrEntity = new ClientAccountMstr();
			clientAccountMstrEntity.setClientAccount(clientAccountMstrModel.getClientAccount());
			clientAccountMstrEntity.setCreatedOn(currentDate);
			clientAccountMstrEntity.setCreatedBy(getCurrentLoginUserId());
			clientAccountMstrEntity.setActiveC("Y");
			savedClientAccountMstrEntity = clientAccountMstrRepo.save(clientAccountMstrEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedClientAccountMstrEntity;
	}

	@Override
	public ClientAccountMstr updateClientAccountMstr(ClientAccountMstrModel clientAccountMstrModel) throws Exception{
		ClientAccountMstr clientAccountMstrEntityToBeUpdated = null;
		try {
			Optional<ClientAccountMstr> clientAccountMstrEntityWrapper = clientAccountMstrRepo.findById(clientAccountMstrModel.getId());
			if (clientAccountMstrEntityWrapper.isPresent()) {
				clientAccountMstrEntityToBeUpdated = clientAccountMstrEntityWrapper.get();
				clientAccountMstrEntityToBeUpdated.setClientAccount(clientAccountMstrModel.getClientAccount());								
				clientAccountMstrEntityToBeUpdated.setModifiedOn(currentDate);
				clientAccountMstrEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());				
				clientAccountMstrRepo.save(clientAccountMstrEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return clientAccountMstrEntityToBeUpdated;
	}

	@Override
	public boolean deleteClientAccountMstrByRecordId(Integer recordId) throws Exception{
		boolean isRecordDeleted = true;
		try {
			clientAccountMstrRepo.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;
	}

	@Override
	public ClientAccountMstr getClientAccountMstrByRecordId(Integer recordId) throws Exception{
		ClientAccountMstr clientAccountMstrEntityFetched = null;
		try {
			Optional<ClientAccountMstr> clientAccountMstrEntityWrapper = clientAccountMstrRepo.findById(recordId);
			if (clientAccountMstrEntityWrapper.isPresent())
				clientAccountMstrEntityFetched = clientAccountMstrEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return clientAccountMstrEntityFetched;
	}

	@Override
	public List<ClientAccountMstr> getAllClientAccountMstrs() throws Exception{
		List<ClientAccountMstr> clientAccountMstrList = null;
		try {
			clientAccountMstrList = clientAccountMstrRepo.findAll();
		} catch (Exception ex) {
			throw ex;
		}
		return clientAccountMstrList;
	}
	
	
	
}
