package com.example.reservation.domain.valueobject;
import com.example.reservation.domain.exception.InvalidUserIdException;

import lombok.Getter;

@Getter
public class UserId {
	private final Long id;
	public UserId(Long id) {
		if(id == null) throw new InvalidUserIdException("UserIdがnullです");
		if(id <= 0) throw new InvalidUserIdException("UserIdが0より小さいです");
		this.id = id;
	}
}
