package com.cap.exs.exceptions;

/**

Exception to be thrown when an pan is already registered for an employee and a new employee tries to use same pan.

@since 1.0

@author Naman Mittal

*/

@SuppressWarnings("serial")
public class PANAlreadyRegisteredException extends RuntimeException {

	public PANAlreadyRegisteredException() {
		super();
	}

	public PANAlreadyRegisteredException(String message) {
		super(message);
	}

}
