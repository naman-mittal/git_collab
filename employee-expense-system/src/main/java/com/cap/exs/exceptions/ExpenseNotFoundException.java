package com.cap.exs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ExpenseNotFoundException  extends RuntimeException{
	
	public ExpenseNotFoundException() {
		super();
	}

	public ExpenseNotFoundException(String message) {
		super(message);
	}

}
