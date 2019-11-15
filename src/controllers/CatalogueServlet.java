package controllers;

import java.io.IOException;
import jhc.jms.*;
import model.User;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CatalogueServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		if(req.getParameter("type").equalsIgnoreCase("change-stock")) {
			int productId = Integer.parseInt(req.getParameter("id"));
			
			// getProductById(productId).setStock(req.getParameter("newStock"))	:P
			
			RequestDispatcher rd = req.getRequestDispatcher("catalogue.jsp");
			rd.forward(req, res);
		}else if(req.getParameter("type").equalsIgnoreCase("add")) {
			
		}	
		// falta delete 
		
		
		
		
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		doPost(req, resp);
	}
	

}
