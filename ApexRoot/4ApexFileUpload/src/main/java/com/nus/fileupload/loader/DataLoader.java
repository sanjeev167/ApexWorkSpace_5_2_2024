/**
 * 
 */
package com.nus.fileupload.loader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.nus.fileupload.entities.FileType;
import com.nus.pvt.master.entities.ClientAccountMstr;
import com.nus.pvt.master.entities.ProjectCodeMstr;
import com.nus.pvt.master.entities.ProjectTypeMstr;
import com.nus.pvt.master.entities.ProjectVerticalMstr;
import com.nus.pvt.master.repo.ClientAccountMstrRepo;
import com.nus.pvt.master.repo.FileTypeRepo;
import com.nus.pvt.master.repo.ProjectCodeMstrRepo;
import com.nus.pvt.master.repo.ProjectTypeMstrRepo;
import com.nus.pvt.master.repo.ProjectVerticalMstrRepo;
import com.nus.pvt.master.repo.UserGradeRepo;
import com.nus.sec.entities.ApexUser;
import com.nus.sec.entities.UserGrade;
import com.nus.sec.repo.ApexUserRepository;

/**
 * @Author:SanjeevKumar<br>
 * @Date:25-Jan-2025<br>
 * @Time:13:44:48 pm<br>
 * @Objective: <br>
 */
@Component
public class DataLoader implements ApplicationRunner {

	HashMap<String, ProjectCodeMstr> projectCodeMstrMap = new HashMap<String, ProjectCodeMstr>();
	private final ProjectCodeMstrRepo projectCodeMstrRepo;
	
	private final UserGradeRepo userGradeRepo;
	HashMap<String, UserGrade> userGradeMap = new HashMap<String, UserGrade>();
	
	
	HashMap<Integer, FileType> fileTypeMap = new HashMap<Integer, FileType>();
	private final FileTypeRepo fileTypeRepo;
	
	HashMap<String, ClientAccountMstr> clientAccountMstrMap = new HashMap<String, ClientAccountMstr>();
	private final ClientAccountMstrRepo clientAccountMstrRepo;
	
	HashMap<String, ProjectVerticalMstr> projectVerticalMstrMap = new HashMap<String, ProjectVerticalMstr>();
	private final ProjectVerticalMstrRepo projectVerticalMstrRepo;
	
	HashMap<String, ProjectTypeMstr> projectTypeMstrMap = new HashMap<String, ProjectTypeMstr>();
	private final ProjectTypeMstrRepo projectTypeMstrRepo;
	
	HashMap<String, ApexUser> apexUserMap = new HashMap<String, ApexUser>();
	private final ApexUserRepository apexUserRepository;

	public DataLoader(FileTypeRepo fileTypeRepo,ProjectCodeMstrRepo projectCodeMstrRepo,UserGradeRepo userGradeRepo,
			ClientAccountMstrRepo clientAccountMstrRepo,ProjectVerticalMstrRepo projectVerticalMstrRepo,
			ProjectTypeMstrRepo projectTypeMstrRepo,ApexUserRepository apexUserRepository) {
		this.userGradeRepo = userGradeRepo;
		this.fileTypeRepo = fileTypeRepo;
		this.projectCodeMstrRepo = projectCodeMstrRepo;
		this.clientAccountMstrRepo = clientAccountMstrRepo;
		this.projectVerticalMstrRepo = projectVerticalMstrRepo;
		this.projectTypeMstrRepo =projectTypeMstrRepo;
		this.apexUserRepository =apexUserRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		//Load ProjectCodeMstr
		List<ProjectCodeMstr> projectCodeMstrList = projectCodeMstrRepo.findAllByActiveC("Y");
		Iterator<ProjectCodeMstr> projectCodeMstrIterator = projectCodeMstrList.iterator();
		ProjectCodeMstr projectCodeMstr;
		while (projectCodeMstrIterator.hasNext()) {
			projectCodeMstr = projectCodeMstrIterator.next();			
			projectCodeMstrMap.put(projectCodeMstr.getProjectCode(), projectCodeMstr);
		}
		//Load FileType
		List<FileType> fileTypeList = fileTypeRepo.findAllByActiveC("Y");
		Iterator<FileType> fileTypeIterator = fileTypeList.iterator();
		FileType fileType;
		while (fileTypeIterator.hasNext()) {
			fileType = fileTypeIterator.next();			
			fileTypeMap.put(fileType.getId(), fileType);
		}
		//Load UserGrade
		List<UserGrade> userGradeList = userGradeRepo.findAllByActiveC("Y");
		Iterator<UserGrade> userGradeIterator = userGradeList.iterator();		
		UserGrade userGrade;
		while (userGradeIterator.hasNext()) {
			userGrade = userGradeIterator.next();			
			userGradeMap.put(userGrade.getUserGrade(), userGrade);
		}	
		//Load ProjectVerticalMstr
		List<ClientAccountMstr> clientAccountMstrList = clientAccountMstrRepo.findAllByActiveC("Y");
		Iterator<ClientAccountMstr> clientAccountMstrIterator = clientAccountMstrList.iterator();
		ClientAccountMstr clientAccountMstr;
		while (clientAccountMstrIterator.hasNext()) {
			clientAccountMstr = clientAccountMstrIterator.next();			
			clientAccountMstrMap.put(clientAccountMstr.getClientAccount(), clientAccountMstr);
		}	
		//Load ProjectVerticalMstr
		List<ProjectVerticalMstr> projectVerticalMstrList = projectVerticalMstrRepo.findAllByActiveC("Y");
		Iterator<ProjectVerticalMstr> projectVerticalMstrIterator = projectVerticalMstrList.iterator();
		ProjectVerticalMstr projectVerticalMstr;
		while (projectVerticalMstrIterator.hasNext()) {
		    projectVerticalMstr = projectVerticalMstrIterator.next();			
			projectVerticalMstrMap.put(projectVerticalMstr.getVerticalName(), projectVerticalMstr);
		}
		
		//Load ProjectVerticalMstr
		List<ProjectTypeMstr> projectTypeMstrList = projectTypeMstrRepo.findAllByActiveC("Y");
		Iterator<ProjectTypeMstr> projectTypeMstrIterator = projectTypeMstrList.iterator();
		ProjectTypeMstr projectTypeMstr;
		while (projectTypeMstrIterator.hasNext()) {
			projectTypeMstr = projectTypeMstrIterator.next();			
			projectTypeMstrMap.put(projectTypeMstr.getProjectType(), projectTypeMstr);
		}
		//Load ApexUser
		List<ApexUser> apexUserList = apexUserRepository.findAllByActiveC("Y");
		Iterator<ApexUser> apexUserIterator = apexUserList.iterator();
		ApexUser apexUser;
		while (apexUserIterator.hasNext()) {
			apexUser = apexUserIterator.next();			
			apexUserMap.put(apexUser.getName(), apexUser);
		}	
	}

