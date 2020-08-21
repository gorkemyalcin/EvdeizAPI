package com.urlayasam.project.exceptions;

public class CompanyAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -8217966980079719766L;

	public CompanyAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
}
