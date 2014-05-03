package org.freecode.demo.entity;

import java.util.Date;
import java.util.List;

public class Account {
  private int id;
  private String name;
  private Double balance;
  private Date createdAt;
  private List<Transaction> transactions;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String aName) {
	this.name = aName;
}
public Double getBalance() {
	return balance;
}
public void setBalance(Double balance) {
	this.balance = balance;
}
public Date getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}


public List<Transaction> getTransactions() {
	return transactions;
}
public void setTransactions(List<Transaction> transactions) {
	this.transactions = transactions;
}
@Override
public String toString() {
	StringBuilder sb = new StringBuilder();
	if ( transactions != null && transactions.size() > 0 )
	{
		for (Transaction aTransaction : transactions) {
			sb.append( "  " + aTransaction + "\n" );			
		}
	}
	return "Account [id=" + id + ", name=" + name + ", balance="
			+ balance + ", createdAt=" + createdAt + "]" + "\n" + sb.toString();
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Account other = (Account) obj;
	if (id != other.id)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}

  
  
}
