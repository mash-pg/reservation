package com.example.reservation.presentation.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReservationRequest {
	@NotNull
	private Long resourceId;
	@NotNull
	private Long userId;
	@NotNull
	@Future
	private LocalDateTime startTime;
	@NotNull
	@Future
	private LocalDateTime endTime;
}
