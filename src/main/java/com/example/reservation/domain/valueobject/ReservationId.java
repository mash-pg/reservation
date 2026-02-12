package com.example.reservation.domain.valueobject;

import com.example.reservation.domain.exception.InvalidReservationIdException;

import lombok.Getter;

@Getter
public class ReservationId {
	private final Long id;
	public ReservationId(Long id) {
		if(id == null) throw new InvalidReservationIdException("ReservationIdがnullです");
		if(id <= 0) throw new InvalidReservationIdException("ReservationIdが0より小さいです");
		this.id = id;
	}
}
