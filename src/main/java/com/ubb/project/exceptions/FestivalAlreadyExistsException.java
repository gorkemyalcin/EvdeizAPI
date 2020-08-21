package com.urlayasam.project.exceptions;

public class FestivalAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 5703702966251637475L;

	public FestivalAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
}
