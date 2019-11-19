package controllers;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Product;

@WebServlet(name = "ProductServlet", urlPatterns = "/ProductServlet")
@MultipartConfig
public class ProductServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		if(req.getParameter("op").equalsIgnoreCase("view")) {
			req.setAttribute("resultType", "showAll");
	
		}else if(req.getParameter("op").equalsIgnoreCase("search")) {
			String nameToQuery = req.getParameter("query");
			List<Product> products = ProductController.getProductByName(nameToQuery);
			req.setAttribute("foundProducts", products);
			req.setAttribute("resultType", "foundByKey");
	
		}
		
		
		RequestDispatcher rd = req.getRequestDispatcher("products.jsp");
		rd.forward(req, res);
		
		
	}	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	

}