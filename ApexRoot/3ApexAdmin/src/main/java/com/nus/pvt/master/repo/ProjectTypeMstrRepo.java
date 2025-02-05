package com.nus.pvt.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.pvt.master.entities.ProjectTypeMstr;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:57:43 pm<br>
 * @Objective: <br>
 */
@Repository
public interface ProjectTypeMstrRepo extends JpaRepository<ProjectTypeMstr, Integer> {

	List<ProjectTypeMstr> findAllByActiveC(String activeC);
}
