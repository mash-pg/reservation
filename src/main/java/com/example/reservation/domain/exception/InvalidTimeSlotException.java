package com.example.reservation.domain.exception;

public class InvalidTimeSlotException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidTimeSlotException(String message) {
		super(message);
	}
}
