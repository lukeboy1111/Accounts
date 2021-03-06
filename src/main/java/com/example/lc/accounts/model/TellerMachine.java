package com.example.lc.accounts.model;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.example.lc.accounts.bo.Account;
import com.example.lc.accounts.currency.Currency;
import com.example.lc.accounts.currency.Currency.Notes;
import com.example.lc.accounts.support.ATMConstants;

public class TellerMachine {
	
	private final static Logger logger = Logger.getLogger(TellerMachine.class);
	
	private Integer currentBalance;
	private List<Notes> listNotes;
	private List<Account> accounts;
	
	public TellerMachine() {
		listNotes = new ArrayList<Notes>();
		
	}
	
	public TellerMachine(List<Account> accounts) {
		currentBalance = ATMConstants.STARTING_BALANCE;
		listNotes = Currency.getBalance(currentBalance);
		this.accounts = accounts;
	}
	
	public void replenish() {
		currentBalance = ATMConstants.STARTING_BALANCE;
		listNotes = Currency.getBalance(currentBalance);
	}
	
	public Optional<List<Notes>> showNotes() {
		if(currentBalance > 0) {
		  List<Notes> returnList = Currency.getBalance(currentBalance);
		  return Optional.of(returnList);
		}
		else {
			return Optional.empty();
		}
	}
	
	public Optional<List<Notes>> makeWithdrawal(Account account, Double amount) {
		if(account.canWithdraw(amount)) {
			List<Notes> notes = Currency.getBalance(amount.intValue());
			return Optional.of(notes);
		}
		else {
			return Optional.empty();
		}
	}
	

	public Integer getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Integer currentBalance) {
		this.currentBalance = currentBalance;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public void setNotes(List<Notes> notes) {
		listNotes = notes;
	}
	
	public List<Notes> getWithdrawalNotes() {
		return listNotes;
	}

	public void reduceBalanceBy(int amount) {
		currentBalance -= amount;
		
	}
	
	
}
