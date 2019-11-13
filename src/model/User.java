package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Users database table.
 * 
 */
@Entity
@Table(name="Users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private String address;

	private String city;

	private String country;

	@Column(name="credit_card")
	private String creditCard;

	private int credit_card_CVV;

	@Column(name="credit_card_expiration")
	private String creditCardExpiration;

	//@Column(name="CURRENT_CONNECTIONS")
	//private BigInteger currentConnections;

	private String name;

	private String password;

	private int phone;

	@Column(name="postal_code")
	private int postalCode;

	private int seller;

	private String surnames;

	//@Column(name="TOTAL_CONNECTIONS")
	//private BigInteger totalConnections;

	//private String user;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="userBean")
	private List<Order> orders;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="userBean")
	private List<Product> products;

	//bi-directional one-to-one association to WishList
	@OneToOne(mappedBy="userBean")
	private WishList wishList;

	public User() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public int getCredit_card_CVV() {
		return this.credit_card_CVV;
	}

	public void setCredit_card_CVV(int credit_card_CVV) {
		this.credit_card_CVV = credit_card_CVV;
	}

	public String getCreditCardExpiration() {
		return this.creditCardExpiration;
	}

	public void setCreditCardExpiration(String creditCardExpiration) {
		this.creditCardExpiration = creditCardExpiration;
	}

	//public BigInteger getCurrentConnections() {
	//	return this.currentConnections;
	//}

	//public void setCurrentConnections(BigInteger currentConnections) {
	//	this.currentConnections = currentConnections;
	//}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPhone() {
		return this.phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public int getSeller() {
		return this.seller;
	}

	public void setSeller(int seller) {
		this.seller = seller;
	}

	public String getSurnames() {
		return this.surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	/*public BigInteger getTotalConnections() {
		return this.totalConnections;
	}

	public void setTotalConnections(BigInteger totalConnections) {
		this.totalConnections = totalConnections;
	}*/

	/*public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}*/

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setUserBean(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setUserBean(null);

		return order;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setUserBean(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setUserBean(null);

		return product;
	}

	public WishList getWishList() {
		return this.wishList;
	}

	public void setWishList(WishList wishList) {
		this.wishList = wishList;
	}

}