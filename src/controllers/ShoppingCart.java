package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Category;
import model.Product;
import model.ProductInCart;
import model.User;

public class ShoppingCart extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private ArrayList<ProductInCart> products;

	public ShoppingCart() {
		products = new ArrayList<ProductInCart>();
		ProductInCart p=new ProductInCart(new Product());// TODO
		p.getProduct().setId(1);
		p.getProduct().setName("bag");
		p.getProduct().setPrice(new BigDecimal(44.99));
		p.getProduct().setDescription("Bag premium quality Bag premium quality Bag premium quality");
		p.getProduct().setShortDescription("Bag premium quality");
		p.getProduct().setImagePath("/tiw-p1/images/product01.jpg");
		p.getProduct().setStock(1);
		Category c = new Category();
		c.setName("bags & shoes");
		p.getProduct().setCategoryBean(c);
		User u = new User();
		u.setEmail("pepe@gmail.com");
		p.getProduct().setUserBean(u);
		p.setQuantity(2);
		products.add(p);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HttpSession session = req.getSession();

		if(session.getAttribute("user") != null) {
			req.getSession().setAttribute("cartList", products);
			if(req.getParameter("type").equalsIgnoreCase("addToCart")) {

				int id = Integer.parseInt(req.getParameter("id"));
				int num = Integer.parseInt(req.getParameter("numOrder"));

				for(ProductInCart product : products) {
					if(product.getProduct().getId() == id) {
						product.setQuantity(product.getQuantity() + num );
						RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
						rd.forward(req, res);
						return ;
					}
				}

				ProductInCart newP = new ProductInCart(ProductController.getProduct(id));
				newP.setQuantity(num);
				products.add(newP);

				RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
				rd.forward(req, res);

			} else if(req.getParameter("type").equalsIgnoreCase("deleteInCart")) {

				int index = Integer.parseInt(req.getParameter("indexToRemove"));

				products.remove(index);

				RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
				rd.forward(req, res);
			} else if(req.getParameter("type").equalsIgnoreCase("placeOrder")) {

				// TODO

				RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
				rd.forward(req, res);
			}
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("login-page.jsp");
			rd.forward(req, res);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getSession().setAttribute("cartList", products);
		RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
		rd.forward(req, res);
	}

}
