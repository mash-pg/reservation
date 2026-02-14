package com.example.reservation.domain.entity;

import com.example.reservation.domain.valueobject.ResourceId;
import com.example.reservation.domain.valueobject.ResourceName;

import lombok.Getter;

@Getter
public class Resource {
	private final ResourceId id;
	private final ResourceName name;
	
	public Resource(ResourceId id,ResourceName name) {
		this.id = id;
		this.name = name;
	}
	
}
