package io.cts.pay.advice;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.cts.pay.exception.PaymentProcessException;

@ControllerAdvice
public class PaymentControllerAdvice {
	
	@ExceptionHandler(PaymentProcessException.class)
	public ResponseEntity<String> processPaymentException(PaymentProcessException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler({RuntimeException.class, SQLException.class})
	public ResponseEntity<String> processRunTimeException(Exception e) {
		return new ResponseEntity<String>("Service temporarily not available", HttpStatus.SERVICE_UNAVAILABLE);	
	}

}
