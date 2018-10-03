package com.capgemini.bankappcheck.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.bankappcheck.entities.BankAccount;
import com.capgemini.bankappcheck.repository.BankAccountRepository;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public double getBalance(long accountId) throws DataAccessException{
		try
	{
		return jdbcTemplate.queryForObject("select balance from bankdata where accountid=?", new Object[] {accountId},double.class);

	}catch (DataAccessException e) {
		e.initCause(new EmptyResultDataAccessException("Expected 1 actual 0", 1));
		throw e;
	}
}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		double balance = jdbcTemplate.queryForObject("select balance from bankdata where accountid=?",
				new Object[] { accountId }, Double.class);
		if (balance + newBalance >= 0)
			if (jdbcTemplate.update("update bankdata set balance = ? where accountid = ?",
					new Object[] { newBalance + balance, accountId }) != 0) {
				return true;
			}
		return false;

	}

}
