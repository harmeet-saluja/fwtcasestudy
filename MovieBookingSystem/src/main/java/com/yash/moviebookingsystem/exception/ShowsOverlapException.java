package com.yash.moviebookingsystem.exception;

public class ShowsOverlapException extends RuntimeException {

	private static final long serialVersionUID = 1986053759823340305L;

	public ShowsOverlapException(String message) {
		super(message);
	}

}
