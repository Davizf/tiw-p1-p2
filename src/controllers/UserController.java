package controllers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.User;

public class UserController {

	public UserController() {
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
