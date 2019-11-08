package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ProductInWishList;

public class WishList extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ProductInWishList> products = new ArrayList<ProductInWishList>();
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HttpSession session = req.getSession();
		if(session.getAttribute("user") != null) {
			
			if(req.getParameter("type").equalsIgnoreCase("addToWishList")) {
				String name = req.getParameter("name");
				
				
				for(ProductInWishList product : products) {
					if(product.getName().equalsIgnoreCase(name)) {
						RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
						rd.forward(req, res);
					}
				}
				
				String path = req.getParameter("path");
				products.add(new ProductInWishList(path,name));
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
