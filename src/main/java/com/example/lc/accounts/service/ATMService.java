package com.example.lc.accounts.service;

import com.example.lc.accounts.bo.Account;
import com.example.lc.accounts.currency.Currency.Notes;
import com.example.lc.accounts.model.TellerMachine;

import java.util.List;
import java.util.Optional;

public interface ATMService {
	public TellerMachine replenish(TellerMachine model);
	public TellerMachine emptyMachine(TellerMachine model);
	public String accountBalance(Account account);
	public Optional<List<Notes>> withdrawal(Account account, int amount);
}
