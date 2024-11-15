package com.aurionpro.model;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
	
	private String transactionId;
	private String productId;
	private String type;;
	private int quantity;
	private Date date;
	
	public Transaction(String transactionId, String productId, String type, int quantity, Date date) {
		super();
		this.transactionId = transactionId;
		this.productId = productId;
		this.type = type;
		this.quantity = quantity;
		this.date = date;
	}

	public Transaction(String string, String productId2, String transactionType, int abs) {
		// TODO Auto-generated constructor stub
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", productId=" + productId + ", type=" + type
				+ ", quantity=" + quantity + ", date=" + date + "]";
	}
	
	
	
	
	
	
	


}
