package com.nus.db.config;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 08-Jan-2025<br>
 * @Time: 9:47:55 pm<br>
 * @Objective: <br>
 */
public class ApiJpaConstants {

	// Persistence unit
	public static final String API_SERVICE_JPA_UNIT = "ApiService";

	// Define all the entities package names here. This will be an entityManager specific
	public static final String[] API_SERVICE_PKG_ENTITIES_ARRAY = new String[] {"com.nus.sec.entities",
			                                                                    "com.nus.pub.entities", 
			                                                                    "com.nus.pvt.admin.entities", 
			                                                                    "com.nus.pvt.master.entities",
			                                                                    "com.nus.fileupload.entities"};

	// Define all the repositories package names here. This will be an entityManager specific
	public static final String PKG_SEC_REPO = "com.nus.sec.repo";
	public static final String PKG_PUB_REPO = "com.nus.pub.repo";
	public static final String PKG_MASTER_REPO = "com.nus.pvt.master.repo";
	public static final String PKG_ADMIN_REPO = "com.nus.pvt.admin.repo";
	public static final String PKG_FILEUPLOAD_REPO = "com.nus.fileupload.repo";

}
