package com.example.lc.accounts.dao.impl; 

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.lc.accounts.bo.Account;
import com.example.lc.accounts.dao.AccountsFetcher;

@Repository
public class AccountsFetcherImpl implements AccountsFetcher {

	@Override
	public List<Account> allOpenAccounts() {
		List<Account> list = new ArrayList<Account>();
		list.add(new Account("01001", 2738.59));
		list.add(new Account("01002", 23.00));
		list.add(new Account("01003", 0.00));
		return list;
	}

}
