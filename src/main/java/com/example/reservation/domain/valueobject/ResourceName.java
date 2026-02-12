package com.example.reservation.domain.valueobject;
import com.example.reservation.domain.exception.InvalidResourceNameException;

import lombok.Getter;

@Getter
public class ResourceName {
	private final String name;
	public ResourceName(String name) {
		if(name == null) throw new InvalidResourceNameException("ResourceNameがnullです");
		if(name.isBlank()) throw new InvalidResourceNameException("ResourceNameが空です");
		this.name = name;
	}
}
