package com.urlayasam.project.exceptions;

public class NullParameterException extends Exception {

	private static final long serialVersionUID = -4223061363872161073L;

	public NullParameterException(String errorMessage) {
		super(errorMessage);
	}
}