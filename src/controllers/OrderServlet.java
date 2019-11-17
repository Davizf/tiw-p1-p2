package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
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
import model.Product;
import model.ProductInCart;
import model.User;

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
		ProductManager productManager = new ProductManager();
		
		System.out.println("----------------"+email);
		for (ProductInCart p : productsInCart) {
			System.out.println(p);
		}


		if(req.getParameter("type").equalsIgnoreCase("confirm-checkout")) {
			//InteractionJMS mq=new InteractionJMS();
			//mq.confirmPurchase("123412124214", req.getParameter("total-price"));

			// El otro proceso que lee el mensaje y generar confirmacion de compra está en mi local, no sé si es un proyecto aparte o en este mismo, estoy esperando respuesta del profe
			// Guardar la compra en la pagina de mis pedidos

			/*
			EntityManager em = factory.createEntityManager();
			Order order = new Order();
			order.setAddress(req.getParameter("address"));
			order.setCity(req.getParameter("city"));
			order.setCountry(req.getParameter("country"));
			order.setPostalCode(Integer.parseInt(req.getParameter("zipCode")));
			order.setUserBean((User) em.find(User.class, email));
			order.setDate("");// TODO


			/*Order orderF = new Order();	
			List<Order>orders = orderManager.getLastOrder();
			for(Order orderFind : orders) {
				orderF = orderFind;
			}
			int kk=orderF.getId(); 


			em.getTransaction().begin();

			ArrayList<Orders_has_Product> products = new ArrayList<Orders_has_Product>();

			Orders_has_Product order_product;
			for(ProductInCart p : productsInCart) {
				//System.out.println("+++++++++++++++++"+kk+"_"+p.getProduct().getId());
//				ids = new Orders_has_ProductPK();
//				ids.setOrder(kk);
//				ids.setProduct(p.getProduct().getId());

				order_product = new Orders_has_Product();
				order_product.setProductPrice(p.getProduct().getPrice());
				order_product.setProductBean(p.getProduct());
				order_product.setShipPrice(p.getProduct().getShipPrice());
				order_product.setQuantity(p.getQuantity());

				System.out.println("1");
				//em.persist(order_product);
				System.out.println("2");
				products.add(order_product);
				//orderF.addOrdersHasProduct(order_product);
			}
//			order.setOrdersHasProducts(products);
//			em.persist(order);

			System.out.println("_1");

			System.out.println("_2");
		
			order.setOrdersHasProducts(products);
			em.persist(order);
			
			for(Orders_has_Product product : products ) {
				System.out.println(product.getQuantity());
				System.out.println(product.getOrderBean());
				System.out.println(product.getProductPrice());
				System.out.println(product.getShipPrice());
				em.persist(product);
			}
			
			
			em.getTransaction().commit();
			em.close();
			factory.close();*/
			

			Orders order = new Orders();
			Orders_has_Product order_product;
			order.setAddress(req.getParameter("address"));
			order.setCity(req.getParameter("city"));
			order.setCountry(req.getParameter("country"));
			order.setPostalCode(Integer.parseInt(req.getParameter("zipCode")));
			order.setUserBean(userManager.getUser(email));
			//order.setOrdersHasProducts(new ArrayList<Orders_has_Product>());
			ArrayList<Orders_has_Product> products = new ArrayList<Orders_has_Product>();
			for(ProductInCart product : productsInCart) {
				Product productInCart = product.getProduct();
				order_product = new Orders_has_Product();
				order_product.setProductPrice(product.getProduct().getPrice());
				order_product.setProductBean(product.getProduct());
				order_product.setOrder(order);
				order_product.setShipPrice(product.getProduct().getShipPrice());
				order_product.setQuantity(product.getQuantity());
				products.add(order_product);
				/*productInCart.addOrdersHasProduct(order_product);
				try {
					productManager.updateProduct(productInCart);
				} catch (Exception e) {
					System.out.println("Descripci�n: " + e.getMessage());
				}*/
			}
			
			order.setOrdersHasProducts(products);
			
			try {
				orderManager.createOrder(order);
			} catch (Exception e) {
				System.out.println("Descripci�n: " + e.getMessage());
			}
			
	
			//			try {
			//				orderManager.createOrder(order);
			//			} catch (Exception e) {
			//				System.out.println("Descripci�n: " + e.getMessage());
			//			}


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