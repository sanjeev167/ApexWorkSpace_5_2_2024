package com.nus.sec.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 8:54:26 pm<br>
 * @Objective: <br>
 */
public class AuthRequest {
	@NotNull(message = "username can't be null")
	@NotBlank(message = "username is mandatory")
	private String username; 
	
	@NotNull(message = "password can't be null")
	@NotBlank(message = "Password is mandatory")
    private String password;   
	
	
	public AuthRequest() {
		super();		
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	} 
    
}//End of AuthRequest
