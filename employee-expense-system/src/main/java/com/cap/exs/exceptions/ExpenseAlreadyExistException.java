package com.cap.exs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ExpenseAlreadyExistException extends RuntimeException {
	
	public ExpenseAlreadyExistException() {
		super();
	}

	public ExpenseAlreadyExistException(String message) {
		super(message);
	}
	
	

}
