package com.cap.exs.exceptions;

/**

Exception to be thrown when trying to find an employee which does not exist.

@since 1.0

@author Naman Mittal

*/

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException() {
		super();
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}
		
}
