package com.example.reservation.presentation.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ReservationRequest {
	private Long resourceId;
	private Long userId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
}
