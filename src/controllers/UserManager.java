package controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.User;


//@SuppressWarnings("unchecked")

public class UserManager {

	private EntityManagerFactory emf;

	public UserManager() {

	}

	public UserManager(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public EntityManager getEntityManager() {
		if (emf == null) {
			throw new RuntimeException(
					"The EntityManagerFactory is null.  This must be passed in to the constructor or set using the setEntityManagerFactory() method.");
		}
		return emf.createEntityManager();
	}

	public String createUser(User user) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(user);
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

	public String deleteUser(User user) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			user = em.merge(user);
			em.remove(user);
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

	public String updateUser(User user) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			user = em.merge(user);
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

	public boolean checkUserEmail(String email) {
		User user = null;
		EntityManager em = getEntityManager();
		try {
			user = (User) em.find(User.class, email);
		} finally {
			em.close();
		}
		
		return user != null;
	}
	
	public Boolean verifyUser(String email, String password) {
		User user = null;
		EntityManager em = getEntityManager();
		try {
			user = (User) em.find(User.class, email);
		} finally {
			em.close();
		}
	
		return user != null ? user.getPassword().equals(password) : false;
	}

	public User getUser(String email) {
		User user = null;
		EntityManager em = getEntityManager();
		try {
			user = (User) em.find(User.class, email);
		} finally {
			em.close();
		}
		return user;
	}

}