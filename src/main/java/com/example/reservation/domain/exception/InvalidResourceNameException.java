package com.example.reservation.domain.exception;

public class InvalidResourceNameException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidResourceNameException(String message) {
		super(message);
	}
}
