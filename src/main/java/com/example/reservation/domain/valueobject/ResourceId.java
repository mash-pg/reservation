package com.example.reservation.domain.valueobject;

import com.example.reservation.domain.exception.InvalidResourceIdException;

import lombok.Getter;

@Getter
public class ResourceId {
	private final Long id;
	public ResourceId(Long id) {
		if(id == null) throw new InvalidResourceIdException("ResourceIdがnullです");
		if(id <= 0) throw new InvalidResourceIdException("ResourceIdが0より小さいです");
		this.id = id;
	}
}
