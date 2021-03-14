package com.cap.exs.exceptions;


/**

Exception to be thrown when trying to delete loginDetails of an existing employee.

@since 1.0

@author Himanshu Nagar

*/

@SuppressWarnings("serial")
public class EmployeeAssociatedException extends RuntimeException {

	public EmployeeAssociatedException() {
		super();
	}

	public EmployeeAssociatedException(String message) {
		super(message);
	}

}
