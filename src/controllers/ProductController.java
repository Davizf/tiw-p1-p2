package controllers;

import java.math.BigDecimal;

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

}