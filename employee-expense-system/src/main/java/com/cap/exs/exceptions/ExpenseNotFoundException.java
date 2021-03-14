package com.cap.exs.exceptions;

/**

Exception to be thrown when an expense with non-existing expense type is searched.

@since 1.0

@author Aman Pratap Singh

*/

@SuppressWarnings("serial")
public class ExpenseNotFoundException  extends RuntimeException{
	
	public ExpenseNotFoundException() {
		super();
	}

	public ExpenseNotFoundException(String message) {
		super(message);
	}

}
