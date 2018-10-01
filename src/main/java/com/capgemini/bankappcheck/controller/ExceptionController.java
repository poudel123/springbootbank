package com.capgemini.bankappcheck.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.bankappcheck.exception.ChangePasswordFailedException;
import com.capgemini.bankappcheck.exception.CustomerNotFoundException;
import com.capgemini.bankappcheck.exception.LowBalanceException;

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
	public String handleErrorPassword(HttpServletRequest request, ChangePasswordFailedException exception) {

		request.setAttribute("passwordFailed", exception);

		return "passwordFailed";
	}
}
