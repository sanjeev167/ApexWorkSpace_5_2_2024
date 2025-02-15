package com.nus.pvt.admin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.pvt.admin.entities.AssignDmProjectToPractice;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:52:24 pm<br>
 * @Objective: <br>
 */
@Repository
public interface AssignDmProjectToPracticeRepo extends JpaRepository<AssignDmProjectToPractice, Integer> {
	
	List<AssignDmProjectToPractice> findAllByActiveC(String activeC);
}
