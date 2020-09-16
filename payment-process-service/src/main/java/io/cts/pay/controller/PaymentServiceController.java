package io.cts.pay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import io.cts.pay.domain.PaymentTxnDetails;
import io.cts.pay.domain.ProcessPaymentRequest;
import io.cts.pay.exception.PaymentProcessException;
import io.cts.pay.service.PaymentProcessService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * The Class PaymentServiceController.
 * @author Karthi
 */
@RestController
@RequestMapping("payment")
public class PaymentServiceController {
	
	 Logger logger = LoggerFactory.getLogger(PaymentServiceController.class);

	
	@Autowired
	private PaymentProcessService paymentService;

	@GetMapping(path = "/getBalance")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "GetAccountBalance", notes = "Retrieve balance for account")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public double getBalance(@RequestParam("accno")Integer accountNumber) throws PaymentProcessException {
		 logger.debug("Balance check for:"+accountNumber);
		return paymentService.getBalanceforAccount(accountNumber);
	}
	
	@GetMapping(path = "/getTxnHistory", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "GetTxnHistory", notes = "Retrieve transaction history")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public PaymentTxnDetails getTxnHistory(@RequestParam("accno")Integer accountNumber) {
		return paymentService.getTxnDetails(accountNumber);
	}
	
	@PostMapping(path = "/updatePayment", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "UpdatePayment", notes = "Update payment request")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<String> updatePaymentTransaction(@RequestBody ProcessPaymentRequest paymentRequest) throws PaymentProcessException {	
		 logger.debug("Request received for update:"+paymentRequest.getAccountNumber());
		 paymentService.updatePaymentTxn(paymentRequest);
		 return new ResponseEntity<String>("Transactopn Successfully Updated", HttpStatus.CREATED);
	}

}
