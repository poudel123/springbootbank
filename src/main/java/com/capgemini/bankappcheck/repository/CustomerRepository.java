package com.capgemini.bankappcheck.repository;

import org.springframework.dao.DataAccessException;

import com.capgemini.bankappcheck.entities.Customer;
import com.capgemini.bankappcheck.exception.ChangePasswordFailedException;

public interface CustomerRepository 
{
	public Customer authenticate(Customer customer) throws DataAccessException;
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword)throws ChangePasswordFailedException;
}
