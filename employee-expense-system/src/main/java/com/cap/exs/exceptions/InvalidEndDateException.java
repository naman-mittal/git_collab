package com.cap.exs.exceptions;

public class InvalidEndDateException extends RuntimeException{

	public InvalidEndDateException() {
		super();
	}

	public InvalidEndDateException(String message) {
		super(message);
	}

}
