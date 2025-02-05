package com.nus.pvt.master.service;

import java.util.List;

import com.nus.pvt.master.entities.ClientAccountMstr;
import com.nus.pvt.master.model.ClientAccountMstrModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:36:44 pm<br>
 * @Objective: <br>
 */
public interface ClientAccountMstrService {

	public ClientAccountMstr addClientAccountMstr(ClientAccountMstrModel clientAccountMstrModel)throws Exception;
	public ClientAccountMstr updateClientAccountMstr(ClientAccountMstrModel clientAccountMstrModel)throws Exception;
	public boolean deleteClientAccountMstrByRecordId(Integer recordId)throws Exception;
	public ClientAccountMstr getClientAccountMstrByRecordId(Integer recordId)throws Exception;
	public List<ClientAccountMstr> getAllClientAccountMstrs()throws Exception;
}
