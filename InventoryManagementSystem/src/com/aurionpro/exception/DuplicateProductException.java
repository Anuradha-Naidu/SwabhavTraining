package com.aurionpro.exception;

public class DuplicateProductException extends Exception{
	
	public DuplicateProductException() {
		super("Duplicate Product exists!");
	}
	
	public String getMessage() {
		return "Duplicate Product exists! Change the product";
	}

}
