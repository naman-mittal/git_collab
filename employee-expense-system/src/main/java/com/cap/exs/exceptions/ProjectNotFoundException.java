package com.cap.exs.exceptions;

/**

Exception to be thrown when a project, searched with id,  is not found

@since 1.0

@author Ishan Agarwal

*/

@SuppressWarnings("serial")
public class ProjectNotFoundException extends RuntimeException {

	public ProjectNotFoundException() {
		super();
	}

	public ProjectNotFoundException(String message) {
		super(message);
	}
		
}
