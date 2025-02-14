package com.nus.pvt.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.pvt.master.entities.ProjectVerticalMstr;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:58:29 pm<br>
 * @Objective: <br>
 */
@Repository
public interface ProjectVerticalMstrRepo extends JpaRepository<ProjectVerticalMstr, Integer> {

	List<ProjectVerticalMstr> findAllByActiveC(String activeC);
	
}
