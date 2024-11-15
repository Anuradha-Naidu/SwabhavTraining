package com.aurionpro.exception;

public class SupplierNotFoundException extends Exception{
	
	public SupplierNotFoundException(String supplierId) {
        super("Supplier with ID " + supplierId + " not found in the inventory.");
    }

}
