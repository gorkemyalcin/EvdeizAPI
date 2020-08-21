package com.urlayasam.project.exceptions;

public class FavoriteNotFoundException extends Exception {
	
	private static final long serialVersionUID = -8038073300550529053L;

	public FavoriteNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
