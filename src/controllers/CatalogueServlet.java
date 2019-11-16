package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;

public class CatalogueServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession sesion = req.getSession();
		String user = (String) sesion.getAttribute("user");

		if(req.getParameter("type").equalsIgnoreCase("change-stock")) {
			// TODO
			int id = Integer.parseInt(req.getParameter("id"));

			// getProductById(productId).setStock(req.getParameter("newStock"))	:P

			RequestDispatcher rd = req.getRequestDispatcher("catalogue.jsp");
			rd.forward(req, res);
		}else if(req.getParameter("type").equalsIgnoreCase("add")) {
			// TODO
			RequestDispatcher rd = req.getRequestDispatcher("product-page.jsp");
			rd.forward(req, res);
		}else if(req.getParameter("type").equalsIgnoreCase("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Product p = ProductController.getProduct(id);
			if (p.getUserBean().getEmail().equals(user))
				ProductController.deleteProduct(id);

			RequestDispatcher rd = req.getRequestDispatcher("catalogue.jsp");
			rd.forward(req, res);
		}
	}

}