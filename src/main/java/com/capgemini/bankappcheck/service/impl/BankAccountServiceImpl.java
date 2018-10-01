package com.capgemini.bankappcheck.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankappcheck.exception.LowBalanceException;
import com.capgemini.bankappcheck.exception.PayeeAccountNotFoundException;
import com.capgemini.bankappcheck.repository.BankAccountRepository;
import com.capgemini.bankappcheck.service.BankAccountService;
@Service
public class BankAccountServiceImpl implements BankAccountService 
{
	@Autowired
	private BankAccountRepository bankAccountRepository;
	@Override
	public double getBalance(long accountId) 
	{
		return bankAccountRepository.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount)throws LowBalanceException {
	
		if(bankAccountRepository.updateBalance(accountId, -1*amount)) {
			return bankAccountRepository.getBalance(accountId);
	}
	
	throw new LowBalanceException("Balance is low to make transaction");
	}
	
	/*public double withdraw(long accountId, double amount) throws LowBalanceException{
		double balance = bankAccountRepository.getBalance(accountId);
		if (balance != -1) {
			if (balance - amount >= 0) {
				bankAccountRepository.updateBalance(accountId, balance - amount);
				return bankAccountRepository.getBalance(accountId);
			}return bankAccountRepository.getBalance(accountId);
		}
		throw new LowBalanceException("Balance is low to make transaction");
	}*/

	@Override
	public double deposit(long accountId, double amount)
	{
		if(bankAccountRepository.updateBalance(accountId, amount))
			return bankAccountRepository.getBalance(accountId);
		return bankAccountRepository.getBalance(accountId);
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws PayeeAccountNotFoundException,LowBalanceException {
		System.out.println("i am in fund transfer service impl"+toAcc);
		if(bankAccountRepository.getBalance(fromAcc)<amount)
		{
			throw new LowBalanceException("amount insufficient");
		}
		else if(bankAccountRepository.getBalance(toAcc)==-1) {
			throw new PayeeAccountNotFoundException("incorrect account");
		}
		
		else if(bankAccountRepository.updateBalance(fromAcc, -1*amount))
		{
			if(bankAccountRepository.updateBalance(toAcc, amount))
			{
				return true;
			}
		}
		throw new PayeeAccountNotFoundException("Fund transfer unsuccessfull");
	}
}
