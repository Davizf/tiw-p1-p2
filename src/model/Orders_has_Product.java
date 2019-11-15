package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the Orders_has_Products database table.
 * 
 */
@Entity
@Table(name="Orders_has_Products")
@NamedQuery(name="Orders_has_Product.findAll", query="SELECT o FROM Orders_has_Product o")
public class Orders_has_Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int order;

	@Column(name="product_price")
	private BigDecimal productPrice;

	//bi-directional one-to-one association to Order
	@OneToOne
	@JoinColumn(name="order", insertable = false, updatable = false)
	private Order orderBean;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product")
	private Product productBean;

	public Orders_has_Product() {
	}

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public BigDecimal getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Order getOrderBean() {
		return this.orderBean;
	}

	public void setOrderBean(Order orderBean) {
		this.orderBean = orderBean;
	}

	public Product getProductBean() {
		return this.productBean;
	}

	public void setProductBean(Product productBean) {
		this.productBean = productBean;
	}

}