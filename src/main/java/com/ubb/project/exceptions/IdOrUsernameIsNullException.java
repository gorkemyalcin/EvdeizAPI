package com.urlayasam.project.exceptions;

public class IdOrUsernameIsNullException extends Exception {
	
	private static final long serialVersionUID = 1950198460049646935L;

	public IdOrUsernameIsNullException(String errorMessage) {
		super(errorMessage);
	}
}
