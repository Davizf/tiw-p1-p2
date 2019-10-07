package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ProductInCart;

public class ShoppingCart extends HttpServlet{
	
	ArrayList<ProductInCart> products = new ArrayList<ProductInCart>();
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		if(req.getParameter("type").equalsIgnoreCase("addToCart")) {
			String name = req.getParameter("name");
			
			
			
			PrintWriter out = res.getWriter();
			out.println("name is : " + name);
			
			
			int num = Integer.parseInt(req.getParameter("numOrder"));
			
			for(ProductInCart product : products) {
				if(product.getName().equalsIgnoreCase(name)) {
					product.setNum(product.getNum() + num );
					
					req.setAttribute("cartList", products);
					RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
					rd.forward(req, res);
				}
			}
			
			String path = req.getParameter("path");
			products.add(new ProductInCart(path,name,num));
			
			req.setAttribute("cartList", products);
			RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
			rd.forward(req, res);
			
			
		} else if(req.getParameter("type").equalsIgnoreCase("deleteInCart")) {
			
			//PrintWriter out = res.getWriter();
			
			int index = Integer.parseInt(req.getParameter("indexToRemove"));
			
			//out.println("index is: " + index);
			
			products.remove(index);
			req.setAttribute("cartList", products);
			//out.print("length is: " + products.size());
			
			RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
			rd.forward(req, res);
			
		}
		
		
	
	}

}
