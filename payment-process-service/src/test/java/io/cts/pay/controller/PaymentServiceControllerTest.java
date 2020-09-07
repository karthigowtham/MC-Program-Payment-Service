package io.cts.pay.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.cts.pay.domain.PaymentTxnDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.cts.pay.service.PaymentProcessService;

/**
 * The Class PaymentServiceControllerTest.
 * @author Karthi
 */
@ExtendWith(SpringExtension.class)
public class PaymentServiceControllerTest {

	/** The Payment Service**/
	@Mock
	private PaymentProcessService paymentService;

	/** controller. */
	@InjectMocks
	private PaymentServiceController controller;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test getBalance().
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetBalance() throws Exception {	
		when(paymentService.getBalanceforAccount(123456)).thenReturn(10000.0);
		double actual = controller.getBalance(123456);
		assertEquals(10000.0, actual, 0.0);
		verify(paymentService, times(1)).getBalanceforAccount(123456);
	}
	
	/**
	 * Test getTxnHistory.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetTxnHistory() throws Exception {		
		PaymentTxnDetails transactions = new PaymentTxnDetails();
		transactions.setAccountNumber(123456);
		transactions.setStatusMessage("success");
		when(paymentService.getTxnDetails(123456)).thenReturn(transactions);
		PaymentTxnDetails actual = controller.getTxnHistory(123456);
		assertEquals(transactions, actual);
		verify(paymentService, times(1)).getTxnDetails(123456);
			
	}	
}
