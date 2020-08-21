package com.urlayasam.project.exceptions;

public class CompanyNotFoundException extends Exception {

	private static final long serialVersionUID = 2330632941975886027L;

	public CompanyNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
