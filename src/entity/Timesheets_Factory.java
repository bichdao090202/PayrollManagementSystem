package entity;

import java.util.Date;
import java.util.Objects;
//chấm công sản xuất
public class Timesheets_Factory {
	private String timesheetID;
	private Date date;
	private int quantity; // số lượng
	private String procedureID; // quy trình
	private String employeeID;

	public Timesheets_Factory() {
		super();

	}

	public Timesheets_Factory(String timesheetID) {
		super();
		this.timesheetID = timesheetID;
	}

	public Timesheets_Factory(String timesheetID, Date date, int quantity, String procedureID, String employeeID) {
		super();
		this.timesheetID = timesheetID;
		this.date = date;
		this.quantity = quantity;
		this.procedureID = procedureID;
		this.employeeID = employeeID;
	}

	public String getTimesheetID() {
		return timesheetID;
	}

	public void setTimesheetID(String timesheetID) {
		this.timesheetID = timesheetID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProcedureID() {
		return procedureID;
	}

	public void setProcedureID(String procedureID) {
		this.procedureID = procedureID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(timesheetID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timesheets_Factory other = (Timesheets_Factory) obj;
		return Objects.equals(timesheetID, other.timesheetID);
	}

	@Override
	public String toString() {
		return "Timesheets_Factory [timesheetID=" + timesheetID + ", date=" + date + ", quantity=" + quantity
				+ ", procedureID=" + procedureID + ", employeeID=" + employeeID + "]";
	}

}
