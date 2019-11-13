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

public class WishList extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ArrayList<Product> products = new ArrayList<Product>();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HttpSession session = req.getSession();
		if(session.getAttribute("user") != null) {

			if(req.getParameter("type").equalsIgnoreCase("addToWishList")) {
				int id = Integer.parseInt(req.getParameter("id"));

				for(Product product : products) {
					if(product.getId()==id) {
						RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
						rd.forward(req, res);
						return;
					}
				}
				products.add(ProductController.getProduct(id));
				req.setAttribute("wishList", products);

				RequestDispatcher rd = req.getRequestDispatcher("wish-list.jsp");
				rd.forward(req, res);


			} else if(req.getParameter("type").equalsIgnoreCase("deleteInWishList")) {

				int index = Integer.parseInt(req.getParameter("indexToRemove"));

				products.remove(index);
				req.setAttribute("wishList", products);

				RequestDispatcher rd = req.getRequestDispatcher("wish-list.jsp");
				rd.forward(req, res);

			}

		} else {

			RequestDispatcher rd = req.getRequestDispatcher("login-page.jsp");
			rd.forward(req, res);

		}

	}

}
