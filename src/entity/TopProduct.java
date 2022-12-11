package entity;

import java.util.Objects;

public class TopProduct {
	private String productID;
	private String name;
	private int quantityDetail;
	
	public TopProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TopProduct(String productID, String name, int quantityDetail) {
		super();
		this.productID = productID;
		this.name = name;
		this.quantityDetail = quantityDetail;
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

	public int getQuantityDetail() {
		return quantityDetail;
	}

	public void setQuantityDetail(int quantityDetail) {
		this.quantityDetail = quantityDetail;
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
		TopProduct other = (TopProduct) obj;
		return Objects.equals(productID, other.productID);
	}

	@Override
	public String toString() {
		return "TopProduct [productID=" + productID + ", name=" + name + ", quantityDetail=" + quantityDetail + "]";
	}
	
	
}
