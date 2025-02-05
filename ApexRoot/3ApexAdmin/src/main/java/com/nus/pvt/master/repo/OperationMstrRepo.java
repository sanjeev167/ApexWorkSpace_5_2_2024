package com.nus.pvt.master.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nus.sec.entities.OperationMstr;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:56:27 pm<br>
 * @Objective: <br>
 */
@Repository
public interface OperationMstrRepo extends CrudRepository<OperationMstr, Integer> {

}
