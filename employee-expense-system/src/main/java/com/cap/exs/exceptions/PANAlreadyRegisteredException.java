package com.cap.exs.exceptions;

@SuppressWarnings("serial")
public class PANAlreadyRegisteredException extends RuntimeException {

	public PANAlreadyRegisteredException() {
		super();
	}

	public PANAlreadyRegisteredException(String message) {
		super(message);
	}

}
