package io.cts.user.advice;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserAccessControllerAdvice {
		
	@ExceptionHandler({RuntimeException.class, SQLException.class})
	public ResponseEntity<String> processRunTimeException(Exception e) {
		return new ResponseEntity<String>("Service temporarily not available", HttpStatus.SERVICE_UNAVAILABLE);	
	}

}
