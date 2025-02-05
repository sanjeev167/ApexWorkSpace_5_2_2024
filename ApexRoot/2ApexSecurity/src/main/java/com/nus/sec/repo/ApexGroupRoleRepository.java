package com.nus.sec.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.sec.entities.ApexGroupRole;
/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 8:59:11 pm<br>
 * @Objective: <br>
 */
@Repository
public interface ApexGroupRoleRepository extends JpaRepository<ApexGroupRole, Integer>{

}//End of ApexRoleGroupRepository

