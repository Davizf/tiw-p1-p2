package models;

public class ProductInWishList {

	private String path;
	private String name;
	
	public ProductInWishList(String path, String name) {
		super();
		this.path = path;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path= path;
	}

	
}
