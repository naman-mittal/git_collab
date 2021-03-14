package com.cap.exs.exceptions;

/**

Exception to be thrown when an wrong credentials provided for login.

@since 1.0

@author Himanshu Nagar

*/

@SuppressWarnings("serial")
public class InvalidUserException extends RuntimeException {

	public InvalidUserException() {
		super();
	}

	public InvalidUserException(String message) {
		super(message);
	}

}
