package com.example.reservation.domain.exception;

public class InvalidReservationIdException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidReservationIdException(String message) {
		super(message);
	}
}
