package io.cts.pay.domain;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class TxnDetails.
 * @author Karthi
 */
@NoArgsConstructor
@Data
public class TxnDetails{
	
	private String txnType;
	
	private double amount;
	
	private double updatedBalance;
	
	private Date txnTimestamp;
	
	

}
