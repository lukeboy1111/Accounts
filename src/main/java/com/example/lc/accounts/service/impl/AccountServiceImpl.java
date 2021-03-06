package com.example.lc.accounts.service.impl;

import org.apache.log4j.Logger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lc.accounts.bo.Account;
import com.example.lc.accounts.currency.Currency;
import com.example.lc.accounts.currency.Currency.Notes;
import com.example.lc.accounts.dao.AccountsFetcher;
import com.example.lc.accounts.exception.InvalidAccountException;
import com.example.lc.accounts.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private final static Logger logger = Logger.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private AccountsFetcher dao;
	
	@Override
	public List<Account> accounts() {
		return dao.allOpenAccounts();
	}

	@Override
	public List<Notes> withdrawal(int amount) {
		return Currency.getBalance(amount);
	}

	@Override
	public Optional<Account> find(String accountNumber) {
		for(Account acc: accounts()) {
			if(acc.getAccountNumber().equals(accountNumber)) {
				logger.debug("Returning account");
				return Optional.of(acc);
			}
		}
		logger.warn("Empty account - not found");
		return Optional.empty();
	}

	@Override
	public String accountBalance(Account account) {
		return account.balanceAsString();
	}

	@Override
	public boolean canWithdraw(Account account, int amount) throws InvalidAccountException {
		Optional<Account> checked = find(account.getAccountNumber());
		if(checked.isPresent()) {
			logger.warn("account found");
			return account.canWithdraw(amount);
		}
		else {
			logger.warn("account not found");
			throw new InvalidAccountException("account not found");
		}
	}

	@Override
	public Account reduceBalanceBy(Account account, int amount) {
		account.reduceBalance(amount);
		return account;
	}

}
