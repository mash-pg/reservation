package com.example.reservation.domain.exception;

public class InvalidResourceIdException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidResourceIdException(String message) {
		super(message);
	}
}
