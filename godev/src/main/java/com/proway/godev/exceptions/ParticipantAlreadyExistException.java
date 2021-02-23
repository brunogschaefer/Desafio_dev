package com.proway.godev.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParticipantAlreadyExistException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ParticipantAlreadyExistException (String firstName, String lastName) {
		super(String.format("O usuário %s já foi registrado.", firstName + " " + lastName));
	}
}
