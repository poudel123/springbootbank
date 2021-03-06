package com.capgemini.bankappcheck.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.bankappcheck.entities.Customer;
import com.capgemini.bankappcheck.exception.ChangePasswordFailedException;
import com.capgemini.bankappcheck.exception.CustomerNotFoundException;
import com.capgemini.bankappcheck.exception.UpdationFailedException;
import com.capgemini.bankappcheck.repository.CustomerRepository;
import com.capgemini.bankappcheck.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService 
{@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer authenticate(Customer customer) throws CustomerNotFoundException {
		try {
		return customerRepository.authenticate(customer);
	}catch (DataAccessException e) {
		CustomerNotFoundException c = new CustomerNotFoundException("The Customer not Found");
		c.initCause(e);
		throw c;
	}
	}
	@Override
	public Customer updateProfile(Customer customer) throws UpdationFailedException {
	try {
		return customerRepository.updateProfile(customer);
	}catch (DataAccessException e) {
		UpdationFailedException a = new UpdationFailedException("failed to update the customer details");
		a.initCause(e);
		throw a;
	}
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) throws ChangePasswordFailedException {
		try {
		return customerRepository.updatePassword(customer, oldPassword, newPassword);
	}catch (DataAccessException e) {
		ChangePasswordFailedException passwordChangeFailedException = new ChangePasswordFailedException(
				"Failed to change the password");
		passwordChangeFailedException.initCause(e);
		throw passwordChangeFailedException;
	}
	}
}
