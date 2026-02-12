package com.example.reservation.domain.valueobject;
import org.apache.commons.validator.routines.EmailValidator;
import com.example.reservation.domain.exception.InvalidEmailException;

import lombok.Getter;

@Getter
public class Email {
	private final String mail;
	public Email(String mail) {
		if(mail == null) throw new InvalidEmailException("mailがnullです");
		if(mail.isBlank()) throw new InvalidEmailException("mailがからです");
		if(!EmailValidator.getInstance().isValid(mail)) {
			throw new InvalidEmailException("mailの形式がおかしいです");
		}
		this.mail = mail;
	}
}
