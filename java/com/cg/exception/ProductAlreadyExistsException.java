package com.cg.exception;

public class ProductAlreadyExistsException extends RuntimeException {

	public ProductAlreadyExistsException(String msg) {
		super(msg);
	}

}
