package com.example.lc.accounts.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lc.accounts.bo.Account;
import com.example.lc.accounts.currency.Currency.Notes;
import com.example.lc.accounts.exception.InvalidAmountException;
import com.example.lc.accounts.model.TellerMachine;
import com.example.lc.accounts.service.ATMService;
import com.example.lc.accounts.service.AccountService;
import com.example.lc.accounts.support.ATMConstants;

@Service
public class ATMServiceImpl implements ATMService {

	private final static Logger logger = Logger.getLogger(ATMServiceImpl.class);
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public TellerMachine replenish(TellerMachine model) {
		model.replenish();
		return model;
	}

	@Override
	public Optional<String> accountBalance(String accountNumber) {
		Optional<Account> found = accountService.find(accountNumber);
		if(found.isPresent()) {
			return Optional.of(accountService.accountBalance(found.get()));
		}
		return Optional.empty();
	}
	
	@Override
	public String accountBalance(Account account) {
		return accountService.accountBalance(account);
	}

	@Override
	public List<Notes> withdrawalNotes(Account account, int amount) throws InvalidAmountException {
		if(!isMultiple(amount, ATMConstants.FIVE)) {
			throw new InvalidAmountException("Not a valid amount");
		}
		if(accountService.canWithdraw(account, amount)) {
			List<Notes> notes = accountService.withdrawal(amount);
			return notes;
		}
		else {
			throw new InvalidAmountException("Account cannot withdraw requested amount");
		}
	}
	
	@Override
	public Account makeWithdrawal(Account account, int amount) throws InvalidAmountException {
		if(accountService.canWithdraw(account, amount)) {
			account = accountService.reduceBalanceBy(account, amount);
			return account;
		}
		else {
			throw new InvalidAmountException("Account cannot withdraw requested amount");
		}
	}

	@Override
	public TellerMachine emptyMachine(TellerMachine model) {
		model.setCurrentBalance(0);
		return model;
	}
	
	private boolean isMultiple(int a, int b) {
        return (a % b == 0);
    }

	

}
