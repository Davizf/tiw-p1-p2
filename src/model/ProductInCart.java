package model;

public class ProductInCart {

	private Product product;
	private int quantity;

	public ProductInCart() {
	}
	public ProductInCart(Product p) {
		product=p;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}