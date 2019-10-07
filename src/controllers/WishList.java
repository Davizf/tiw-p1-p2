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

public class WishList extends HttpServlet{
	
	ArrayList<ProductInWishList> products = new ArrayList<ProductInWishList>();
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
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
		
		
	
	}

}
