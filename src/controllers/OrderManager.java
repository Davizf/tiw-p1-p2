package controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Order;

public class OrderManager {

	private EntityManagerFactory emf;

	public OrderManager() {

	}

	public OrderManager(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManager getEntityManager() {
		if (emf == null) {
			throw new RuntimeException(
					"The EntityManagerFactory is null.  This must be passed in to the constructor or set using the setEntityManagerFactory() method.");
		}
		return emf.createEntityManager();
	}

	public String createOrder(Order order) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(order);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	public String deleteOrder(Order order) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			order = em.merge(order);
			em.remove(order);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	public String updateOrder(Order order) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			order = em.merge(order);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	public Order getOrder(int id) {
		Order order = null;
		EntityManager em = getEntityManager();
		try {
			order = (Order) em.find(Order.class, id);
		} finally {
			em.close();
		}
		return order;
	}

	public Boolean verifyOrder(String email, String password) {
		Order order = null;
		EntityManager em = getEntityManager();
		try {
			order = (Order) em.find(Order.class, email);
		} finally {
			em.close();
		}

		return order != null ? false : false;
	}

	@SuppressWarnings("unchecked")
	public List<Order> getAllOrders() {
		List<Order> orders = null;
		EntityManager em = getEntityManager();
		try {
			orders = (List<Order>) em.createNamedQuery("Order.findAll").getResultList();
		} finally {
			em.close();
		}
		return orders;
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getOrdersByCategory(String category) {
		List<Order> orders = null;
		EntityManager em = getEntityManager();
		try {
			orders = (List<Order>) em.createNamedQuery("Order.findAllByCategory").setParameter("category", category).getResultList();
		} finally {
			em.close();
		}
		return orders;
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getLastOrders() {
		List<Order> Orders = null;
		EntityManager em = getEntityManager();
		try {
			Orders = (List<Order>) em.createNamedQuery("Order.OrderById").setMaxResults(3).getResultList();
		} finally {
			em.close();
		}

		return Orders;
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getOrdersBySeller(String email) {
		List<Order> Orders = null;
		EntityManager em = getEntityManager();
		try {
			Orders = (List<Order>) em.createNamedQuery("Order.findAllBySeller").setParameter("email", email).getResultList();
		} finally {
			em.close();
		}

		return Orders;
	}



}