package com.nus.pub.ctrl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * @Author: SanjeevKumar<br>
 * @Date: 14-Jan-2025<br>
 * @Time: 7:23:39 am<br>
 * @Objective: <br>
 */
public class encodeEncryptUserPassword {

	/**
	 * @param <ByCryptPasswordEncoder>
	 * @param args
	 */
	public static void mains(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String testPasswordEncoded = passwordEncoder.encode("admin");	   
	    System.out.println("encoded password = "+testPasswordEncoded);
	}

}
