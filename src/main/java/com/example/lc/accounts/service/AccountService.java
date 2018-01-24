package com.example.lc.accounts.service;

import java.util.List;
import java.util.Optional;

import com.example.lc.accounts.bo.Account;
import com.example.lc.accounts.currency.Currency.Notes;
import com.example.lc.accounts.exception.InvalidAccountException;
import com.example.lc.accounts.exception.InvalidAmountException;

public interface AccountService {
	public List<Account> accounts();
	public List<Notes> withdrawal(int amount);
	public Optional<Account> find(String accountNumber);
	public String accountBalance(Account account);
	public boolean canWithdraw(Account account, int amount) throws InvalidAccountException;
	public Account reduceBalanceBy(Account account, int amount);
}
