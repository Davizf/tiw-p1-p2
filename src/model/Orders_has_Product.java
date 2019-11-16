package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the orders_has_products database table.
 * 
 */
@Entity
@Table(name="orders_has_products")
@NamedQuery(name="Orders_has_Product.findAll", query="SELECT o FROM Orders_has_Product o")
public class Orders_has_Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="product_price")
	private BigDecimal productPrice;

	private int quantity;

	@Column(name="ship_price")
	private BigDecimal shipPrice;

	//bi-directional many-to-one association to Order
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Order.class)
	@JoinColumn(name="id_order")
	private Order orderBean;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Product.class)
	@JoinColumn(name="product")
	private Product productBean;

	public Orders_has_Product() {
	}

	public int getId() {
		return this.id;
	}
	/*
	public void setId(int id) {
		this.id = id;
	}*/

	public BigDecimal getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getShipPrice() {
		return this.shipPrice;
	}

	public void setShipPrice(BigDecimal shipPrice) {
		this.shipPrice = shipPrice;
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
	
	@Override
    public int hashCode() {
        return 31;
    }

}