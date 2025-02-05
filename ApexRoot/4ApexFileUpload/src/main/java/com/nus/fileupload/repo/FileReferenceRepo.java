package com.nus.fileupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.fileupload.entities.FileReference;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 30-Jan-2025<br>
 * @Time: 12:27:54 pm<br>
 * @Objective: <br>
 */
@Repository
public interface FileReferenceRepo extends JpaRepository<FileReference, Integer>{

}
