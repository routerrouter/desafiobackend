package com.softexpert.desafiobackend.controller.exception;

public class StatusChangeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public StatusChangeException(String exception) {
		super(exception);
	}

}
