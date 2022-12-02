package entity;

import java.util.Date;
import java.util.Objects;
//chấm công sản xuất
public class TimesheetFactory {
	private int timesheetID;
	private Date date;
	private int quantity; // số lượng
	private int assignmentID;

	public TimesheetFactory() {
		super();

	}

	public TimesheetFactory(int timesheetID) {
		super();
		this.timesheetID = timesheetID;
	}

	public TimesheetFactory(int timesheetID, Date date, int quantity, int assignmentID) {
		super();
		this.timesheetID = timesheetID;
		this.date = date;
		this.quantity = quantity;
		this.assignmentID = assignmentID;
	}
	
	public TimesheetFactory(int timesheetID, Date date, int quantity) {
		super();
		this.timesheetID = timesheetID;
		this.date = date;
		this.quantity = quantity;
	}
	
	public TimesheetFactory(Date date, int quantity, int assignmentID) {
		super();
		this.date = date;
		this.quantity = quantity;
		this.assignmentID = assignmentID;
	}

	public int getTimesheetID() {
		return timesheetID;
	}

	public void setTimesheetID(int timesheetID) {
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

	public int getAssignmentID() {
		return assignmentID;
	}

	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
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
		TimesheetFactory other = (TimesheetFactory) obj;
		return Objects.equals(timesheetID, other.timesheetID);
	}

	@Override
	public String toString() {
		return "Timesheets_Factory [timesheetID=" + timesheetID + ", date=" + date + ", quantity=" + quantity
				+ ", assignmentID=" + assignmentID + "]";
	}

}
