package com.nus.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @Author: SanjeevKumar<br>
 * @Date: 08-Jan-2025<br>
 * @Time: 10:00:34 pm<br>
 * @Objective: <br>
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class TokenRefreshException extends RuntimeException{	
	private static final long serialVersionUID = 1L;	
	public TokenRefreshException(String token, String message) {
	    super(String.format("Failed for [%s]: %s", token, message));
	  }
}//End of TokenRefreshException
