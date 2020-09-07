package io.cts.pay.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.cts.pay.domain.PaymentTxnDetails;
import io.cts.pay.domain.ProcessPaymentRequest;
import io.cts.pay.domain.TxnDetails;
import io.cts.pay.entity.BalanceLedgerEntity;
import io.cts.pay.entity.PaymentProcessStoreEntity;
import io.cts.pay.exception.PaymentProcessException;
import io.cts.pay.repository.BalanceLedgerRepository;
import io.cts.pay.repository.PaymentProcessRepository;


/**
 * The Class PaymentProcessServiceImpl.
 * @author Karthi
 */
@Service
public class PaymentProcessServiceImpl implements PaymentProcessService {

	Logger logger = LoggerFactory.getLogger(PaymentProcessServiceImpl.class);
	
	@Autowired
	private BalanceLedgerRepository balanceRepo;
	
	@Autowired
	private PaymentProcessRepository paymentRepo;

	@Override
	public double getBalanceforAccount(Integer accountNumber) throws PaymentProcessException {
		BalanceLedgerEntity ledger = balanceRepo.findByAccountNumber(accountNumber);
		if(null == ledger) {
			logger.error("Account not availabel in system:"+accountNumber);
			throw new PaymentProcessException("Account not available in system");			
		}
		
		return ledger.getCurrentBalance();
	}

	@Override
	public PaymentTxnDetails getTxnDetails(Integer accountNumber) {
		List<PaymentProcessStoreEntity> entities = paymentRepo.findByAccountNumber(accountNumber);
		PaymentTxnDetails details = new PaymentTxnDetails();
		details.setAccountNumber(accountNumber);
		if(CollectionUtils.isEmpty(entities)) {
			details.setStatusMessage("No Transaction found in system");
			return details;
		}
		details.setStatusMessage("success");
		details.setUserId(entities.get(0).getUserId());
		List<TxnDetails> txnDetails = new ArrayList<TxnDetails>();
		entities.stream().forEach(entity -> {
			TxnDetails txn = new TxnDetails();
			txn.setAmount(entity.getAmount());
			txn.setTxnTimestamp(entity.getTxnTimestamp());
			txn.setTxnType(entity.getTxnType());
			txn.setUpdatedBalance(entity.getUpdatedBalance());
			txnDetails.add(txn);
		});
		details.setTransactions(txnDetails);
		return details;
		
	}

	@Override
	@Transactional
	public void updatePaymentTxn(ProcessPaymentRequest request) throws PaymentProcessException {
		if(!eligibleForTxn(request)) {
			logger.error("Account not eligible for DBIT transaction");
			 throw new PaymentProcessException("This Transacton Not Eligible");
		}	
		
		BalanceLedgerEntity ledger = balanceRepo.findByAccountNumber(request.getAccountNumber());
		double updatedBal = request.getTxnType().equals("CRDT")?ledger.getCurrentBalance()+request.getAmount():ledger.getCurrentBalance()-request.getAmount();
		ledger.setCurrentBalance(updatedBal);
		balanceRepo.save(ledger);
		
		PaymentProcessStoreEntity entity = new PaymentProcessStoreEntity();
		entity.setAccountNumber(request.getAccountNumber());
		entity.setAmount(request.getAmount());
		entity.setTxnTimestamp(new Date());
		entity.setTxnType(request.getTxnType());
		entity.setUpdatedBalance(updatedBal);
		entity.setUserId(ledger.getUserId());
		
		paymentRepo.save(entity);
	}

	private boolean eligibleForTxn(ProcessPaymentRequest request) {
		if(request.getTxnType().equals("CRDT")) {
			return true;
		}
		BalanceLedgerEntity ledger = balanceRepo.findByAccountNumber(request.getAccountNumber());
		if(null == ledger) {
			return false;
		}
		return ledger.getCurrentBalance()-request.getAmount() >= 0 ? true: false;
	}

}