package entity;

import java.util.Date;
import java.util.Objects;

public class DetailProduction {
	private String detailProductionID;
	private int quantityProduction;
	private String state;
	private String productId;
	private Date date;
	private int orders;
	
	public DetailProduction() {
		// TODO Auto-generated constructor stub
	}

	public DetailProduction(String detailProductionID, int quantityProduction, String state, String productId,
			Date date, int orders) {
		super();
		this.detailProductionID = detailProductionID;
		this.quantityProduction = quantityProduction;
		this.state = state;
		this.productId = productId;
		this.date = date;
		this.orders = orders;
	}

	public String getDetailProductionID() {
		return detailProductionID;
	}

	public void setDetailProductionID(String detailProductionID) {
		this.detailProductionID = detailProductionID;
	}

	public int getQuantityProduction() {
		return quantityProduction;
	}

	public void setQuantityProduction(int quantityProduction) {
		this.quantityProduction = quantityProduction;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(detailProductionID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailProduction other = (DetailProduction) obj;
		return Objects.equals(detailProductionID, other.detailProductionID);
	}

	@Override
	public String toString() {
		return "DetailProduction [detailProductionID=" + detailProductionID + ", quantityProduction="
				+ quantityProduction + ", state=" + state + ", productId=" + productId + ", date=" + date + ", orders="
				+ orders + "]";
	}

	
}
