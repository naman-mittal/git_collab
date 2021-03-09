package com.cap.exs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ExpenseClaimNotFoundException extends RuntimeException {
	public ExpenseClaimNotFoundException() {
		super();
	}

	public ExpenseClaimNotFoundException(String message) {
		super(message);
	}
}
