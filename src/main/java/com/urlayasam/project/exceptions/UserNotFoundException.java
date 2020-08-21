package com.urlayasam.project.exceptions;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -3137085893724418870L;

	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
