package entity;

import java.util.Objects;

//quy trình
public class Produre {
	private String procedureID;
	private String name;
	private double price;
	private int numberOrdinal; // thứ tự
	private String productID;

	public Produre() {
		super();
	}

	public Produre(String procedureID) {
		super();
		this.procedureID = procedureID;
	}

	public Produre(String procedureID, String name, double price, int numberOrdinal, String productID) {
		super();
		this.procedureID = procedureID;
		this.name = name;
		this.price = price;
		this.numberOrdinal = numberOrdinal;
		this.productID = productID;
	}

	public String getProcedureID() {
		return procedureID;
	}

	public void setProcedureID(String procedureID) {
		this.procedureID = procedureID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumberOrdinal() {
		return numberOrdinal;
	}

	public void setNumberOrdinal(int numberOrdinal) {
		this.numberOrdinal = numberOrdinal;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(procedureID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produre other = (Produre) obj;
		return Objects.equals(procedureID, other.procedureID);
	}

	@Override
	public String toString() {
		return "Produre [procedureID=" + procedureID + ", name=" + name + ", price=" + price + ", numberOrdinal="
				+ numberOrdinal + ", productID=" + productID + "]";
	}

}