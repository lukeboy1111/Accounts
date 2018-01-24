package com.example.lc.accounts.exception;

public class InvalidAmountException extends RuntimeException {

	
	private static final long serialVersionUID = -9157088927872282470L;

    public InvalidAmountException(String message) {
        super(message);
    }
}
