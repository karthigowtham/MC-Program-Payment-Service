package io.cts.pay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cts.pay.entity.BalanceLedgerEntity;


/**
 * The Interface BalanceLedgerRepository.
 * @author Karthi
 */
@Repository
public interface BalanceLedgerRepository extends JpaRepository<BalanceLedgerEntity, Long> {
	
	
	/**
	 * Method findByAccountNumber
	 * @param accountNumber
	 * @return BalanceLedgerEntity
	 */
	public BalanceLedgerEntity findByAccountNumber(Integer accountNumber);

}