package controllers;

import java.util.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Product;

public class ProductController {

	public static Product getProduct(int id){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");		
		ProductManager manager = new ProductManager();
		manager.setEntityManagerFactory(factory);
		Product product = manager.getProduct(id);
		factory.close();
		return product;
	}
	
	public static List<Product> getProductsByCategory(String category){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");		
		ProductManager manager = new ProductManager();
		manager.setEntityManagerFactory(factory);
		List<Product> products = manager.getProductsByCategory(category);
		factory.close();
		return products;
	}
	
	public static List<Product> getProductsBySeller(String email){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");		
		ProductManager manager = new ProductManager();
		manager.setEntityManagerFactory(factory);
		List<Product> products = manager.getProductsBySeller(email);
		factory.close();
		return products;
	}


	public static List<Product> getLastProducts(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");		
		ProductManager manager = new ProductManager();
		manager.setEntityManagerFactory(factory);
		List<Product> products = manager.getLastProducts();
		factory.close();
		return products;
	}

	public static List<Product> getAllProducts(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");		
		ProductManager manager = new ProductManager();
		manager.setEntityManagerFactory(factory);
		List<Product> products = manager.getProducts();
		factory.close();
		return products;
	}

}