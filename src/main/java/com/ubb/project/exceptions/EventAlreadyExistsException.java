package com.urlayasam.project.exceptions;

public class EventAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 3484174070541670589L;

	public EventAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
}
