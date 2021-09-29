package com.cg.exception;

public class ProductNotPresentException extends RuntimeException {

	public ProductNotPresentException(String msg) {
		super(msg);
	}

}
