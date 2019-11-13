package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Category;
import model.Product;


public class ProductController {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");		
	static ProductManager manager = new ProductManager();

	
	public static Product getProduct(int id){
		manager.setEntityManagerFactory(factory);
		Product p=new Product();
		p.setId(1);
		p.setName("bag");
		p.setPrice(new BigDecimal(44.99));
		p.setDescription("Bag premium quality Bag premium quality Bag premium quality");
		p.setShortDescription("Bag premium quality");
		p.setImagePath("/tiw-p1/images/product01.jpg");
		p.setStock(1);
		Category c = new Category();
		c.setName("bags & shoes");
		p.setCategoryBean(c);
		return p;
	}

	
	public static ArrayList<Product> getLastsProducts(){
		ArrayList<Product> l=new ArrayList<Product>();
		Product p=new Product();
		p.setId(1);
		p.setName("bag");
		p.setPrice(new BigDecimal(44.99));
		p.setDescription("Bag premium quality Bag premium quality Bag premium quality");
		p.setShortDescription("Bag premium quality");
		p.setImagePath("/tiw-p1/images/product01.jpg");
		p.setStock(1);
		Category c = new Category();
		c.setName("bags & shoes");
		p.setCategoryBean(c);
		l.add(p);

		Product p2=new Product();
		p2.setId(2);
		p2.setName("watch");
		p2.setPrice(new BigDecimal(49.99));
		p2.setDescription("Watch premium quality Watch premium quality Watch premium quality");
		p2.setShortDescription("Watch premium quality");
		p2.setImagePath("/tiw-p1/images/product02.jpg");
		p2.setStock(10);
		Category c2 = new Category();
		c2.setName("jewelry & watches");
		p2.setCategoryBean(c2);
		l.add(p2);

		return l;
	}
	
	public static List<Product> getAllProducts(){
		manager.setEntityManagerFactory(factory);
		
		return manager.getProducts();
	}

}