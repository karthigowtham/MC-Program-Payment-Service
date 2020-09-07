package io.cts.pay.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class BalanceLedgerEntity.
 * @author Karthi
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "BALANCE_LEDGER")
public class BalanceLedgerEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
 
    @Column(name = "USER_ID", length = 25, nullable = false)
    private String userId;
    
    @Column(name = "ACCOUNT_NUMBER", length = 10, nullable = false, unique=true)
    private Integer accountNumber;	
	
    @Column(name = "BALANCE")
	private double currentBalance;

}
