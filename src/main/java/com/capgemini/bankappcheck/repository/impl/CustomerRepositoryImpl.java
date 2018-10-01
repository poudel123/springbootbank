package com.capgemini.bankappcheck.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.bankappcheck.entities.BankAccount;
import com.capgemini.bankappcheck.entities.Customer;
import com.capgemini.bankappcheck.repository.CustomerRepository;
@Repository
public class CustomerRepositoryImpl implements CustomerRepository
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Customer authenticate(Customer customer) throws DataAccessException 
	{
		try {
			return jdbcTemplate.queryForObject("select * from customerdata where customerId=? and password=?",new Object[] {customer.getCustomerId(),customer.getPassword()}, new CustomerRowMapper());
		}
		

	
	catch (DataAccessException e) {

		e.initCause(new EmptyResultDataAccessException("customer", 1));
		
		throw e;
	}
	}
	@Override
	public Customer updateProfile(Customer customer) 
	{
		jdbcTemplate.update("UPDATE customerdata SET address=?,email=?  WHERE customerId=?",new Object[] {customer.getAddress(),customer.getEmail(),customer.getCustomerId()});
		return customer;
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) throws DataAccessException
	{
		try {
		int count=0;
		String originalPassword=jdbcTemplate.queryForObject("select password from customerdata where customerid=?",new Object[] {customer.getCustomerId()},String.class);
		if(oldPassword.equals(originalPassword))
		{
			count=jdbcTemplate.update("UPDATE customerdata SET password=?  WHERE customerId=?",new Object[] {newPassword,customer.getCustomerId()});
		}
		if(count==1)
		{
			return true;
		}
		return false;
	
	}catch (DataAccessException e) {
		e.initCause(new EmptyResultDataAccessException(1));
		throw e;
	}
	}

	private class CustomerRowMapper implements RowMapper<Customer>
	{
		@Override
		public Customer mapRow (ResultSet rs , int rowNum) throws SQLException {
			Customer customer = new Customer();
			BankAccount bankAccount= new BankAccount();
			customer.setAddress(rs.getString(5));
			customer.setCustomerId(rs.getInt(2));
			customer.setCustomerName(rs.getString(1));
			customer.setDateOfBirth(rs.getDate(6).toLocalDate());
			customer.setEmail(rs.getString(4));
			bankAccount= jdbcTemplate.queryForObject("select * from bankdata where accountid=?", new Object[] {rs.getInt(7)},new BankAccountRowMapper());
			customer.setBankAccount(bankAccount);
			return customer;
		}
	}
	private class BankAccountRowMapper implements RowMapper<BankAccount>
	{
		@Override
		public BankAccount mapRow (ResultSet rs , int rowNum) throws SQLException{
			BankAccount bankAccount = new BankAccount();
			bankAccount.setAccountId(rs.getInt(1));
			bankAccount.setAccountType(rs.getString(3));
			bankAccount.setBalance(rs.getInt(2));
			return bankAccount;
			
		}
		
	}
}
