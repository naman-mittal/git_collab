package com.cap.exs.exceptions;


public class ExpenseAssociatedException extends RuntimeException {
	
	public ExpenseAssociatedException()
	{
		super();
	}

	public ExpenseAssociatedException(String message)
	{
		super(message);
	}
}
