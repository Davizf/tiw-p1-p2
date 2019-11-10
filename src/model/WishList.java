package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the WishLists database table.
 * 
 */
@Entity
@Table(name="WishLists")
@NamedQuery(name="WishList.findAll", query="SELECT w FROM WishList w")
public class WishList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String user;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product")
	private Product productBean;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="user", insertable=false, updatable=false)
	private User userBean;

	public WishList() {
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Product getProductBean() {
		return this.productBean;
	}

	public void setProductBean(Product productBean) {
		this.productBean = productBean;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}