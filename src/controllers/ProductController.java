package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;

import model.Category;
import model.Product;
import model.User;

public class ProductController {

	// TODO Juanjo
	public static Product getProduct(int id){
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
		User u = new User();
		u.setEmail("pepe@gmail.com");
		p.setUserBean(u);
		return p;
	}

	// TODO Juanjo
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
		User u = new User();
		u.setEmail("pepe@gmail.com");
		p.setUserBean(u);
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
		User u2 = new User();
		u2.setEmail("pepe@gmail.com");
		p2.setUserBean(u2);
		l.add(p2);

		return l;
	}

}