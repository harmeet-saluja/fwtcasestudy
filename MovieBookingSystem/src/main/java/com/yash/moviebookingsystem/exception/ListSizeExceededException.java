package com.yash.moviebookingsystem.exception;

public class ListSizeExceededException extends RuntimeException {

	private static final long serialVersionUID = -5542323714069197070L;

	public ListSizeExceededException(String message) {
		super(message);
	}

}
