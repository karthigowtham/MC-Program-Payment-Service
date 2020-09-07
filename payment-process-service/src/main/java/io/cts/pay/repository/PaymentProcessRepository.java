package io.cts.pay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.cts.pay.entity.BalanceLedgerEntity;
import io.cts.pay.entity.PaymentProcessStoreEntity;


/**
 * The Interface PaymentProcessRepository.
 * @author Karthi
 */
@Repository
public interface PaymentProcessRepository extends JpaRepository<PaymentProcessStoreEntity, Long> {
	
	
	/**
	 * Method findByAccountNumber
	 * @param accountNumber
	 * @return list of PaymentProcessStoreEntity
	 */
	@Query("Select p from PaymentProcessStoreEntity p where p.accountNumber= :accountNumber order by p.txnTimestamp desc")  
	public List<PaymentProcessStoreEntity> findByAccountNumber(Integer accountNumber);
	
}