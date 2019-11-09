package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Users;

public class UserController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	ArrayList<Users> users = new ArrayList<Users>();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HttpSession session = req.getSession();
		String email = req.getParameter("email");	

		if(req.getParameter("button").equalsIgnoreCase("Register")) {

			for(Users user : users) {
				if(user.getEmail().equalsIgnoreCase(email)) {

					req.setAttribute("message", "This email has already been taken!");
					RequestDispatcher rd = req.getRequestDispatcher("register-page.jsp");
					rd.forward(req, res);
					return ;
				}
			}

			Users user = new Users();
			user.setTelephone(Integer.parseInt(req.getParameter("tel")));
			user.setZipCode(Integer.parseInt(req.getParameter("zipCode")));
			user.setAdress(req.getParameter("adress"));
			user.setCity(req.getParameter("city"));
			user.setCountry(req.getParameter("country"));
			user.setEmail(email);
			user.setFirstName(req.getParameter("firstName"));
			user.setLastName(req.getParameter("lastName"));
			user.setPassword(req.getParameter("password"));
			String seller = req.getParameter("seller");
			user.setSeller( (seller != null && seller.equals("on"))?1:0 );
			users.add(user);

			req.setAttribute("Users", users);

			RequestDispatcher rd = req.getRequestDispatcher("login-page.jsp");
			rd.forward(req, res);

		}else if(req.getParameter("button").equalsIgnoreCase("Login")) {

			for(Users user : users) {
				if( user.getEmail().equalsIgnoreCase(req.getParameter("email")) &&
						user.getPassword().equalsIgnoreCase(req.getParameter("password"))) {
					session.setAttribute("user", email);
					session.setAttribute("username", user.getFirstName());
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.forward(req, res);
				}
			}

			req.setAttribute("message", "The email or password is wrong!");
			RequestDispatcher rd = req.getRequestDispatcher("login-page.jsp");
			rd.forward(req, res);

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

			req.setAttribute("message", "Changes have been made correctly!");
			RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
			rd.forward(req, res);

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
