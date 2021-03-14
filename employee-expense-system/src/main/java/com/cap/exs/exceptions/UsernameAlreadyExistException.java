package com.cap.exs.exceptions;

/**

Exception to be thrown when a username is already registered for an employee and a new employee tries to use same username.

@since 1.0

@author Himanshu Nagar

*/

@SuppressWarnings("serial")
public class UsernameAlreadyExistException extends RuntimeException {

	public UsernameAlreadyExistException() {
		super();
	}

	public UsernameAlreadyExistException(String message) {
		super(message);
	}

	
	
}
