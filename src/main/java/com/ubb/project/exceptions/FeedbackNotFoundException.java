package com.urlayasam.project.exceptions;

public class FeedbackNotFoundException extends Exception {

	private static final long serialVersionUID = -2675904373006027944L;

	public FeedbackNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
