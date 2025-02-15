package com.nus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 08-Jan-2025<br>
 * @Time: 9:53:14 pm<br>
 * @Objective: This is a custom exception which can be thrown from any section of the code and be caught through a 
 * centralized controller advice.<br>
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidJwtAuthenticationException extends RuntimeException{	
	private static final long serialVersionUID = 1L;
	public InvalidJwtAuthenticationException(String message) { 
		super(message);		System.out.println("MMMMMMMM");
	}
}//End of InvalidJwtAuthenticationException 
