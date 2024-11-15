package com.aurionpro.test;

import java.util.Scanner;

import com.aurionpro.model.Inventory;
import com.aurionpro.model.Product;
import com.aurionpro.model.Supplier;

public class InventoryManagement {
	
	public static void main(String[] args) {
		Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        
        boolean running = true;
        
        while (running) {
            System.out.println("\nWelcome to the Inventory Management System");
            System.out.println("1. Product Management");
            System.out.println("2. Supplier Management");
            System.out.println("3. Transaction Management");
            System.out.println("4. Save Data");
            System.out.println("5. Load Data");
            System.out.println("6. Generate Reports");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    manageProducts(inventory, scanner);
                    break;
                case 2:
                    manageSuppliers(inventory, scanner);
                    break;
                case 3:
                    manageTransactions(inventory, scanner);
                    break;
                case 4:
                    try {
                        inventory.saveData();
                        System.out.println("Data saved successfully.");
                    } catch (Exception e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        inventory.loadData();
                        System.out.println("Data loaded successfully.");
                    } catch (Exception e) {
                        System.out.println("Error loading data: " + e.getMessage());
                    }
                    break;
                case 6:
                    generateReports(inventory);
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting Inventory Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void manageProducts(Inventory inventory, Scanner scanner) {
        System.out.println("\nProduct Management");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. View Product Details");
        System.out.println("5. View All Products");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String productId = scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Product Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Product Quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter Product Price: ");
                    double price = scanner.nextDouble();
                    Product product = new Product(productId, name, description, quantity, price);
                    inventory.addProduct(product);
                    System.out.println("Product added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Product ID to update: ");
                    productId = scanner.nextLine();
                    System.out.print("Enter New Product Name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter New Product Description: ");
                    description = scanner.nextLine();
                    System.out.print("Enter New Product Quantity: ");
                    quantity = scanner.nextInt();
                    System.out.print("Enter New Product Price: ");
                    price = scanner.nextDouble();
                    inventory.updateProduct(productId, name, description, quantity, price);
                    System.out.println("Product updated successfully.");
                    break;
                case 3:
                    System.out.print("Enter Product ID to delete: ");
                    productId = scanner.nextLine();
                    inventory.deleteProduct(productId);
                    System.out.println("Product deleted successfully.");
                    break;
                case 4:
                    System.out.print("Enter Product ID to view: ");
                    productId = scanner.nextLine();
                    Product productDetails = inventory.getProduct(productId);
                    System.out.println(productDetails);
                    break;
                case 5:
                    inventory.getAllProducts().forEach(System.out::println);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void manageSuppliers(Inventory inventory, Scanner scanner) {
        System.out.println("\nSupplier Management");
        System.out.println("1. Add Supplier");
        System.out.println("2. Update Supplier");
        System.out.println("3. Delete Supplier");
        System.out.println("4. View Supplier Details");
        System.out.println("5. View All Suppliers");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter Supplier ID: ");
                    String supplierId = scanner.nextLine();
                    System.out.print("Enter Supplier Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Supplier Contact Info: ");
                    String contactInfo = scanner.nextLine();
                    Supplier supplier = new Supplier(supplierId, name, contactInfo);
                    inventory.addSupplier(supplier);
                    System.out.println("Supplier added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Supplier ID to update: ");
                    supplierId = scanner.nextLine();
                    System.out.print("Enter New Supplier Name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter New Supplier Contact Info: ");
                    contactInfo = scanner.nextLine();
                    inventory.updateSupplier(supplierId, name, contactInfo);
                    System.out.println("Supplier updated successfully.");
                    break;
                case 3:
                    System.out.print("Enter Supplier ID to delete: ");
                    supplierId = scanner.nextLine();
                    inventory.deleteSupplier(supplierId);
                    System.out.println("Supplier deleted successfully.");
                    break;
                case 4:
                    System.out.print("Enter Supplier ID to view: ");
                    supplierId = scanner.nextLine();
                    Supplier supplierDetails = inventory.getSupplier(supplierId);
                    System.out.println(supplierDetails);
                    break;
                case 5:
                    inventory.getAllSuppliers().forEach(System.out::println);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void manageTransactions(Inventory inventory, Scanner scanner) {
        System.out.println("\nTransaction Management");
        System.out.println("1. Add Stock");
        System.out.println("2. Remove Stock");
        System.out.println("3. View Transaction History");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        
        try {
            System.out.print("Enter Product ID: ");
            String productId = scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter quantity to add: ");
                    int addQuantity = scanner.nextInt();
                    inventory.addStock(productId, addQuantity);
                    System.out.println("Stock added successfully.");
                    break;
                case 2:
                    System.out.print("Enter quantity to remove: ");
                    int removeQuantity = scanner.nextInt();
                    inventory.removeStock(productId, removeQuantity);
                    System.out.println("Stock removed successfully.");
                    break;
                case 3:
                    inventory.getTransactionHistory(productId).forEach(System.out::println);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void generateReports(Inventory inventory) {
        System.out.println("\nGenerating Reports...");
        System.out.println("Total Products: " + inventory.getAllProducts().size());
        double totalStockValue = inventory.getAllProducts().stream()
                .mapToDouble(product -> product.getQuantity() * product.getPrice())
                .sum();
        System.out.println("Total Stock Value: $" + totalStockValue);
    
	}
}
