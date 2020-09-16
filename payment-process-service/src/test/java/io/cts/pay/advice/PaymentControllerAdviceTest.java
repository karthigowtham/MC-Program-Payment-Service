package io.cts.pay.advice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.cts.pay.exception.PaymentProcessException;

@ExtendWith(SpringExtension.class)
public class PaymentControllerAdviceTest {
	
	@Test
	public void testprocessPaymentException() {
		PaymentControllerAdvice advice = new PaymentControllerAdvice();
		ResponseEntity<String> res = advice.processPaymentException(new PaymentProcessException("msg"));
		assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
	}
	
	@Test
	public void testprocessRunTimeException() {
		PaymentControllerAdvice advice = new PaymentControllerAdvice();
		ResponseEntity<String> res = advice.processRunTimeException(new Exception("msg"));
		assertEquals(HttpStatus.SERVICE_UNAVAILABLE, res.getStatusCode());
	}

}
