package Entites;

public class Product {
	
	private int idProduct;
	private String nameProduct;
	private float price;
	
	public Product(int idProduct, String nameProduct, float price) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.price = price;
	}
	
	public Product(String nameProduct, float price) {
		super();
		this.nameProduct = nameProduct;
		this.price = price;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return nameProduct;
	}


}
