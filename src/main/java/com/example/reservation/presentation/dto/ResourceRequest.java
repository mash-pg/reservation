package com.example.reservation.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ResourceRequest {
	@NotNull
	@NotBlank
	private String name;
}
