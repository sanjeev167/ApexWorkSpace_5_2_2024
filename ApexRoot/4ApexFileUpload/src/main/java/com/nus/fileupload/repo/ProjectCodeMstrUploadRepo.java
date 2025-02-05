package com.nus.fileupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.pvt.master.entities.ProjectCodeMstr;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 27-Jan-2025<br>
 * @Time: 10:01:43 pm<br>
 * @Objective: <br>
 */
@Repository
public interface ProjectCodeMstrUploadRepo extends JpaRepository<ProjectCodeMstr, Integer>{

}
