package com.example.lc.accounts.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.lc.accounts.bo.Account;
import com.example.lc.accounts.currency.Currency.Notes;
import com.example.lc.accounts.model.TellerMachine;
import com.example.lc.accounts.service.ATMService;
import com.example.lc.accounts.service.AccountService;
import com.example.lc.accounts.support.ATMConstants;

@Controller
@EnableAutoConfiguration
public class ExampleController {
	private AccountService accountService;
	private ATMService atmService;
	
	public ExampleController(AccountService accountService, ATMService atmService) {
		this.accountService = accountService;
		this.atmService = atmService;
	}
	
	private void setUp(TellerMachine model) {
		atmService.replenish(model);
	}
	
	@GetMapping("/")
    public String showPavings(ModelMap modelMap, HttpSession httpSession) {
		TellerMachine model = new TellerMachine();
        httpSession.setAttribute(ATMConstants.MODEL, model);
        
        modelMap.addAttribute("accounts", model.getAccounts());
        
        return "welcome";
    }
	
	@GetMapping("/example")
	public String firstExample(ModelMap modelMap, HttpSession httpSession) {
		 TellerMachine model = (TellerMachine) httpSession.getAttribute(ATMConstants.MODEL);
		 checkModel(model);
	     setUp(model);
	    
		 
	     modelMap.addAttribute("accounts", model.getAccounts());
	     modelMap.addAttribute("atmBalance", model.getCurrentBalance());
	     modelMap.addAttribute("notes", model.showNotes());
		 return "example";
	}
	
	@GetMapping("/examplePageTwo")
	public String firstExamplePageTwo(ModelMap modelMap, HttpSession httpSession) {
		 TellerMachine model = (TellerMachine) httpSession.getAttribute(ATMConstants.MODEL);
		 checkModel(model);
	     setUp(model);
	     
	     Integer amount = 100;
	     Integer oldAtmBalance = model.getCurrentBalance();
	     Account account = new Account("01001", 200.00);
	     Double oldBalance = account.getBalance();
		 model = atmService.takeMoneyFromMachine(model, account, amount);
		 List<Notes> list = model.getWithdrawalNotes();
			
		 Integer newAtmBalance = model.getCurrentBalance();
		 Integer expected = oldAtmBalance - amount;
		 Double newBalance = account.getBalance();
		 Double expectedBalance = oldBalance - amount;
		 
	     
	     modelMap.addAttribute("amount", amount);
	     modelMap.addAttribute("atmBalanceOld", oldAtmBalance);
	     modelMap.addAttribute("atmBalance", newAtmBalance);
	     modelMap.addAttribute("accounts", model.getAccounts());
	     modelMap.addAttribute("notes", model.showNotes());
		 return "exampleTwo";
	}
	
	private void checkModel(TellerMachine model) {
    	if(null == model) {
    		throw new IllegalArgumentException("Session has expired");
    	}
    }
	
	@ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView illegalArgumentExceptionHandler(Exception exception, HttpServletRequest request) 
    {
        ModelAndView mav = new ModelAndView();
        ModelMap model = mav.getModelMap();
        
        mav.setViewName("welcome");
        return mav;
 
    }
}