	/**projectVerticalMstrRepo
	 * @return the 
	 */
	public HashMap<String, ProjectCodeMstr> getProjectCodeMstrMap() {
		return projectCodeMstrMap;
	}

	/**
	 * @param projectCodeMstrMap the projectCodeMstrMap to set
	 */
	public void setProjectCodeMstrMap(HashMap<String, ProjectCodeMstr> projectCodeMstrMap) {
		this.projectCodeMstrMap = projectCodeMstrMap;
	}

	/**
	 * @return the fileTypeMap
	 */
	public HashMap<Integer, FileType> getFileTypeMap() {
		return fileTypeMap;
	}

	/**
	 * @param fileTypeMap the fileTypeMap to set
	 */
	public void setFileTypeMap(HashMap<Integer, FileType> fileTypeMap) {
		this.fileTypeMap = fileTypeMap;
	}

	/**
	 * @return the userGradeMap
	 */
	public HashMap<String, UserGrade> getUserGradeMap() {
		return userGradeMap;
	}

	/**
	 * @param userGradeMap the userGradeMap to set
	 */
	public void setUserGradeMap(HashMap<String, UserGrade> userGradeMap) {
		this.userGradeMap = userGradeMap;
	}

	/**
	 * @return the clientAccountMstrMap
	 */
	public HashMap<String, ClientAccountMstr> getClientAccountMstrMap() {
		return clientAccountMstrMap;
	}

	/**
	 * @param clientAccountMstrMap the clientAccountMstrMap to set
	 */
	public void setClientAccountMstrMap(HashMap<String, ClientAccountMstr> clientAccountMstrMap) {
		this.clientAccountMstrMap = clientAccountMstrMap;
	}

	/**
	 * @return the projectVerticalMstrMap
	 */
	public HashMap<String, ProjectVerticalMstr> getProjectVerticalMstrMap() {
		return projectVerticalMstrMap;
	}

	/**
	 * @param projectVerticalMstrMap the projectVerticalMstrMap to set
	 */
	public void setProjectVerticalMstrMap(HashMap<String, ProjectVerticalMstr> projectVerticalMstrMap) {
		this.projectVerticalMstrMap = projectVerticalMstrMap;
	}

	/**
	 * @return the projectTypeMstrMap
	 */
	public HashMap<String, ProjectTypeMstr> getProjectTypeMstrMap() {
		return projectTypeMstrMap;
	}

	/**
	 * @param projectTypeMstrMap the projectTypeMstrMap to set
	 */
	public void setProjectTypeMstrMap(HashMap<String, ProjectTypeMstr> projectTypeMstrMap) {
		this.projectTypeMstrMap = projectTypeMstrMap;
	}

	/**
	 * @return the apexUserMap
	 */
	public HashMap<String, ApexUser> getApexUserMap() {
		return apexUserMap;
	}

	/**
	 * @param apexUserMap the apexUserMap to set
	 */
	public void setApexUserMap(HashMap<String, ApexUser> apexUserMap) {
		this.apexUserMap = apexUserMap;
	}
	
	
}
