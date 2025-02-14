package com.nus.pvt.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.sec.entities.UserGrade;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 5:00:05 pm<br>
 * @Objective: <br>
 */
@Repository
public interface UserGradeRepo extends JpaRepository<UserGrade, Integer> {

	List<UserGrade> findAllByActiveC(String activeC);
}
