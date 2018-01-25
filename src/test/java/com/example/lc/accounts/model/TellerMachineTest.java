package com.example.lc.accounts.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.example.lc.accounts.bo.Account;
import com.example.lc.accounts.currency.Currency.Notes;

import junit.framework.TestCase;

public class TellerMachineTest extends TestCase {

	@Test
	public void testCheckCanWithdraw() {
		Account account = new Account("01001", 500.00);
		List<Account> list = new ArrayList<Account>();
		list.add(account);
		TellerMachine model = new TellerMachine(list);
		Optional<List<Notes>> notes = model.makeWithdrawal(account, 100.00);
		assertTrue(notes.isPresent());
		List<Notes> notesReceived = notes.get();
		assertEquals(5, notesReceived.size());
		checkNote(notesReceived, 0, 50);
		checkNote(notesReceived, 1, 20);
		checkNote(notesReceived, 2, 20);
		checkNote(notesReceived, 3, 5);
		checkNote(notesReceived, 4, 5);
	}
	
	@Test
	public void testCheckCanWithdrawSecondTest() {
		Account account = new Account("01001", 500.00);
		List<Account> list = new ArrayList<Account>();
		list.add(account);
		TellerMachine model = new TellerMachine(list);
		Optional<List<Notes>> notes = model.makeWithdrawal(account, 120.00);
		assertTrue(notes.isPresent());
		List<Notes> notesReceived = notes.get();
		assertEquals(5, notesReceived.size());
		checkNote(notesReceived, 0, 50);
		checkNote(notesReceived, 1, 50);
		checkNote(notesReceived, 2, 10);
		checkNote(notesReceived, 3, 5);
		checkNote(notesReceived, 4, 5);
	}
	
	private void checkNote(List<Notes> list, int index, int value) {
		Notes note1 = list.get(index);
		assertNotNull(note1);
		assertEquals(value, note1.value());
	}
}
