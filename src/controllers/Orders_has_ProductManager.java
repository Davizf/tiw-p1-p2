package controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Orders_has_Product;

public class Orders_has_ProductManager {

	private EntityManagerFactory emf;

	public Orders_has_ProductManager() {

	}

	public Orders_has_ProductManager(EntityManagerFactory emf) {
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

	public String createOrders_has_Product(Orders_has_Product Orders_has_Product) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(Orders_has_Product);
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

	public String deleteOrders_has_Product(Orders_has_Product Orders_has_Product) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			Orders_has_Product = em.merge(Orders_has_Product);
			em.remove(Orders_has_Product);
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

	public String updateOrders_has_Product(Orders_has_Product Orders_has_Product) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			Orders_has_Product = em.merge(Orders_has_Product);
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

	public Orders_has_Product getOrders_has_Product(int id) {
		Orders_has_Product Orders_has_Product = null;
		EntityManager em = getEntityManager();
		try {
			Orders_has_Product = (Orders_has_Product) em.find(Orders_has_Product.class, id);
		} finally {
			em.close();
		}
		return Orders_has_Product;
	}

	public Boolean verifyOrders_has_Product(String email, String password) {
		Orders_has_Product Orders_has_Product = null;
		EntityManager em = getEntityManager();
		try {
			Orders_has_Product = (Orders_has_Product) em.find(Orders_has_Product.class, email);
		} finally {
			em.close();
		}

		return Orders_has_Product != null ? false : false;
	}

	@SuppressWarnings("unchecked")
	public List<Orders_has_Product> getAllOrders_has_Products() {
		List<Orders_has_Product> Orders_has_Products = null;
		EntityManager em = getEntityManager();
		try {
			Orders_has_Products = (List<Orders_has_Product>) em.createNamedQuery("Orders_has_Product.findAll").getResultList();
		} finally {
			em.close();
		}
		return Orders_has_Products;
	}
	
	@SuppressWarnings("unchecked")
	public List<Orders_has_Product> getOrders_has_ProductsByCategory(String category) {
		List<Orders_has_Product> Orders_has_Products = null;
		EntityManager em = getEntityManager();
		try {
			Orders_has_Products = (List<Orders_has_Product>) em.createNamedQuery("Orders_has_Product.findAllByCategory").setParameter("category", category).getResultList();
		} finally {
			em.close();
		}
		return Orders_has_Products;
	}
	
	@SuppressWarnings("unchecked")
	public List<Orders_has_Product> getLastOrders_has_Products() {
		List<Orders_has_Product> Orders_has_Products = null;
		EntityManager em = getEntityManager();
		try {
			Orders_has_Products = (List<Orders_has_Product>) em.createNamedQuery("Orders_has_Product.Orders_has_ProductById").setMaxResults(3).getResultList();
		} finally {
			em.close();
		}

		return Orders_has_Products;
	}
	
	@SuppressWarnings("unchecked")
	public List<Orders_has_Product> getOrders_has_ProductsBySeller(String email) {
		List<Orders_has_Product> Orders_has_Products = null;
		EntityManager em = getEntityManager();
		try {
			Orders_has_Products = (List<Orders_has_Product>) em.createNamedQuery("Orders_has_Product.findAllBySeller").setParameter("email", email).getResultList();
		} finally {
			em.close();
		}

		return Orders_has_Products;
	}



}