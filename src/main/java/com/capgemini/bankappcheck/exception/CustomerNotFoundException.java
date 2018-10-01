package com.capgemini.bankappcheck.exception;

import org.springframework.dao.DataAccessException;

public class CustomerNotFoundException extends DataAccessException {
	public CustomerNotFoundException(String message) {
		super(message);
	}

}
