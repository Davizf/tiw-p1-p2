package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the orders_has_products database table.
 * 
 */
@Embeddable
public class Orders_has_ProductPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int order;

	@Column(insertable=false, updatable=false)
	private int product;

	public Orders_has_ProductPK() {
	}
	public int getOrder() {
		return this.order;
	}
	public void setOrder(int order) {
		this.order = order;
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
		if (!(other instanceof Orders_has_ProductPK)) {
			return false;
		}
		Orders_has_ProductPK castOther = (Orders_has_ProductPK)other;
		return 
			(this.order == castOther.order)
			&& (this.product == castOther.product);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.order;
		hash = hash * prime + this.product;
		
		return hash;
	}
}