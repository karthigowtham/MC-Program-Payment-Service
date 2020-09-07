package io.cts.apigw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * The Class FallbackController.
 * @author Karthi
 */
@RestController
@RequestMapping("/fallback")
public class FallbackController {
	
	 Logger logger = LoggerFactory.getLogger(FallbackController.class);

	
	 @GetMapping("/message")
	 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 public String test() {
		 logger.info("Circuit breaker enabled");
		 return "Message from fallbackl: Requested service currently not avilable";
	}

}
