package com.urlayasam.project.exceptions;

public class UsernameAndPasswordDoNotMatchException extends Exception {
	
	private static final long serialVersionUID = -2337950511833090934L;

	public UsernameAndPasswordDoNotMatchException(String errorMessage) {
		super(errorMessage);
	}

}
