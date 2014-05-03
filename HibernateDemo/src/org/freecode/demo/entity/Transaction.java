package org.freecode.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@NamedNativeQueries({	@NamedNativeQuery(	name = "updateBalance",	
                                            query = "CALL prc_update_balance2(:v_userid, :v_transactiontype, :d_amount )",	
                                            resultClass = Account.class ) })
@Entity
@Table (name = "t_transaction", uniqueConstraints = { @UniqueConstraint( columnNames = "id") } )
public class Transaction implements Serializable {
	private int id;
	private int userId;
	private double amount;
	private String transactionType;
	private Date transactionTime;
	
	public Transaction( int anId, int aUserId, double anAmount, String aTransType, Date aTransTime )
	{
		id = anId;
		userId = aUserId;
		amount = anAmount;
		transactionType = aTransType;
		transactionTime = aTransTime;
	}
	
	public Transaction()
	{
		
	}
	
	// not auto-generate number
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "user_id", nullable = false )
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Column(name = "transaction_type")
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	@Column(name = "transaction_time")
	public Date getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", userId=" + userId + ", amount="
				+ amount + ", transactionType=" + transactionType
				+ ", transactionTime=" + transactionTime + "]";
	}
	
}
