package com.cap.exs.exceptions;

@SuppressWarnings("serial")
public class AlreadyExistException extends RuntimeException {

	public AlreadyExistException() {
		super();
	}

	public AlreadyExistException(String message) {
		super(message);
	}

}
