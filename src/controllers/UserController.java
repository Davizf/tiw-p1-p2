package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

public class UserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ArrayList<User> users = new ArrayList<User>();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String email = req.getParameter("email");	
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("InsertBuyer");		
		UserManager manager = new UserManager();
		manager.setEntityManagerFactory(factory);
	
		
		if(req.getParameter("button").equalsIgnoreCase("Register")) {

			if(manager.checkUserEmail(email)) {
				req.setAttribute("message", "This email has already been taken!");
				RequestDispatcher rd = req.getRequestDispatcher("register-page.jsp");
				rd.forward(req, res);
				return ;
			}

			User user = new User();
			user.setPhone(Integer.parseInt(req.getParameter("tel")));
			user.setPostal_code(Integer.parseInt(req.getParameter("zipCode")));
			user.setAddress(req.getParameter("address"));
			user.setCity(req.getParameter("city"));
			user.setCountry(req.getParameter("country"));
			user.setEmail(email);
			user.setName(req.getParameter("firstName"));
			user.setSurnames(req.getParameter("lastName"));
			user.setPassword(req.getParameter("password"));
			String seller = req.getParameter("seller");
			user.setSeller( (seller != null && seller.equals("on"))?1:0 );
			
			try {
				manager.createUser(user);
			} catch (Exception e) {
				System.out.println("Descripci�n: " + e.getMessage());
			} 
			
			RequestDispatcher rd = req.getRequestDispatcher("login-page.jsp");
			rd.forward(req, res);

		}else if(req.getParameter("button").equalsIgnoreCase("Login")) {
			String password = req.getParameter("password");

			if(manager.verifyUser(email, password)) {
				User user = manager.getUser(email);
				session.setAttribute("user", email);
				session.setAttribute("username", user.getName());
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, res);
			}
		
			req.setAttribute("message", "The email or password is wrong!");
			RequestDispatcher rd = req.getRequestDispatcher("login-page.jsp");
			rd.forward(req, res);

		} else if(req.getParameter("button").equalsIgnoreCase("Save my profile")) {

			for(User user : users) {
				if( user.getEmail().equalsIgnoreCase(req.getParameter("email"))) {

					int zipCode =   Integer.parseInt(req.getParameter("zipCode")) ;
					int telephone = Integer.parseInt(req.getParameter("tel")); 
					user.setPhone(telephone);
					user.setPostal_code(zipCode);
					String firstName = req.getParameter("firstName");
					String lastName = req.getParameter("lastName");
					String adress = req.getParameter("adress");
					String city = req.getParameter("city");
					String country = req.getParameter("country");
					String password = req.getParameter("password");			
					user.setAddress(adress);
					user.setCity(city);
					user.setCountry(country);
					user.setName(firstName);
					user.setName(lastName);
					user.setPassword(password);
					session.setAttribute("username", user.getName());
				}
			}

			req.setAttribute("message", "Changes have been made correctly!");
			RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
			rd.forward(req, res);

		} else if(req.getParameter("button").equalsIgnoreCase("No")) {
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, res);

		} else if(req.getParameter("button").equalsIgnoreCase("Yes")) {

			for(User user : users) {
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
