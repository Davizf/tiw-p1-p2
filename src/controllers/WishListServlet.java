package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import model.User;
import model.WishList;

public class WishListServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ArrayList<Product> products = new ArrayList<Product>();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HttpSession session = req.getSession();
		if(session.getAttribute("user") != null) {
			String email = (String) session.getAttribute("user");
			if(req.getParameter("type").equalsIgnoreCase("addToWishList")) {
				int id = Integer.parseInt(req.getParameter("id"));
				Product product = ProductController.getProduct(id);
				User user = UserController.getUserInformation(email);
				WishList wishList = new WishList();
				
				wishList.setProductBean(product);
				wishList.setUserBean(user);
				user.addWishlist(wishList);
				
				UserController.modifyUser(user);

				RequestDispatcher rd = req.getRequestDispatcher("wish-list.jsp");
				rd.forward(req, res);


			} else if(req.getParameter("type").equalsIgnoreCase("deleteInWishList")) {
				int product = Integer.parseInt(req.getParameter("product"));
				WishList wishList = WishListController.getWishListByUserAndProduct(email, product);

				WishListController.deleteWishList(wishList);

				RequestDispatcher rd = req.getRequestDispatcher("wish-list.jsp");
				rd.forward(req, res);

			}

		} else {

			RequestDispatcher rd = req.getRequestDispatcher("login-page.jsp");
			rd.forward(req, res);

		}

	}

}
