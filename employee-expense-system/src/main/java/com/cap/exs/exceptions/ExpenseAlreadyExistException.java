package com.cap.exs.exceptions;

/**

Exception to be thrown while when an expense type already exist.

@since 1.0

@author Aman Pratap Singh

*/

@SuppressWarnings("serial")
public class ExpenseAlreadyExistException extends RuntimeException {
	
	public ExpenseAlreadyExistException() {
		super();
	}

	public ExpenseAlreadyExistException(String message) {
		super(message);
	}
	
	

}
