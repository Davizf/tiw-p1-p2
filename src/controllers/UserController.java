package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Product;
import model.User;
import model.WishList;

public class UserController {

	public static User getUserInformation(String email) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");		
		UserManager manager = new UserManager();
		manager.setEntityManagerFactory(factory);
		User user = manager.getUser(email);
		factory.close();
		return user;
	}
	
	public static void modifyUser(User user) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");		
		UserManager manager = new UserManager();
		manager.setEntityManagerFactory(factory);
		try {
			manager.updateUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		factory.close();
	}
	
	public static ArrayList<Product> getWishListProduct(User user) {
		List<WishList> wishLists = user.getWishlists();
		ArrayList<Product> wishListProducts =  new ArrayList<Product>();
		
		for(WishList wishList : wishLists) {
			wishListProducts.add(wishList.getProductBean());
		}
		
		return wishListProducts;
	}
}