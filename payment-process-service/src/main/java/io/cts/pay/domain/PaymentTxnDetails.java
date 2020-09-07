package io.cts.pay.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class PaymentTxnDetails.
 * @author Karthi
 */
@NoArgsConstructor
@Data
public class PaymentTxnDetails{
	
	private Integer accountNumber;
	
	private String userId;
	
	private String statusMessage;
	
	private List<TxnDetails> transactions;

}
