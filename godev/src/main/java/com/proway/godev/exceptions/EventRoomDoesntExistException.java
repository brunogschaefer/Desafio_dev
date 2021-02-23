package com.proway.godev.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.BAD_REQUEST)
public class EventRoomDoesntExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EventRoomDoesntExistException (String messageError) {
		super(messageError);
	}
}
