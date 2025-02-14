package com.nus.fileupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.pvt.master.entities.ClientAccountMstr;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 28-Jan-2025<br>
 * @Time: 11:07:00 am<br>
 * @Objective: <br>
 */
@Repository
public interface ClientAccountMstrUploadRepo extends JpaRepository<ClientAccountMstr, Integer>{

}
