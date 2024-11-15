package com.aurionpro.exception;

public class ProductNotFoundException extends Exception{
	
	public ProductNotFoundException() {
		super(" Product doesn't exist !");
	}
	
	public String getMessage() {
		return "Product Not Found! Check valid product";
	}

}
