package com.example.reservation.presentation.dto;

import java.time.LocalDateTime;

import com.example.reservation.application.dto.ReservationOutput;

import lombok.Getter;

@Getter
public class ReservationResponse {
	private final Long id;
	private final Long resourceId;
	private final Long userId;
	private final LocalDateTime startTime;
	private final LocalDateTime endTime;
	private final String status;
	
	public ReservationResponse(
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
	public static ReservationResponse fromOutput(
			ReservationOutput output
			) {
		
		return new ReservationResponse(
					output.getId(),
					output.getResourceId(),
					output.getUserId(),
					output.getStartTime(),
					output.getEndTime(),
					output.getStatus()
				);
		
	}
	
}
