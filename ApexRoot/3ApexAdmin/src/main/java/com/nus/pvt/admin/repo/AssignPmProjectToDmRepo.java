package com.nus.pvt.admin.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nus.pvt.admin.entities.AssignPmProjectToDm;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:52:55 pm<br>
 * @Objective: <br>
 */
@Repository
public interface AssignPmProjectToDmRepo extends CrudRepository<AssignPmProjectToDm, Integer> {

}
