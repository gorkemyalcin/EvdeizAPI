package com.urlayasam.project.exceptions;

public class EventUpdateRequestHasNullValuesException extends Exception {

	private static final long serialVersionUID = 6529454824338974803L;

	public EventUpdateRequestHasNullValuesException(String errorMessage) {
		super(errorMessage);
	}
}
