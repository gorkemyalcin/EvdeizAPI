package com.urlayasam.project.exceptions;

public class CommentNotFoundException extends Exception {

	private static final long serialVersionUID = 8740077929996067320L;

	public CommentNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
