package com.capgemini.bankappcheck.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.bankappcheck.exception.ChangePasswordFailedException;
import com.capgemini.bankappcheck.exception.CustomerNotFoundException;
import com.capgemini.bankappcheck.exception.LowBalanceException;
import com.capgemini.bankappcheck.exception.PayeeAccountNotFoundException;
import com.capgemini.bankappcheck.exception.UpdationFailedException;

@ControllerAdvice
public class ExceptionController {
   
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public String handelException(HttpServletRequest request, CustomerNotFoundException exception ) {
		
		request.setAttribute("success", exception.toString());
		return "success";
	}
	
	
	
	@ExceptionHandler(value = LowBalanceException.class)
	public String handleError(HttpServletRequest request, LowBalanceException exception) {

		request.setAttribute("lowBalance", exception);

		return "lowBalance";
	}
	
	
	@ExceptionHandler(value = ChangePasswordFailedException.class)
	public String handleErrorExp(HttpServletRequest request, ChangePasswordFailedException exception) {
		System.out.println(exception);
		request.setAttribute("passwordFailed", exception.getMessage());

		return "passwordFailed";
	}
	
	@ExceptionHandler(value = PayeeAccountNotFoundException.class)
	public String handleError(HttpServletRequest request, PayeeAccountNotFoundException exception) {

		request.setAttribute("payeenotFound", exception);

		return "payeenotFound";
	}
	@ExceptionHandler(value = UpdationFailedException.class)
	public String handleExp(HttpServletRequest request, UpdationFailedException exception) {
		request.setAttribute("exception", exception);
		return "updateFail";
	}
}
