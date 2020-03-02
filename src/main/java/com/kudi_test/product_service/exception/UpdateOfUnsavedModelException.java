package com.kudi_test.product_service.exception;

public class UpdateOfUnsavedModelException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UpdateOfUnsavedModelException(String message) {
		super(message);
	}

	public UpdateOfUnsavedModelException(String message, Throwable t) {
		super(message, t);
	}
}
