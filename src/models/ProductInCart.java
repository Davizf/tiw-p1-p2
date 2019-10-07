package models;

public class ProductInCart {

	private String path;
	private String name;
	private int num;
	
	public ProductInCart(String path, String name, int num) {
		super();
		this.path = path;
		this.name = name;
		this.num = num;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
