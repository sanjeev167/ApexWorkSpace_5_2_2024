package com.nus.sec.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.sec.entities.ApexRole;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 9:00:38 pm<br>
 * @Objective: <br>
 */
@Repository
public interface ApexRoleRepository extends JpaRepository<ApexRole, Integer>{

}//End of ApiRoleRepository
