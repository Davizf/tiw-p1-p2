package controllers;


import java.io.IOException;
import java.io.PrintWriter;

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
		
		HttpSession sesion = req.getSession();
		
		
		
		if(req.getParameter("category").equalsIgnoreCase("all")) {
			
		}
		
		
	}	

}