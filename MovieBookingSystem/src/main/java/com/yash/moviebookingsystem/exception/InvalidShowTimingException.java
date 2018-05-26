package com.yash.moviebookingsystem.exception;

public class InvalidShowTimingException extends RuntimeException {

	private static final long serialVersionUID = -3250777895903985090L;

	public InvalidShowTimingException(String message) {
		super(message);
	}

}
