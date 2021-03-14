package com.cap.exs.exceptions;

/**

Exception to be thrown when an end date, which is before start date, is provided.

@since 1.0

@author Harsh Bansal

*/
@SuppressWarnings("serial")
public class InvalidEndDateException extends RuntimeException{

	public InvalidEndDateException() {
		super();
	}

	public InvalidEndDateException(String message) {
		super(message);
	}

}
