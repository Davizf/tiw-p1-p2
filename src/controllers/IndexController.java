package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jdbc.info.InformationProperties;
import model.Product;
import models.Category;


public class IndexController {

	public static ArrayList<Category> getCategories(){
		ArrayList<Category> categories = new ArrayList<Category>();
		Category category = null;
		String query = "SELECT * FROM Categories";
		String userName = InformationProperties.getStrUser();
		String password = InformationProperties.getStrPassword();
		String url = "jdbc:mysql://localhost/" + InformationProperties.getStrDatabaseName() + "?user=" + userName
				+ "&password=" + password + "&useSSL=false&serverTimezone=UTC";
		ResultSet res = null;
		
		try {
			Class.forName(InformationProperties.getStrClassDriver());

			Connection connection = DriverManager.getConnection(url);

			Statement myStatement = connection.createStatement();

			res = myStatement.executeQuery(query);
			while(res.next()) {
				category = new Category();
				category.setName(res.getString("name"));
				categories.add(category);
			}
			res.close();
			myStatement.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLWarning sqlWarning) {
			while (sqlWarning != null) {
				System.out.println("Error: " + sqlWarning.getErrorCode());
				System.out.println("Descripci�n: " + sqlWarning.getMessage());
				System.out.println("SQLstate: " + sqlWarning.getSQLState());
				sqlWarning = sqlWarning.getNextWarning();
			}
		} catch (SQLException sqlException) {
			while (sqlException != null) {
				System.out.println("Error: " + sqlException.getErrorCode());
				System.out.println("Descripci�n: " + sqlException.getMessage());
				System.out.println("SQLstate: " + sqlException.getSQLState());
				sqlException = sqlException.getNextException();
			}
		}
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");		
		ProductManager manager = new ProductManager();
		manager.setEntityManagerFactory(factory);

		List<Product> products = manager.getProducts();
		
		for (Product product : products) {
			System.out.println(product.getName());
		}
		
		return categories;
	}

}
