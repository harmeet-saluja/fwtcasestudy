package com.yash.moviebookingsystem.exception;

public class FieldLimitExceededException extends RuntimeException {

	private static final long serialVersionUID = 2479999663825317793L;

	public FieldLimitExceededException(String message) {
		super(message);
	}

}
