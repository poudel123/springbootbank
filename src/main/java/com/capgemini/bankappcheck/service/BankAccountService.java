package com.capgemini.bankappcheck.service;

import com.capgemini.bankappcheck.exception.LowBalanceException;
import com.capgemini.bankappcheck.exception.PayeeAccountNotFoundException;

public interface BankAccountService 
{
	public double getBalance(long accountId);
	public double withdraw(long accountId, double amount)throws LowBalanceException;
	public double deposit(long accountId, double amount);
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws PayeeAccountNotFoundException;

}
