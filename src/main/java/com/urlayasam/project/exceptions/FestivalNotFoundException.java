package com.urlayasam.project.exceptions;

public class FestivalNotFoundException extends Exception {

	private static final long serialVersionUID = 4858849763806000065L;

	public FestivalNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}