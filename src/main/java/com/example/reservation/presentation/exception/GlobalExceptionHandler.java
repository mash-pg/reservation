package com.example.reservation.presentation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.reservation.domain.exception.InvalidEmailException;
import com.example.reservation.domain.exception.InvalidReservationIdException;
import com.example.reservation.domain.exception.InvalidResourceIdException;
import com.example.reservation.domain.exception.InvalidResourceNameException;
import com.example.reservation.domain.exception.InvalidStateTransitionException;
import com.example.reservation.domain.exception.InvalidTimeSlotException;
import com.example.reservation.domain.exception.InvalidUserIdException;
import com.example.reservation.domain.exception.ReservationNotFoundException;
import com.example.reservation.domain.exception.ResourceNotFoundException;
import com.example.reservation.presentation.dto.ErrorResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<ErrorResponse> handleInvalidInvalidEmailException(InvalidEmailException e){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
	}
	@ExceptionHandler(InvalidReservationIdException.class)
	public ResponseEntity<ErrorResponse> handleInvalidReservationIdException(InvalidReservationIdException e){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
	}
	@ExceptionHandler(InvalidResourceIdException.class)
	public ResponseEntity<ErrorResponse> handleInvalidResourceIdException(InvalidResourceIdException e){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
	}
	
	@ExceptionHandler(ReservationNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleReservationNotFoundException(ReservationNotFoundException e){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(e.getMessage()));
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(e.getMessage()));
	}
	
	@ExceptionHandler(InvalidStateTransitionException.class)
	public ResponseEntity<ErrorResponse> handleInvalidStateTransitionException(InvalidStateTransitionException e){
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(e.getMessage()));
	}

	@ExceptionHandler(InvalidTimeSlotException.class)
	public ResponseEntity<ErrorResponse> handleInvalidInvalidTimeSlotException(InvalidTimeSlotException e){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
	}

	@ExceptionHandler(InvalidResourceNameException.class)
	public ResponseEntity<ErrorResponse> handleInvalidResourceNameException(InvalidResourceNameException e){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
	}
	
	@ExceptionHandler(InvalidUserIdException.class)
	public ResponseEntity<ErrorResponse> handleInvalidUserIdException(InvalidUserIdException e){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		String message = e.getBindingResult()
				.getFieldErrors()
				.get(0)
				.getDefaultMessage();
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(message));
	}
}
