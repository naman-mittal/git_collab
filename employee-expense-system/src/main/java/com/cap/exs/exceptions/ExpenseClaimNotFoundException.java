package com.cap.exs.exceptions;

/**

Exception to be thrown when a non-existing expense claim is searched.

@since 1.0

@author Harsh Bansal
@author Ashutosh Upadhyay

*/

@SuppressWarnings("serial")
public class ExpenseClaimNotFoundException extends RuntimeException {
	public ExpenseClaimNotFoundException() {
		super();
	}

	public ExpenseClaimNotFoundException(String message) {
		super(message);
	}
}
