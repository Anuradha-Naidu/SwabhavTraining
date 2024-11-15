package com.aurionpro.exception;

public class InsufficientStockException extends Exception{
	
	public InsufficientStockException(String productId, int availableStock, int requestedQuantity) {
        super("Insufficient stock for Product ID " + productId + ". Available: " + availableStock + ", Requested: " + requestedQuantity);
    }

}
