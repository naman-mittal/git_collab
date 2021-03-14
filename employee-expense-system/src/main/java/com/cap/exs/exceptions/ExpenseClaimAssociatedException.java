package com.cap.exs.exceptions;

/**

Exception to be thrown when an object associated with an expense claim is deleted.

@since 1.0

@author Naman Mittal

*/

@SuppressWarnings("serial")
public class ExpenseClaimAssociatedException extends RuntimeException {

	public ExpenseClaimAssociatedException() {
		super();
	}

	public ExpenseClaimAssociatedException(String message) {
		super(message);
	}

}
