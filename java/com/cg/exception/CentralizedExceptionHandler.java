package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {
	
//	@ResponseStatus(HttpStatus.FOUND)
	@ExceptionHandler(ProductAlreadyExistsException.class)
	public String handleProductAlreadyExists(ProductAlreadyExistsException e) {
		return e.getMessage();
	}
	
//	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ExceptionHandler(InvalidProductException.class)
	public String handleInvalidProduct(InvalidProductException e) {
		return e.getMessage();
	}
	
//	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ExceptionHandler(ProductNotPresentException.class)
	public String handleProductNotPresent(ProductNotPresentException e) {
		return e.getMessage();
	}
	
//	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ExceptionHandler(RetailerNotFoundException.class)
	public String handleRetailerNotFound(RetailerNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(RetailerAlreadyExistsException.class)
	public String handleRetailerNotFound(RetailerAlreadyExistsException e) {
		return e.getMessage();
	}
	
//	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ExceptionHandler(AddressNotFoundException.class)
	public String handleAddressNotFound(AddressNotFoundException e) {
		return e.getMessage();
	}
	
//	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ExceptionHandler(Exception.class)
	public String handleError(Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}
}
