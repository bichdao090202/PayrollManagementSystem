package entity;

import java.util.Objects;

public class Product {
	private String productID;
	private String name;
	private int quantity; // số lượng

	public Product() {
		super();
	}

	public Product(String productID) {
		super();
		this.productID = productID;
	}

	public Product(String productID, String name, int quantity) {
		super();
		this.productID = productID;
		this.name = name;
		this.quantity = quantity;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productID, other.productID);
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", name=" + name + ", quantity=" + quantity + "]";
	}

}
