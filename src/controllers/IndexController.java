package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;

import jdbc.info.InformationProperties;
import models.Category;

public class IndexController {

	public static ArrayList<Category> getCategories(){
		ArrayList<Category> categories = new ArrayList<Category>();
		String query = "SELECT * FROM Categories";
		String userName = InformationProperties.getStrUser();
		String password = InformationProperties.getStrPassword();
		String url = "jdbc:mysql://localhost/" + InformationProperties.getStrDatabaseName() + "?user=" + userName
				+ "&password=" + password + "&useSSL=false&serverTimezone=UTC";
		
		try {
			Class.forName(InformationProperties.getStrClassDriver());

			Connection connection = DriverManager.getConnection(url);

			Statement myStatement = connection.createStatement();

			
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
		
		return categories;
	}

}
