package com.cap.exs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UsernameAlreadyExistException extends RuntimeException {

	public UsernameAlreadyExistException() {
		super();
	}

	public UsernameAlreadyExistException(String message) {
		super(message);
	}

	
	
}
