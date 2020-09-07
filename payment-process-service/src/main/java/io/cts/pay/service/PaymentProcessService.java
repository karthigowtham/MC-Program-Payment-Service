package io.cts.pay.service;

import io.cts.pay.domain.PaymentTxnDetails;
import io.cts.pay.domain.ProcessPaymentRequest;
import io.cts.pay.exception.PaymentProcessException;


/**
 * The Interface PaymentProcessService.
 * @author Karthi
 */
public interface PaymentProcessService {
	
	
	/**
	 * Method getBalanceForAccount
	 * @param accountNumber
	 * @return balance amount
	 * @throws Exception
	 */
	public double getBalanceforAccount(Integer accountNumber) throws PaymentProcessException;
	
	/**
	 * Method getTxnDetails
	 * @param accountNumber
	 * @return updatePaymentTxn
	 * @throws Exception
	 */
	public PaymentTxnDetails getTxnDetails(Integer accountNumber);
	
	/**
	 * Method updatePaymentTxn
	 * @param request
	 * @return txn status
	 * @throws Exception
	 */
	public void updatePaymentTxn(ProcessPaymentRequest request) throws PaymentProcessException;
	

}
