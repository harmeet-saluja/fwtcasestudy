package com.yash.moviebookingsystem.exception;

public class FileNameIsEmptyException extends RuntimeException {

	private static final long serialVersionUID = -1488641771751958477L;

	public FileNameIsEmptyException(String message) {
		super(message);
	}

}
