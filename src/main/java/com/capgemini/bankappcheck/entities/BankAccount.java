package com.capgemini.bankappcheck.entities;

import java.util.Objects;

public class BankAccount 
{
	private long accountId;
	private double balance;
	private String accountType;

	public long getAccountId() 
	{
		return accountId;
	}
	public void setAccountId(long accountId) 
	{
		this.accountId = accountId;
	}
	public double getBalance() 
	{
		return balance;
	}
	public void setBalance(double balance) 
	{
		this.balance = balance;
	}
	public String getAccountType() 
	{
		return accountType;
	}
	public void setAccountType(String accountType) 
	{
		this.accountType = accountType;
	}
	
	public BankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BankAccount(long accountId, double balance, String accountType) 
	{
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.accountType = accountType;
	}
	
}
