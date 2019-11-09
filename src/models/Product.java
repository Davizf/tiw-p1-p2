package models;

public class Product {

	private String photo_path;
	private String name;
	
	public Product(String photo_path, String name) {
		super();
		this.photo_path = photo_path;
		this.name = name;
	}
	
	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
