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
import com.example.lc.accounts.currency.Currency.Notes;
import com.example.lc.accounts.model.TellerMachine;

import junit.framework.TestCase;

import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ExampleController.class)
public class AccountServiceTest {
	
	private TellerMachine model;
	
	@MockBean
	private ATMService atmService;
	
	@Autowired
	private AccountService service;
	
	@Autowired
    private MockMvc mvc;
	
	@Before
    public void setUp() throws Exception {
		model = new TellerMachine();
		model.setCurrentBalance(1000);
	}
	
	
	@Test
	public void testNotesReceived() {
		int amount = 100;

		List<Notes> list = service.withdrawal(amount);
		assertNotNull(list);
		assertEquals(5, list.size());
		// 50, 20, 20, 5, 5
		checkNote(list, 0, 50);
		checkNote(list, 1, 20);
		checkNote(list, 2, 20);
		checkNote(list, 3, 5);
		checkNote(list, 4, 5);
		
	}
	
	@Test
	public void testMoreNotesReceived() {
		int amount = 110;

		List<Notes> list = service.withdrawal(amount);
		assertNotNull(list);
		assertEquals(4, list.size());
		// 50, 20, 20, 5, 5
		checkNote(list, 0, 50);
		checkNote(list, 1, 50);
		checkNote(list, 2, 5);
		checkNote(list, 3, 5);
		
	}
	
	@Test
	public void testNotesReceivedWithFiver() {
		int amount = 105;
		
		List<Notes> list = service.withdrawal(amount);
		assertNotNull(list);
		assertEquals(6, list.size());
		// 50, 20, 20, 5, 5, 5
		checkNote(list, 0, 50);
		checkNote(list, 1, 20);
		checkNote(list, 2, 20);
		checkNote(list, 3, 5);
		checkNote(list, 4, 5);
		checkNote(list, 5, 5);
	}
	
	@Test
	public void testNotesReceivedZero() {
		int amount = 0;
		
		List<Notes> list = service.withdrawal(amount);
		assertNotNull(list);
		assertEquals(0, list.size());
		
	}
	
	@Test
	public void checkAccountBalanceRendersAsStrings() {
		double bal = 2335.29;
		String balString = "2335.29";
		Account account = new Account("010101",bal );
		
		assertEquals(balString, service.accountBalance(account));
		
		bal = 2335.00;
		balString = "2335.00";
		account = new Account("010101",bal );
		
		assertEquals(balString, service.accountBalance(account));
		
	}
	
	@Test
	public void checkCanWithdraw() {
		
		Account account = new Account("01001", 200.00);
		int amount = 100;
		assertTrue(service.canWithdraw(account, amount));
	}
	
	private void checkNote(List<Notes> list, int index, int value) {
		Notes note1 = list.get(index);
		assertNotNull(note1);
		assertEquals(value, note1.value());
	}
	
	
	
}
