package com.example.lc.accounts.bo;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Account {
	private String accountNumber;
	private Double balance;
	
	public Account(String accountNumber, Double balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}

	public boolean canWithdraw(double amount) {
		if(balance >= amount) {
			return true;
		}
		return false;
	}

	public String balanceAsString() {
		NumberFormat formatter = new DecimalFormat("#0.00");
        String balanceString = formatter.format(balance);
		return balanceString;
	}

	public void reduceBalance(int amount) {
		balance = balance - amount;
	}
	
}
