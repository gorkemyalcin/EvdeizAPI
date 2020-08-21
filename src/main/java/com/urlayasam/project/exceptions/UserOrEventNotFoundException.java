package com.urlayasam.project.exceptions;

public class UserOrEventNotFoundException extends Exception {

	private static final long serialVersionUID = 795668455160206066L;

	public UserOrEventNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
