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
			int id = Integer.parseInt(req.getParameter("id"));
			Product p = ProductController.getProduct(id);
			int newStock = Integer.parseInt(req.getParameter("newStock"));
			if (ProductController.verifyStock(newStock)) {
				p.setStock(newStock);
				if (p.getUserBean().getEmail().equals(user))
					ProductController.modifyProduct(p);
			} else {
				req.setAttribute("msg_error", "Error, invalid stock value.");
			}

			RequestDispatcher rd = req.getRequestDispatcher("catalogue.jsp");
			rd.forward(req, res);
			
		}else if(req.getParameter("type").equalsIgnoreCase("add")) {
			req.setAttribute("seller", "yes");
			req.setAttribute("operation", "add");
			RequestDispatcher rd = req.getRequestDispatcher("product-page.jsp");
			rd.forward(req, res);
			
		}else if(req.getParameter("type").equalsIgnoreCase("modify")) {
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("productId", id);
			Product p = ProductController.getProduct(id);
			req.setAttribute("product", p);
			req.setAttribute("seller", "yes");
			req.setAttribute("operation", "modify");
			RequestDispatcher rd = req.getRequestDispatcher("product-page.jsp");
			rd.forward(req, res);
			
		}else if(req.getParameter("type").equalsIgnoreCase("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Product p = ProductController.getProduct(id);
			if (p.getUserBean().getEmail().equals(user))
				ProductController.deleteProduct(id);

			RequestDispatcher rd = req.getRequestDispatcher("catalogue.jsp");
			rd.forward(req, res);
		}else if(req.getParameter("product_operation").equalsIgnoreCase("add")) {
			// TODO
			String product_name=req.getParameter("product_name");
			double product_price=Double.parseDouble(req.getParameter("product_price"));
			double product_sale_price=Double.parseDouble(req.getParameter("product_sale_price"));
			double product_ship_price=Double.parseDouble(req.getParameter("product_ship_price"));
			int product_stock=Integer.parseInt(req.getParameter("product_stock"));
			String product_short_description=req.getParameter("product_short_description");
			String product_description=req.getParameter("product_description");
			String product_category=req.getParameter("product_category");
		}else if(req.getParameter("product_operation").equalsIgnoreCase("modify")) {
			// TODO
			String product_name=req.getParameter("product_name");
			double product_price=Double.parseDouble(req.getParameter("product_price"));
			double product_sale_price=Double.parseDouble(req.getParameter("product_sale_price"));
			double product_ship_price=Double.parseDouble(req.getParameter("product_ship_price"));
			int product_stock=Integer.parseInt(req.getParameter("product_stock"));
			String product_short_description=req.getParameter("product_short_description");
			String product_description=req.getParameter("product_description");
			String product_category=req.getParameter("product_category");
			
			
		}
	}

}