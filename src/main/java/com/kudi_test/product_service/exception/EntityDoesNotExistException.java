package com.kudi_test.product_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The requested entity could not be found")
public class EntityDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityDoesNotExistException(String message) {
		super(message);
	}

	public EntityDoesNotExistException( ) {
		super("The entity cannot be found");
	}
}
