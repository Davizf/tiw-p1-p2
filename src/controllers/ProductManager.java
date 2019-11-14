package controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Product;

public class ProductManager {

	private EntityManagerFactory emf;

	public ProductManager() {

	}

	public ProductManager(EntityManagerFactory emf) {
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

	public String createProduct(Product product) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(product);
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

	public String deleteProduct(Product product) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			product = em.merge(product);
			em.remove(product);
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

	public String updateProduct(Product product) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			product = em.merge(product);
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

	public Product getProduct(int id) {
		Product product = null;
		EntityManager em = getEntityManager();
		try {
			product = (Product) em.find(Product.class, id);
		} finally {
			em.close();
		}
		return product;
	}

	public Boolean verifyProduct(String email, String password) {
		Product Product = null;
		EntityManager em = getEntityManager();
		try {
			Product = (Product) em.find(Product.class, email);
		} finally {
			em.close();
		}

		return Product != null ? false : false;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		List<Product> products = null;
		EntityManager em = getEntityManager();
		try {
			products = (List<Product>) em.createNamedQuery("Product.findAll").getResultList();
		} finally {
			em.close();
		}
		return products;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getProductsByCategory(String category) {
		List<Product> products = null;
		EntityManager em = getEntityManager();
		try {
			products = (List<Product>) em.createNamedQuery("Product.findAllByCategory").setParameter("category", category).getResultList();
		} finally {
			em.close();
		}
		return products;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getLastProducts() {
		List<Product> products = null;
		EntityManager em = getEntityManager();
		try {
			products = (List<Product>) em.createNamedQuery("Product.OrderById").setMaxResults(3).getResultList();
		} finally {
			em.close();
		}

		return products;
	}


}