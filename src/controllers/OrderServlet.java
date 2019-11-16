package controllers;

import java.io.IOException;
import jhc.jms.*;
import model.Order;
import model.Orders_has_Product;
import model.Orders_has_ProductPK;
import model.User;
import models.ProductInCart;

import java.io.PrintWriter;
import java.util.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderServlet extends HttpServlet{
	
	/**
	 * 
	 */
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
		Orders_has_ProductManager orderProductManager = new Orders_has_ProductManager();
		orderProductManager.setEntityManagerFactory(factory);
		
		if(req.getParameter("type").equalsIgnoreCase("confirm-checkout")) {
			//InteractionJMS mq=new InteractionJMS();
			//mq.confirmPurchase("123412124214", req.getParameter("total-price"));
			
			
			// El otro proceso que lee el mensaje y generar confirmacion de compra está en mi local, no sé si es un proyecto aparte o en este mismo, estoy esperando respuesta del profe
			
			// Guardar la compra en la pagina de mis pedidos
			

			
			Orders_has_Product order_products = new Orders_has_Product();
			List<Order> orders = (List<Order>)orderManager.getLastOrder();
			Order lastOrder = null;
			
			for(Order order : orders) {
				lastOrder = order;
			}
			
			/*order.setAddress(req.getParameter("address"));
			order.setCity(req.getParameter("city"));
			order.setCountry(req.getParameter("country"));
			order.setPostalCode(Integer.parseInt(req.getParameter("zipCode")));
			order.setUserBean(userManager.getUser(email));
			order.setOrdersHasProduct(order_products);
			

			
			try {
				orderManager.createOrder(order);
			} catch (Exception e) {
				System.out.println("Descripci�n: " + e.getMessage());
			}*/
			Orders_has_ProductPK ids = new Orders_has_ProductPK();
			
			for(ProductInCart productInCart: productsInCart) {
				order_products.setProductPrice(productInCart.getProduct().getPrice());
				order_products.setProductBean(productInCart.getProduct());
				order_products.setOrderBean(lastOrder);
				ids.setOrder(lastOrder.getId());
				ids.setProduct(productInCart.getProduct().getId());
				order_products.setId(ids);
				try {
					orderProductManager.createOrders_has_Product(order_products);
				} catch (Exception e) {
					System.out.println("Descripci�n: " + e.getMessage());
				}
			}
			
			factory.close();
			//order_products.setOrderBean(order);
			
			/*user.setPhone(Integer.parseInt(req.getParameter("tel")));
			user.setPostalCode(Integer.parseInt(req.getParameter("zipCode")));
			user.setAddress(req.getParameter("address"));
			user.setCity(req.getParameter("city"));
			user.setCountry(req.getParameter("country"));
			user.setEmail(email);
			user.setName(req.getParameter("firstName"));
			user.setSurnames(req.getParameter("lastName"));
			user.setPassword(req.getParameter("password"));
			user.setCreditCard(req.getParameter("card"));
			user.setCreditCardExpiration(req.getParameter("cardExpire"));
			user.setCredit_card_CVV(Integer.parseInt(req.getParameter("cvv")));*/

			
			
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
