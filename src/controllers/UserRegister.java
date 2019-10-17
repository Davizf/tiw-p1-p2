package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ProductInWishList;
import models.Users;

public class UserRegister extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Users> users = new ArrayList<Users>();
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		String email = req.getParameter("email");
			
		for(Users user : users) {
			if(user.getEmail().equalsIgnoreCase(email)) {
				
				// redirigir a la pagina blanca y decir que el usuario ya existe
				RequestDispatcher rd = req.getRequestDispatcher("blanck.jsp");
				rd.forward(req, res);
			}
		}
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String adress = req.getParameter("adress");
		String city = req.getParameter("city");
		String country = req.getParameter("country");
		int zipCode =   Integer.parseInt(req.getParameter("zipCode")) ;
		int telephone = Integer.parseInt(req.getParameter("tel")); 
				
		Users user = new Users();
		user.setAdress(adress);
		user.setCity(city);
		user.setCountry(country);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setTelephone(telephone);
		user.setZipCode(zipCode);
		
		
		users.add(user);
		req.setAttribute("Users", users);
			
		RequestDispatcher rd = req.getRequestDispatcher("blanck.jsp");
		rd.forward(req, res);
			

		
	}

}
