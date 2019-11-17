package controllers;

import java.util.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Product;

public class ProductController {
	
	public static final int MAX_STOCK=2147483647, MIN_STOCK=0;

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
		List<Product> products = manager.getAllProducts();
		factory.close();
		return products;
	}

	public static boolean deleteProduct(int id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");		
		ProductManager manager = new ProductManager();
		manager.setEntityManagerFactory(factory);
		Product p = manager.getProduct(id);
		try {
			manager.deleteProduct(p);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			factory.close();
		}
		return true;
	}

	public static boolean modifyProduct(Product p) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");		
		ProductManager manager = new ProductManager();
		manager.setEntityManagerFactory(factory);
		try {
			manager.updateProduct(p);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			factory.close();
		}
		return true;
	}

	public static int addProduct(Product p) {// TODO
		int id=-1;
		return id;
	}

	public static boolean verifyStock(int stock) {
		return stock<MAX_STOCK && stock>MIN_STOCK;
	}

}