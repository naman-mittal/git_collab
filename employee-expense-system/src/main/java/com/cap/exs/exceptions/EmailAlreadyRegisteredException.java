package com.cap.exs.exceptions;

/**

Exception to be thrown when an email is already registered for an employee and a new employee tries to use same email.

@since 1.0

@author Naman Mittal

*/

@SuppressWarnings("serial")
public class EmailAlreadyRegisteredException extends RuntimeException {

	public EmailAlreadyRegisteredException() {
		super();
	}

	public EmailAlreadyRegisteredException(String message) {
		super(message);
	}

}
