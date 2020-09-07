package io.cts.pay.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class PaymentProcessStoreEntity.
 * @author Karthi
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PAYMENT_PROCESS_STORE")
public class PaymentProcessStoreEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
 
    @Column(name = "USER_ID", length = 25, nullable = false)
    private String userId;
    
    @Column(name = "ACCOUNT_NUMBER", length = 10, nullable = false)
    private Integer accountNumber;	
    
    @Column(name = "TXN_TYPE")
    private String txnType;
    
    @Column(name = "AMOUNT")
	private double amount;
    
    @Column(name = "UPDATED_BALANCE")
	private double updatedBalance;
    
    @Column(name = "TXN_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date txnTimestamp;
    	
}
