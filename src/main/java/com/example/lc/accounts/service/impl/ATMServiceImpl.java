package com.example.lc.accounts.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.lc.accounts.bo.Account;
import com.example.lc.accounts.currency.Currency.Notes;
import com.example.lc.accounts.model.TellerMachine;
import com.example.lc.accounts.service.ATMService;

@Service
public class ATMServiceImpl implements ATMService {

	private final static Logger logger = Logger.getLogger(ATMServiceImpl.class);
	
	@Override
	public TellerMachine replenish(TellerMachine model) {
		model.replenish();
		return model;
	}

	@Override
	public String accountBalance(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Notes>> withdrawal(Account account, int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TellerMachine emptyMachine(TellerMachine model) {
		model.setCurrentBalance(0);
		return model;
	}

}
