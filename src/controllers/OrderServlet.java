package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Order;
import model.Orders_has_Product;
import model.ProductInCart;
import model.User;

public class OrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("user");
		@SuppressWarnings("unchecked")
		ArrayList<ProductInCart> productsInCart = (ArrayList<ProductInCart>)session.getAttribute("cartList");

		System.out.println("----------------"+email);
		for (ProductInCart p : productsInCart) {
			System.out.println(p);
		}


		if(req.getParameter("type").equalsIgnoreCase("confirm-checkout")) {
			//InteractionJMS mq=new InteractionJMS();
			//mq.confirmPurchase("123412124214", req.getParameter("total-price"));

			// El otro proceso que lee el mensaje y generar confirmacion de compra está en mi local, no sé si es un proyecto aparte o en este mismo, estoy esperando respuesta del profe
			// Guardar la compra en la pagina de mis pedidos


			EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");
			EntityManager em = factory.createEntityManager();
			Order order = new Order();
			order.setAddress(req.getParameter("address"));
			order.setCity(req.getParameter("city"));
			order.setCountry(req.getParameter("country"));
			order.setPostalCode(Integer.parseInt(req.getParameter("zipCode")));
			order.setUserBean((User) em.find(User.class, email));
			order.setDate("");// TODO

			em.getTransaction().begin();
			em.persist(order);
			em.getTransaction().commit();
			int kk=order.getId();
			em.close();
			factory.close();

			factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");
			em = factory.createEntityManager();
			em.getTransaction().begin();
			ArrayList<Orders_has_Product> products = new ArrayList<Orders_has_Product>();

			Orders_has_Product order_product;
//			Orders_has_ProductPK ids;
			for(ProductInCart p : productsInCart) {
				System.out.println("+++++++++++++++++"+kk+"_"+p.getProduct().getId());
//				ids = new Orders_has_ProductPK();
//				ids.setOrder(kk);
//				ids.setProduct(p.getProduct().getId());

				order_product = new Orders_has_Product();
				order_product.setProductPrice(p.getProduct().getPrice());
				order_product.setProductBean(p.getProduct());
				order_product.setOrderBean(order);
				order_product.setShipPrice(p.getProduct().getShipPrice());
				order_product.setQuantity(p.getQuantity());

				System.out.println("1");
				em.persist(order_product);
				System.out.println("2");
				products.add(order_product);
			}
//			order.setOrdersHasProducts(products);
//			em.persist(order);

			System.out.println("_1");
			em.getTransaction().commit();
			System.out.println("_2");
			em.close();
			factory.close();

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