package com.example.lc.accounts.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.lc.accounts.bo.Account;
import com.example.lc.accounts.controller.ExampleController;
import com.example.lc.accounts.exception.InvalidAmountException;
import com.example.lc.accounts.currency.Currency;
import com.example.lc.accounts.currency.Currency.Notes;
import com.example.lc.accounts.model.TellerMachine;

import junit.framework.TestCase;

import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ExampleController.class)
public class AtmServiceTest {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ATMService service;
	
	private TellerMachine model;
	
	@Before
	public void setUp() {
		model = new TellerMachine();
		model = service.replenish(model);
	}
	
	@Test(expected=InvalidAmountException.class)
	public void checkWithdrawalFunctions() {
		model = new TellerMachine();
		model = service.replenish(model);
		Integer oldBalance = model.getCurrentBalance();
		Account account = new Account("1", 100.00);
		int amount = 10;
		model = service.takeMoneyFromMachine(model, account, amount);
		
	}
	
	private void checkNote(List<Notes> list, int index, int value) {
		Notes note1 = list.get(index);
		assertNotNull(note1);
		assertEquals(value, note1.value());
	}
	
	@Test(expected=InvalidAmountException.class)
	public void checkWithdrawalFunctionsMax() {
		Account account = new Account("1", 100.00);
		int amount = 260;
		service.takeMoneyFromMachine(model, account, amount);
	}
	
	@Test
	public void checkWithdrawalFunctionsAndNotes() {
		int amount = 100;
		model = new TellerMachine();
		model = service.replenish(model);
		Integer oldAtmBalance = model.getCurrentBalance();
		Account account = new Account("01001", 200.00);
		Double oldBalance = account.getBalance();
		model = service.takeMoneyFromMachine(model, account, amount);
		List<Notes> list = model.getWithdrawalNotes();
		// 50, 20, 20, 5, 5
		checkNote(list, 0, 50);
		checkNote(list, 1, 20);
		checkNote(list, 2, 20);
		checkNote(list, 3, 5);
		checkNote(list, 4, 5);
		Integer newAtmBalance = model.getCurrentBalance();
		Integer expected = oldAtmBalance - amount;
		assertEquals(expected, newAtmBalance);
		Double newBalance = account.getBalance();
		Double expectedBalance = oldBalance - amount;
		assertEquals(expectedBalance, newBalance);
		
	}
	
	@Test
	public void checkWithdrawalFunctionsAndNotesExactBalance() {
		int amount = 100;
		model = new TellerMachine();
		model = service.replenish(model);
		Integer oldAtmBalance = model.getCurrentBalance();
		Account account = new Account("01001", 100.00);
		Double oldBalance = account.getBalance();
		model = service.takeMoneyFromMachine(model, account, amount);
		List<Notes> list = model.getWithdrawalNotes();
		// 50, 20, 20, 5, 5
		checkNote(list, 0, 50);
		checkNote(list, 1, 20);
		checkNote(list, 2, 20);
		checkNote(list, 3, 5);
		checkNote(list, 4, 5);
		Integer newAtmBalance = model.getCurrentBalance();
		Integer expected = oldAtmBalance - amount;
		assertEquals(expected, newAtmBalance);
		Double newBalance = account.getBalance();
		Double expectedBalance = oldBalance - amount;
		assertEquals(expectedBalance, newBalance);
		
	}
	
	
	
	
}
