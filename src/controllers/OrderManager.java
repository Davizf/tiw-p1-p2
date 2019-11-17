package controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Orders;

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

	public String createOrder(Orders order) throws Exception {
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

	public String deleteOrder(Orders order) throws Exception {
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

	public String updateOrder(Orders order) throws Exception {
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

	public Orders getOrder(int id) {
		Orders order = null;
		EntityManager em = getEntityManager();
		try {
			order = (Orders) em.find(Orders.class, id);
		} finally {
			em.close();
		}
		return order;
	}

	public Boolean verifyOrder(String email, String password) {
		Orders order = null;
		EntityManager em = getEntityManager();
		try {
			order = (Orders) em.find(Orders.class, email);
		} finally {
			em.close();
		}

		return order != null ? false : false;
	}

	@SuppressWarnings("unchecked")
	public List<Orders> getAllOrders() {
		List<Orders> orders = null;
		EntityManager em = getEntityManager();
		try {
			orders = (List<Orders>) em.createNamedQuery("Orders.findAll").getResultList();
		} finally {
			em.close();
		}
		return orders;
	}
	
	@SuppressWarnings("unchecked")
	public List<Orders> getOrdersByCategory(String category) {
		List<Orders> orders = null;
		EntityManager em = getEntityManager();
		try {
			orders = (List<Orders>) em.createNamedQuery("Orders.findAllByCategory").setParameter("category", category).getResultList();
		} finally {
			em.close();
		}
		return orders;
	}
	
	@SuppressWarnings("unchecked")
	public List<Orders> getLastOrders() {
		List<Orders> Orders = null;
		EntityManager em = getEntityManager();
		try {
			Orders = (List<Orders>) em.createNamedQuery("Orders.OrderById").setMaxResults(3).getResultList();
		} finally {
			em.close();
		}

		return Orders;
	}
	
	@SuppressWarnings("unchecked")
	public List<Orders> getOrdersBySeller(String email) {
		List<Orders> Orders = null;
		EntityManager em = getEntityManager();
		try {
			Orders = (List<Orders>) em.createNamedQuery("Orders.findAllBySeller").setParameter("email", email).getResultList();
		} finally {
			em.close();
		}

		return Orders;
	}
	
	@SuppressWarnings("unchecked")
	public List<Orders> getLastOrder() {
		List<Orders> order = null;
		EntityManager em = getEntityManager();
		try {
			order = (List<Orders>) em.createNamedQuery("Orders.OrderById").setMaxResults(1).getResultList();
		} finally {
			em.close();
		}

		return order;
	}


}