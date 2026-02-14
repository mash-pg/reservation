package com.example.reservation.presentation.dto;

import com.example.reservation.application.dto.ResourceOutput;

import lombok.Getter;

@Getter
public class ResourceResponse {
	private final Long id;
	private final String name;
	
	public ResourceResponse(Long id,String name) {
		this.id = id;
		this.name = name;
	}
	public static ResourceResponse fromOutput(
			ResourceOutput output
			) {
		
		return new ResourceResponse(
				output.getId(),
				output.getName()
				);
		
	}
	
	
}
