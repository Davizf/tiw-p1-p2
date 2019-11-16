package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wishlists database table.
 * 
 */
@Entity
@Table(name="wishlists")
@NamedQuery(name="WishList.findAll", query="SELECT w FROM WishList w")
public class WishList implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WishListPK id;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user", insertable = false, updatable = false)
	private User userBean;

	public WishList() {
	}

	public WishListPK getId() {
		return this.id;
	}

	public void setId(WishListPK id) {
		this.id = id;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}