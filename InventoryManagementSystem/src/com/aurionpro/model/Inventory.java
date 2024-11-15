package com.aurionpro.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.aurionpro.exception.DuplicateProductException;
import com.aurionpro.exception.InsufficientStockException;
import com.aurionpro.exception.ProductNotFoundException;

public class Inventory {
	
	private Map<String, Product> products = new HashMap<>();
    private Map<String, Supplier> suppliers = new HashMap<>();
    private List<Transaction> transactions = new ArrayList<>();
    
    public synchronized void addProduct(Product product) throws DuplicateProductException {
        if (products.containsKey(product.getProductId())) { 
        	throw new DuplicateProductException();
        }
        products.put(product.getProductId(), product);
    }
    
    public synchronized void updateProduct(String productId, String name, String description, int quantity, double price) throws ProductNotFoundException {
        Product product = products.get(productId);
        if (product == null) {
        	throw new ProductNotFoundException();
        }
        product.setName(name);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setPrice(price);
    }
    
    public synchronized void deleteProduct(String productId) throws ProductNotFoundException {
        if (products.remove(productId) == null) throw new ProductNotFoundException();
    }

    public Product getProduct(String productId) throws ProductNotFoundException {
        Product product = products.get(productId);
        if (product == null) throw new ProductNotFoundException();
        return product;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    public void addSupplier(Supplier supplier) throws Exception {
        if (suppliers.containsKey(supplier.getSupplierId())) throw new Exception("Supplier already exists!");
        suppliers.put(supplier.getSupplierId(), supplier);
    }

    public void updateSupplier(String supplierId, String name, String contactInfo) throws Exception {
        Supplier supplier = suppliers.get(supplierId);
        if (supplier == null) throw new Exception("Supplier not found!");
        supplier.setName(name);
        supplier.setContactInfo(contactInfo);
    }

    public void deleteSupplier(String supplierId) throws Exception {
        if (suppliers.remove(supplierId) == null) throw new Exception("Supplier not found!");
    }

    public Supplier getSupplier(String supplierId) throws Exception {
        Supplier supplier = suppliers.get(supplierId);
        if (supplier == null) throw new Exception("Supplier not found!");
        return supplier;
    }

    public List<Supplier> getAllSuppliers() {
        return new ArrayList<>(suppliers.values());
    }
    
    public synchronized void addStock(String productId, int quantity) throws ProductNotFoundException {
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        product.setQuantity(product.getQuantity() + quantity);
        transactions.add(new Transaction(generateTransactionId(), productId, "add", quantity, new Date()));
    }

    public synchronized void removeStock(String productId, int quantity) throws ProductNotFoundException, InsufficientStockException {
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        if (product.getQuantity() < quantity) {
            throw new InsufficientStockException(productId, product.getQuantity(), quantity);
        }
        product.setQuantity(product.getQuantity() - quantity);
        transactions.add(new Transaction(generateTransactionId(), productId, "remove", quantity, new Date()));
    }

    public synchronized void updateStock(String productId, int quantity, String transactionType) throws Exception {
        Product product = products.get(productId);
        if (product == null) throw new Exception("Product not found!");
        
        if (transactionType.equals("Remove Stock") && product.getQuantity() < Math.abs(quantity)) {
            throw new Exception("Insufficient stock!");
        }
        
        product.setQuantity(product.getQuantity() + quantity);
        transactions.add(new Transaction(UUID.randomUUID().toString(), productId, transactionType, Math.abs(quantity)));
    }
    
    private String generateTransactionId() {
        return "TXN" + new Date().getTime();
    }

    public List<Transaction> getTransactionHistory(String productId) {
        return transactions.stream().filter(t -> t.getProductId().equals(productId)).collect(Collectors.toList());
    }

    
    public void saveData() throws IOException {
    	
    	try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("inventory.dat"))) {
            out.writeObject(products);
            out.writeObject(suppliers);
            out.writeObject(transactions);
        }
    }

    public void loadData() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("inventory.dat"))) {
            products = (Map<String, Product>) in.readObject();
            suppliers = (Map<String, Supplier>) in.readObject();
            transactions = (List<Transaction>) in.readObject();
        }
    }
    

    
    

}
