package com.example.lc.accounts.dao;

import java.util.List;

import com.example.lc.accounts.bo.Account;

public interface AccountsFetcher {
	public List<Account> allOpenAccounts();
	
}
