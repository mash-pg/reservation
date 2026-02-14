package com.example.reservation.application.dto;

import com.example.reservation.domain.entity.Resource;

import lombok.Getter;

@Getter
public class ResourceOutput {
	private final Long id;
	private final String name;
	
	public ResourceOutput(Long id,String name) {
		this.id = id;
		this.name = name;
	}
	public static ResourceOutput fromEntity(
			Resource resource
			) {
		
		return new ResourceOutput(
				resource.getId().getId(),
				resource.getName().getName()
				);
		
	}
}
