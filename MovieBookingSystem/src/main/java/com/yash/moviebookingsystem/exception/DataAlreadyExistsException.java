package com.yash.moviebookingsystem.exception;

public class DataAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1665191673717099512L;

	public DataAlreadyExistsException(String message) {
		super(message);
	}
}
