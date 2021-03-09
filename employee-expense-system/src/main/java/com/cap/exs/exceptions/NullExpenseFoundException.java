package com.cap.exs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class NullExpenseFoundException extends RuntimeException{
	
	public NullExpenseFoundException() {
		super();
	}

	public NullExpenseFoundException(String message) {
		super(message);
	}

}
