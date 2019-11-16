package controllers;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Order;
import model.Product;
import model.User;

public class OrderController {

	public OrderController() {
		// TODO Auto-generated constructor stub
	}
	
	public static User getUserInformation(String email){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");		
		UserManager manager = new UserManager();
		manager.setEntityManagerFactory(factory);
		User user = manager.getUser(email);
		factory.close();
		return user;
	}
}
