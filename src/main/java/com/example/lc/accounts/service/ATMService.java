package com.example.lc.accounts.service;

import com.example.lc.accounts.bo.Account;
import com.example.lc.accounts.currency.Currency.Notes;
import com.example.lc.accounts.exception.InvalidAmountException;
import com.example.lc.accounts.model.TellerMachine;

import java.util.List;
import java.util.Optional;

public interface ATMService {
	public TellerMachine replenish(TellerMachine model);
	public TellerMachine emptyMachine(TellerMachine model);
	public Optional<String> accountBalance(String accountNumber);
	public String accountBalance(Account account);
	public List<Notes> withdrawalNotes(Account account, int amount) throws InvalidAmountException;
	public Account makeWithdrawal(Account account, int amount) throws InvalidAmountException;
	public TellerMachine takeMoneyFromMachine(TellerMachine model, Account account, int amount) throws InvalidAmountException;
	
}
