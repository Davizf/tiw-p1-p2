package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ProductInCart;

public class ShoppingCart extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ProductInCart> products = new ArrayList<ProductInCart>();
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("user") != null) {
			
			
			
			if(req.getParameter("type").equalsIgnoreCase("addToCart")) {
				
				String name = req.getParameter("name");
				int num = Integer.parseInt(req.getParameter("numOrder"));
				
				for(ProductInCart product : products) {
					if(product.getName().equalsIgnoreCase(name)) {
						
						product.setNum(product.getNum() + num );
						req.setAttribute("cartList", products);
						RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
						rd.forward(req, res);
						return ;
					}
				}
				
				String path = req.getParameter("path");
				products.add(new ProductInCart(path,name,num));
				
				req.setAttribute("cartList", products);
				RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
				rd.forward(req, res);
				
				
			} else if(req.getParameter("type").equalsIgnoreCase("deleteInCart")) {
				
				
				int index = Integer.parseInt(req.getParameter("indexToRemove"));
				
				products.remove(index);
				req.setAttribute("cartList", products);

				
				RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
				rd.forward(req, res);
				
			}
			
			
			
		}else {
			
			RequestDispatcher rd = req.getRequestDispatcher("login-page.jsp");
			rd.forward(req, res);
			
		}
		
		
		
		
	
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setAttribute("cartList", products);
		RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
		rd.forward(req, res);
	}

}
