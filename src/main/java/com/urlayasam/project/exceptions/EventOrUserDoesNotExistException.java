package com.urlayasam.project.exceptions;

public class EventOrUserDoesNotExistException extends Exception {
	
	private static final long serialVersionUID = -6073908630215055168L;

	public EventOrUserDoesNotExistException(String errorMessage) {
		super(errorMessage);
	}
}
