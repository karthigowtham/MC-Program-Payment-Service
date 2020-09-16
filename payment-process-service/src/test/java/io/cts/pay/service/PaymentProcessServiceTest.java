package io.cts.pay.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.cts.pay.domain.ProcessPaymentRequest;
import io.cts.pay.entity.BalanceLedgerEntity;
import io.cts.pay.entity.PaymentProcessStoreEntity;
import io.cts.pay.exception.PaymentProcessException;
import io.cts.pay.repository.BalanceLedgerRepository;
import io.cts.pay.repository.PaymentProcessRepository;

/**
 * The Class PaymentProcessServiceTest.
 * @author Karthi
 */
@ExtendWith(SpringExtension.class)
public class PaymentProcessServiceTest {

	@Spy
	private BalanceLedgerRepository repository;
	
	@Spy
	private PaymentProcessRepository paymentRepo;

	@InjectMocks
	private PaymentProcessServiceImpl serviceImpl;

		/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetBalanceforAccountEx() throws PaymentProcessException {		
		assertThrows( PaymentProcessException.class, ()-> {	
		serviceImpl.getBalanceforAccount(123456);
		});
		
	}
	
	@Test
	public void testGetTxnDetailsEmpty()  {		
		assertNotNull(serviceImpl.getTxnDetails(123456));
	}
	
	@Test
	public void testUpdatePaymentTxnEx() throws PaymentProcessException {	
		ProcessPaymentRequest request = new ProcessPaymentRequest();
		request.setAccountNumber(123456);
		request.setTxnType("DBIT");
		assertThrows( PaymentProcessException.class, ()-> {	
		serviceImpl.updatePaymentTxn(request);
		});	
	}
	
	@Test
	public void testGetBalanceforAccount() throws PaymentProcessException {		
		BalanceLedgerEntity ledger = new BalanceLedgerEntity();
		ledger.setCurrentBalance(10000.0);
		Mockito.when(repository.findByAccountNumber(123456)).thenReturn(ledger);
		assertEquals(10000.0, serviceImpl.getBalanceforAccount(123456));
	}
	
	@Test
	public void testUpdatePaymentTxn() throws PaymentProcessException {	
		ProcessPaymentRequest request = new ProcessPaymentRequest();
		request.setAccountNumber(123456);
		request.setAmount(5000.0);
		request.setTxnType("CRDT");
		BalanceLedgerEntity ledger = new BalanceLedgerEntity();
		ledger.setCurrentBalance(10000.0);
		Mockito.when(repository.findByAccountNumber(123456)).thenReturn(ledger);
		serviceImpl.updatePaymentTxn(request);		
	}
	
	@Test
	public void testgetTxnHistory() {
		PaymentProcessStoreEntity entity = new PaymentProcessStoreEntity();
		entity.setAmount(5000.0);
		entity.setTxnTimestamp(new Date());
		entity.setUpdatedBalance(10000.0);
		entity.setUserId("user");
		List<PaymentProcessStoreEntity> entities= new ArrayList<>();
		entities.add(entity);
		Mockito.when(paymentRepo.findByAccountNumber(123456)).thenReturn(entities);
		assertNotNull(serviceImpl.getTxnDetails(123456));
	}
	
	@Test
	public void testUpdatePaymentTxnDBIT() throws PaymentProcessException {	
		ProcessPaymentRequest request = new ProcessPaymentRequest();
		request.setAccountNumber(123456);
		request.setAmount(5000.0);
		request.setTxnType("DBIT");
		BalanceLedgerEntity ledger = new BalanceLedgerEntity();
		ledger.setCurrentBalance(10000.0);
		Mockito.when(repository.findByAccountNumber(123456)).thenReturn(ledger);
		serviceImpl.updatePaymentTxn(request);		
	}
}
