package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the wishlists database table.
 * 
 */
@Embeddable
public class WishListPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String user;

	@Column(insertable=false, updatable=false)
	private int product;

	public WishListPK() {
	}
	public String getUser() {
		return this.user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getProduct() {
		return this.product;
	}
	public void setProduct(int product) {
		this.product = product;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WishListPK)) {
			return false;
		}
		WishListPK castOther = (WishListPK)other;
		return 
			this.user.equals(castOther.user)
			&& (this.product == castOther.product);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.user.hashCode();
		hash = hash * prime + this.product;
		
		return hash;
	}
}