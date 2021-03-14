package com.cap.exs.exceptions;

/**

 Exception to be thrown when a certain role is not found in the database.
 
 @since 1.0
 
 @author Naman Mittal

*/
@SuppressWarnings("serial")
public class RoleNotFoundException extends RuntimeException {

	public RoleNotFoundException() {
		super();
	}

	public RoleNotFoundException(String message) {
		super(message);
	}

}
