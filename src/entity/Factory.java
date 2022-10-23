package entity;

import java.util.Objects;

public class Factory {
	private String factoryID;
	private String name;
	private String headForemanID;//quản đốc

	public Factory() {
		super();
	}

	public Factory(String factoryID) {
		super();
		this.factoryID = factoryID;
	}

	public Factory(String factoryID, String name, String headForemanID) {
		super();
		this.factoryID = factoryID;
		this.name = name;
		this.headForemanID = headForemanID;
	}

	public String getFactoryID() {
		return factoryID;
	}

	public void setFactoryID(String factoryID) {
		this.factoryID = factoryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadForemanID() {
		return headForemanID;
	}

	public void setHeadForemanID(String headForemanID) {
		this.headForemanID = headForemanID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(factoryID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factory other = (Factory) obj;
		return Objects.equals(factoryID, other.factoryID);
	}

	@Override
	public String toString() {
		return "Factory [factoryID=" + factoryID + ", name=" + name + ", headForemanID=" + headForemanID + "]";
	}

}
