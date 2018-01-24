package com.example.lc.accounts.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.lc.accounts.bo.Account;
import com.example.lc.accounts.dao.impl.AccountsFetcherImpl;

import junit.framework.TestCase;

public class AccountsFetcherTest {

	@Test
	public void checkItemsReturned() {
		AccountsFetcher dao = new AccountsFetcherImpl();
		List<Account> items = dao.allOpenAccounts();
		assertEquals(3, items.size());
	}
	
}
