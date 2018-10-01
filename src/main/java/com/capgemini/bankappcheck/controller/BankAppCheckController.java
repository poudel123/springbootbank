package com.capgemini.bankappcheck.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankappcheck.entities.Customer;

import com.capgemini.bankappcheck.exception.PayeeAccountNotFoundException;
import com.capgemini.bankappcheck.service.BankAccountService;


@Controller
public class BankAppCheckController 
{
	@Autowired
	BankAccountService bankAccountService;
	@RequestMapping("balanceEnquiry")
	public String getBalanceEnquiryPage()
	{
		return "balanceEnquiry";
	}
	
	@RequestMapping("fundTransfer")
	public String getfundTransferPage ()
	{
		return "fundTransfer";
	}
	
	@RequestMapping("fundTransfer.do")
	public String fundTransfer (HttpSession session,HttpServletRequest request, @RequestParam long payeeAccountNumber,@RequestParam double amount) throws PayeeAccountNotFoundException
	{
		session = request.getSession();
		Customer customer= (Customer) session.getAttribute("customer");
		
		if(bankAccountService.fundTransfer(customer.getBankAccount().getAccountId(), payeeAccountNumber, amount))
		{
			customer.getBankAccount().setBalance(bankAccountService.getBalance(customer.getBankAccount().getAccountId())); 
			session.setAttribute("customer", customer);
			return "transferSuccess";
		}
		
		return "transferFail";
	}

}
