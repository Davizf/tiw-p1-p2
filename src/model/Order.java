package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Orders database table.
 * 
 */
@Entity
@Table(name="Orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user")
	private User userBean;

	//bi-directional one-to-one association to Orders_has_Product
	@OneToOne(mappedBy="orderBean")
	private Orders_has_Product ordersHasProduct;

	public Order() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

	public Orders_has_Product getOrdersHasProduct() {
		return this.ordersHasProduct;
	}

	public void setOrdersHasProduct(Orders_has_Product ordersHasProduct) {
		this.ordersHasProduct = ordersHasProduct;
	}

}