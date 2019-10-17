package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Users;

public class UserController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Users> users = new ArrayList<Users>();
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String email = req.getParameter("email");	
		
		if(req.getParameter("button").equalsIgnoreCase("Register")) {

			for(Users user : users) {
				if(user.getEmail().equalsIgnoreCase(email)) {
					
					// redirigir a la pagina blanca y decir que el usuario ya existe
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.forward(req, res);
				}
			}
			
			Users user = new Users();
			int zipCode =   Integer.parseInt(req.getParameter("zipCode")) ;
			int telephone = Integer.parseInt(req.getParameter("tel")); 
			user.setTelephone(telephone);
			user.setZipCode(zipCode);
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String adress = req.getParameter("adress");
			String city = req.getParameter("city");
			String country = req.getParameter("country");
			String password = req.getParameter("password");			
			
			user.setAdress(adress);
			user.setCity(city);
			user.setCountry(country);
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);

			user.setPassword(password);
			users.add(user);
			
			
			req.setAttribute("Users", users);
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, res);
				
		}else if(req.getParameter("button").equalsIgnoreCase("Login")) {
			
			for(Users user : users) {
				if(user.getPassword().equalsIgnoreCase(req.getParameter("password"))) {
					RequestDispatcher rd = req.getRequestDispatcher("blank.jsp");
					rd.forward(req, res);
				}
			}
			
		}
		

		
	}

}
