package com.example.lc.accounts.service.impl;

import org.apache.log4j.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lc.accounts.bo.Account;
import com.example.lc.accounts.dao.AccountsFetcher;
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

}
