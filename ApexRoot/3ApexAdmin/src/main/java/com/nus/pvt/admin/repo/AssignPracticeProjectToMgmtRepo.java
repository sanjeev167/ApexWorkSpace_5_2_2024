package com.nus.pvt.admin.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nus.pvt.admin.entities.AssignPracticeProjectToMgmt;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:53:22 pm<br>
 * @Objective: <br>
 */
@Repository
public interface AssignPracticeProjectToMgmtRepo extends CrudRepository<AssignPracticeProjectToMgmt, Integer> {

}
