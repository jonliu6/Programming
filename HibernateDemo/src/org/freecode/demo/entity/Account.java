package org.freecode.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
	
	private int id;
	private String userName;
	private double balance;
	private Date createdAt;
	
	public Account()
	{
		
	}
	
	public Account( int anId, String aName, double aBalance, Date aDate )
	{
		id = anId;
		userName = aName;
		balance = aBalance;
		createdAt = aDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userName=" + userName + ", balance="
				+ balance + ", createdAt=" + createdAt + "]";
	}
	
	

}
