package com.example.lc.accounts.exception;

public class InvalidAccountException extends RuntimeException {
	private static final long serialVersionUID = -9157088927872282471L;

    public InvalidAccountException(String message) {
        super(message);
    }
}
