package com.urlayasam.project.exceptions;

public class UserAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 4616806377962732003L;

	public UserAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
}
