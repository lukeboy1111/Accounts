package com.example.lc.accounts.bo;

import org.junit.Test;

import junit.framework.TestCase;

public class AccountsTest extends TestCase {

	@Test
	public void testCantWithdraw() {
		Account account = new Account("1", 0.0);
		assertFalse(account.canWithdraw(100.0));
		
	}
	
	@Test
	public void testCanWithdraw() {
		Account account = new Account("1", 100.0);
		assertTrue(account.canWithdraw(100.0));
		
	}
	
	@Test
	public void testBalanceAsString() {
		Account account = new Account("1", 100.0);
		String expected = "100.00";
		assertEquals(expected, account.balanceAsString());
		
	}
	
}
