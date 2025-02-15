package com.nus.fileupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.sec.entities.ApexUser;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 06-Feb-2025<br>
 * @Time: 6:41:04 pm<br>
 * @Objective: <br>
 */
@Repository
public interface UserAccountMstrUploadRepo extends JpaRepository<ApexUser, Integer>{

}
