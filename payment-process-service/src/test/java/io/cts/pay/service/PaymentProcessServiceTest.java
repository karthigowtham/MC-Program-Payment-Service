package io.cts.pay.service;

import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
	PaymentProcessServiceImpl serviceImpl;

		/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

}
