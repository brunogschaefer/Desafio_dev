package com.proway.godev.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.BAD_REQUEST)
public class MaxLimitReachedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MaxLimitReachedException (String messageError) {
		super(messageError);
	}
}
