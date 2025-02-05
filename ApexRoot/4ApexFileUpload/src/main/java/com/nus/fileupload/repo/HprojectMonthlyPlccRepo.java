package com.nus.fileupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.fileupload.entities.HprojectMonthlyPlcc;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 30-Jan-2025<br>
 * @Time: 6:13:27 pm<br>
 * @Objective: <br>
 */
@Repository
public interface HprojectMonthlyPlccRepo extends JpaRepository<HprojectMonthlyPlcc, Integer>{

}
