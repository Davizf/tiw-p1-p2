package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Orders.findAll", query="SELECT o FROM Orders o")
@NamedQuery(name="Orders.OrderById", query="SELECT o FROM Orders o ORDER BY o.id DESC")
public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String address;

	private String city;

	private String country;

	private String date;

	@Column(name="postal_code")
	private int postalCode;

	//bi-directional many-to-one association to User
	@ManyToOne()
	@JoinColumn(name="user")
	private User userBean;

	//bi-directional many-to-one association to Orders_has_Product
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Orders_has_Product> ordersHasProducts;

	public Orders() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

	public List<Orders_has_Product> getOrdersHasProducts() {
		return this.ordersHasProducts;
	}

	public void setOrdersHasProducts(List<Orders_has_Product> ordersHasProducts) {
		this.ordersHasProducts = ordersHasProducts;
	}

	public Orders_has_Product addOrdersHasProduct(Orders_has_Product ordersHasProduct) {
		getOrdersHasProducts().add(ordersHasProduct);
		ordersHasProduct.setOrder(this);

		return ordersHasProduct;
	}

	public Orders_has_Product removeOrdersHasProduct(Orders_has_Product ordersHasProduct) {
		getOrdersHasProducts().remove(ordersHasProduct);
		ordersHasProduct.setOrder(null);

		return ordersHasProduct;
	}

}