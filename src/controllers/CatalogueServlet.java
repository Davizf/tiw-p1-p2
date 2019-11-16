package controllers;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Category;
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
			req.setAttribute("operation", "add");
			RequestDispatcher rd = req.getRequestDispatcher("product-page-seller.jsp");
			rd.forward(req, res);

		}else if(req.getParameter("type").equalsIgnoreCase("modify")) {
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("productId", id);
			Product p = ProductController.getProduct(id);
			req.setAttribute("product", p);
			req.setAttribute("operation", "modify");
			RequestDispatcher rd = req.getRequestDispatcher("product-page-seller.jsp");
			rd.forward(req, res);

		}else if(req.getParameter("type").equalsIgnoreCase("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Product p = ProductController.getProduct(id);
			if (p.getUserBean().getEmail().equals(user))
				ProductController.deleteProduct(id);

			RequestDispatcher rd = req.getRequestDispatcher("catalogue.jsp");
			rd.forward(req, res);
		}else if(req.getParameter("type").equalsIgnoreCase("add_product")) {
			// TODO
			String product_name=req.getParameter("product_name");
			double product_price=Double.parseDouble(req.getParameter("product_price"));
			double product_sale_price=Double.parseDouble(req.getParameter("product_sale_price"));
			double product_ship_price=Double.parseDouble(req.getParameter("product_ship_price"));
			int product_stock=Integer.parseInt(req.getParameter("product_stock"));
			String product_short_description=req.getParameter("product_short_description");
			String product_description=req.getParameter("product_description");
			String product_category=req.getParameter("product_category");
			String product_image_path=req.getParameter("product_image_path");

			Product p=new Product();
			p.setName(product_name);
			p.setPrice(new BigDecimal(product_price));
			p.setSalePrice(new BigDecimal(product_sale_price));
			p.setShipPrice(new BigDecimal(product_ship_price));
			p.setStock(product_stock);
			p.setShortDescription(product_short_description);
			Category c = new Category();
			c.setName(product_category);
			p.setCategoryBean(c);
			p.setDescription(product_description);
			p.setImagePath(product_image_path);
		}else if(req.getParameter("type").equalsIgnoreCase("modify_product")) {
			// TODO
			String product_name=req.getParameter("product_name");
			double product_price=Double.parseDouble(req.getParameter("product_price"));
			double product_sale_price=Double.parseDouble(req.getParameter("product_sale_price"));
			double product_ship_price=Double.parseDouble(req.getParameter("product_ship_price"));
			int product_stock=Integer.parseInt(req.getParameter("product_stock"));
			String product_short_description=req.getParameter("product_short_description");
			String product_description=req.getParameter("product_description");
			String product_category=req.getParameter("product_category");
			String product_image_path=req.getParameter("product_image_path");

			Product p=new Product();
			p.setName(product_name);
			p.setPrice(new BigDecimal(product_price));
			p.setSalePrice(new BigDecimal(product_sale_price));
			p.setShipPrice(new BigDecimal(product_ship_price));
			p.setStock(product_stock);
			p.setShortDescription(product_short_description);
			Category c = new Category();
			c.setName(product_category);
			p.setCategoryBean(c);
			p.setDescription(product_description);
			p.setImagePath(product_image_path);
		}
	}

}