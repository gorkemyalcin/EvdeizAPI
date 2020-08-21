package com.urlayasam.project.exceptions;

public class EventNotFoundException extends Exception {
	
	private static final long serialVersionUID = 585538172346782072L;

	public EventNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
