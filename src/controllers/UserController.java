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
import model.User;

public class UserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ArrayList<User> users = new ArrayList<User>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String op = req.getParameter("operation");
		if (op != null && op.equalsIgnoreCase("log_out"))
			req.getSession().setAttribute("user", null);
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String email = req.getParameter("email");	

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");		
		UserManager manager = new UserManager();
		manager.setEntityManagerFactory(factory);


		if(req.getParameter("operation").equalsIgnoreCase("Register")) {

			if(manager.checkUserEmail(email)) {
				req.setAttribute("message", "This email has already been taken!");
				RequestDispatcher rd = req.getRequestDispatcher("register-page.jsp");
				rd.forward(req, res);
			} else {
				User user = new User();
				user.setPhone(Integer.parseInt(req.getParameter("tel")));
				user.setPostalCode(Integer.parseInt(req.getParameter("zipCode")));
				user.setAddress(req.getParameter("address"));
				user.setCity(req.getParameter("city"));
				user.setCountry(req.getParameter("country"));
				user.setEmail(email);
				user.setName(req.getParameter("firstName"));
				user.setSurnames(req.getParameter("lastName"));
				user.setPassword(req.getParameter("password"));
				user.setCreditCard(req.getParameter("card"));
				user.setCreditCardExpiration(req.getParameter("cardExpire"));
				user.setCredit_card_CVV(Integer.parseInt(req.getParameter("cvv")));
				String seller = req.getParameter("seller");
				user.setSeller( (int) ((seller != null && seller.equals("on"))?1:0) );

				try {
					manager.createUser(user);
				} catch (Exception e) {
					System.out.println("Descripci�n: " + e.getMessage());
				} 

				RequestDispatcher rd = req.getRequestDispatcher("login-page.jsp");
				rd.forward(req, res);
			}
		}else if(req.getParameter("operation").equalsIgnoreCase("Login")) {
			String password = req.getParameter("password");

			if(manager.verifyUser(email, password)) {
				User user = manager.getUser(email);
				session.setAttribute("user", email);
				session.setAttribute("username", user.getName());
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, res);
			} else {
				req.setAttribute("message", "The email or password is wrong!");
				RequestDispatcher rd = req.getRequestDispatcher("login-page.jsp");
				rd.forward(req, res);
			}

		} else if(req.getParameter("operation").equalsIgnoreCase("My Profile")) {

			User user = manager.getUser((String) session.getAttribute("user"));

			req.setAttribute("user_information", (User) user);
			RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
			rd.forward(req, res);

		} else if(req.getParameter("operation").equalsIgnoreCase("Save my profile")) {

			User user = new User();
			user.setPhone(Integer.parseInt(req.getParameter("tel")));
			user.setPostalCode(Integer.parseInt(req.getParameter("zipCode")));
			user.setAddress(req.getParameter("address"));
			user.setCity(req.getParameter("city"));
			user.setCountry(req.getParameter("country"));
			user.setEmail(email);
			user.setName(req.getParameter("firstName"));
			user.setSurnames(req.getParameter("lastName"));
			user.setPassword(req.getParameter("password"));
			user.setCreditCard(req.getParameter("card"));
			user.setCreditCardExpiration(req.getParameter("cardExpire"));
			user.setCredit_card_CVV(Integer.parseInt(req.getParameter("cvv")));
			session.setAttribute("username", user.getName());

			try {
				manager.updateUser(user);
			} catch (Exception e) {
				System.out.println("Descripci�n: " + e.getMessage());
			} 
			req.setAttribute("message", "Changes have been made correctly!");
			req.setAttribute("user_information", (User) user);
			RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
			rd.forward(req, res);

		} else if(req.getParameter("operation").equalsIgnoreCase("No")) {
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, res);

		} else if(req.getParameter("operation").equalsIgnoreCase("Yes")) {

			User user = manager.getUser((String) session.getAttribute("user"));

			try {
				manager.deleteUser(user);
			} catch (Exception e) {
				System.out.println("Descripci�n: " + e.getMessage());
			}
			session.invalidate();
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, res);

		}

	}

}
