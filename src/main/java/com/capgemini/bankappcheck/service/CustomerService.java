package com.capgemini.bankappcheck.service;

import org.springframework.dao.DataAccessException;

import com.capgemini.bankappcheck.entities.Customer;
import com.capgemini.bankappcheck.exception.ChangePasswordFailedException;
import com.capgemini.bankappcheck.exception.UpdationFailedException;

public interface CustomerService
{
	public Customer authenticate(Customer customer) throws DataAccessException;
	public Customer updateProfile(Customer customer)throws UpdationFailedException;
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword)throws ChangePasswordFailedException;

}
