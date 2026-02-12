package com.example.reservation.domain.entity;

import com.example.reservation.domain.exception.InvalidStateTransitionException;
import com.example.reservation.domain.valueobject.ReservationId;
import com.example.reservation.domain.valueobject.ReservationStatus;
import com.example.reservation.domain.valueobject.ResourceId;
import com.example.reservation.domain.valueobject.TimeSlot;
import com.example.reservation.domain.valueobject.UserId;

import lombok.Getter;

@Getter
public class Reservation {
	private final ReservationId id;
	private final ResourceId resourceId;
	private final UserId userId;
	private final TimeSlot timeSlot;
	private ReservationStatus status;
	
	public Reservation(
			ReservationId id,
			ResourceId resourceId,
			UserId userId,
			TimeSlot timeSlot,
			ReservationStatus status			
			) {
		this.id =id;
		this.resourceId = resourceId;
		this.userId = userId;
		this.timeSlot = timeSlot;
		this.status = status;
	}
	
	public void confirm() {
		if(this.status != ReservationStatus.PENDING) {
			throw new InvalidStateTransitionException("ReservationStatusがPENDINGでないです");
		}
		this.status = ReservationStatus.CONFIRMED;
	}
	
	public void cancel() {
		if(this.status != ReservationStatus.CONFIRMED) {
			throw new InvalidStateTransitionException("ReservationStatusがCONFIRMEDでないです");
		}
		this.status = ReservationStatus.CANCELLED;
	}
	
	public void complete() {
		if(this.status != ReservationStatus.CONFIRMED) {
			throw new InvalidStateTransitionException("ReservationStatusがCONFIRMEDでないです");
		}
		this.status = ReservationStatus.COMPLETED;
	}
	
}
