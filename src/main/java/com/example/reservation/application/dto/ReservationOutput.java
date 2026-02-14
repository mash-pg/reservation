package com.example.reservation.application.dto;

import java.time.LocalDateTime;

import com.example.reservation.domain.entity.Reservation;

import lombok.Getter;

@Getter
public class ReservationOutput {
	private final Long id;
	private final Long resourceId;
	private final Long userId;
	private final LocalDateTime startTime;
	private final LocalDateTime endTime;
	private final String status;
	
	public ReservationOutput(
			Long id,
			Long resourceId,
			Long userId,
			LocalDateTime startTime,
			LocalDateTime endTime,
			String status
			) {
		this.id = id;
		this.resourceId = resourceId;
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}
	public static ReservationOutput fromEntity(
			Reservation reservation
			) {
		
		return new ReservationOutput(
				    reservation.getId() != null ?	reservation.getId().getId() : null,
					reservation.getResourceId().getId(),
					reservation.getUserId().getId(),
					reservation.getTimeSlot().getStartTime(),
					reservation.getTimeSlot().getEndTime(),
					reservation.getStatus().name()
				);
		
	}
}
