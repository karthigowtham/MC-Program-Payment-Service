package io.cts.pay.domain;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class ProcessPayment.
 * @author Karthi
 */
@NoArgsConstructor
@Data
public class ProcessPaymentRequest{
	
	private Integer accountNumber;
	
	private String txnType;
	
	private double amount;

}
