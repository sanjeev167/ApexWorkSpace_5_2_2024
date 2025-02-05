package com.nus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 27-Jan-2025<br>
 * @Time: 7:51:41 pm<br>
 * @Objective: <br>
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DataIntegrityViolationException extends RuntimeException{	
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityViolationException(String message) {
	    super(String.format("Failed for [%s]: %s", message));
	  }
}
