package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Users;

public class UserController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<Users> users = new ArrayList<Users>();
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		String email = req.getParameter("email");	
		
		if(req.getParameter("button").equalsIgnoreCase("Register")) {

			for(Users user : users) {
				if(user.getEmail().equalsIgnoreCase(email)) {
					
					String message = "This email has already been taken!";
					res.sendRedirect("register-page.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
					return ;
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
			
			RequestDispatcher rd = req.getRequestDispatcher("login-page.jsp");
			rd.forward(req, res);
				
		}else if(req.getParameter("button").equalsIgnoreCase("Login")) {
		
			for(Users user : users) {
				if( user.getPassword().equalsIgnoreCase(req.getParameter("email")) &&
						user.getPassword().equalsIgnoreCase(req.getParameter("password"))) {
					session.setAttribute("user", email);
					session.setAttribute("username", user.getFirstName());
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.forward(req, res);
				}
			}
			
			
			
			String message = "The email or password is wrong!";
			res.sendRedirect("login-page.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			
			
			
		} else if(req.getParameter("button").equalsIgnoreCase("Save my profile")) {
		
			for(Users user : users) {
				if( user.getEmail().equalsIgnoreCase(req.getParameter("email"))) {
					
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
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setPassword(password);
					session.setAttribute("username", user.getFirstName());
				}
			}
			
			String message = "Changes have been made correctly!";
			res.sendRedirect("profile.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			
			
		} else if(req.getParameter("button").equalsIgnoreCase("No")) {
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, res);
			
		} else if(req.getParameter("button").equalsIgnoreCase("Yes")) {
			
			for(Users user : users) {
				if( user.getEmail().equalsIgnoreCase(  (String) session.getAttribute("user")  )) {
					users.remove(user);
				}
			}
			session.invalidate();
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, res);
			
		}
		
	}

}
