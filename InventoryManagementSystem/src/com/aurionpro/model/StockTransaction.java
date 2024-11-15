package com.aurionpro.model;

public class StockTransaction implements Runnable{
	
	private Inventory inventory;
    private String productId;
    private int quantity;
    private String transactionType;
    
    public StockTransaction(Inventory inventory, String productId, int quantity, String transactionType) {
        this.inventory = inventory;
        this.productId = productId;
        this.quantity = quantity;
        this.transactionType = transactionType;
    }

	@Override
	public void run() {
		
		try{
            if(transactionType.equalsIgnoreCase("add")){
                inventory.addStock(productId, quantity);
                System.out.println("Added " + quantity + " to product " + productId + " by " + Thread.currentThread().getName());
            }else if(transactionType.equalsIgnoreCase("remove")){
                inventory.removeStock(productId, quantity);
                System.out.println("Removed " + quantity + " from product " + productId + " by " + Thread.currentThread().getName());
            }
        }catch(Exception e){
            System.out.println("Transaction failed for product " + productId + ": " + e.getMessage());
        }
		
	}

}
