package controllers;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.ProductInCart;
import model.User;

public class OrderController {

	public static boolean checkProductsStock(ArrayList<ProductInCart> productsInCart){
		for(ProductInCart productInCart : productsInCart) {
			if(productInCart.getQuantity() > productInCart.getProduct().getStock()) {
				return false;
			}	
		}
		
		return true;		
	}

}