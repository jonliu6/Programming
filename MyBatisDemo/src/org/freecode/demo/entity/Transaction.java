package org.freecode.demo.entity;

import java.util.Date;

public class Transaction {
  private int id;
  private int userId;
  private double amount;
  private String transactionType;
  private Date transactionTime;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
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
