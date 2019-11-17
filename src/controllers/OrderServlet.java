package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Orders;
import model.Orders_has_Product;

import model.ProductInCart;

public class OrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("user");
		@SuppressWarnings("unchecked")
		ArrayList<ProductInCart> productsInCart = (ArrayList<ProductInCart>)session.getAttribute("cartList");
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");
		UserManager userManager = new UserManager();
		userManager.setEntityManagerFactory(factory);
		OrderManager orderManager = new OrderManager();
		orderManager.setEntityManagerFactory(factory);
		


		if(req.getParameter("type").equalsIgnoreCase("confirm-checkout")) {
			//InteractionJMS mq=new InteractionJMS();
			//mq.confirmPurchase("123412124214", req.getParameter("total-price"));

			// El otro proceso que lee el mensaje y generar confirmacion de compra está en mi local, no sé si es un proyecto aparte o en este mismo, estoy esperando respuesta del profe
			// Guardar la compra en la pagina de mis pedidos

		
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Orders order = new Orders();
			Orders_has_Product order_product;
			order.setAddress(req.getParameter("address"));
			order.setCity(req.getParameter("city"));
			order.setCountry(req.getParameter("country"));
			order.setPostalCode(Integer.parseInt(req.getParameter("zipCode")));
			order.setUserBean(userManager.getUser(email));
			order.setDate(formatter.format(date));
			
			ArrayList<Orders_has_Product> products = new ArrayList<Orders_has_Product>();
			for(ProductInCart product : productsInCart) {
				order_product = new Orders_has_Product();
				order_product.setProductPrice(product.getProduct().getPrice());
				order_product.setProductBean(product.getProduct());
				order_product.setOrder(order);
				order_product.setShipPrice(product.getProduct().getShipPrice());
				order_product.setQuantity(product.getQuantity());
				products.add(order_product);
			}
			
			order.setOrdersHasProducts(products);
			
			try {
				orderManager.createOrder(order);
			} catch (Exception e) {
				System.out.println("Descripci�n: " + e.getMessage());
			}
	
			session.setAttribute("cartList", null);
			RequestDispatcher rd = req.getRequestDispatcher("confirm-page.jsp");
			rd.forward(req, res);
		}else if(req.getParameter("type").equalsIgnoreCase("my-orders")) {
			RequestDispatcher rd = req.getRequestDispatcher("my-orders.jsp");
			rd.forward(req, res);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}