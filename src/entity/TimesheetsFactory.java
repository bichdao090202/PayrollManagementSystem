package entity;

import java.util.Date;
import java.util.Objects;
//chấm công sản xuất
public class TimesheetsFactory {
	private String timesheetID;
	private Date date;
	private int quantity; // số lượng
	private String assignmentID;

	public TimesheetsFactory() {
		super();

	}

	public TimesheetsFactory(String timesheetID) {
		super();
		this.timesheetID = timesheetID;
	}

	public TimesheetsFactory(String timesheetID, Date date, int quantity, String assignmentID) {
		super();
		this.timesheetID = timesheetID;
		this.date = date;
		this.quantity = quantity;
		this.assignmentID = assignmentID;
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

	public String getassignmentID() {
		return assignmentID;
	}

	public void setassignmentID(String assignmentID) {
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
		TimesheetsFactory other = (TimesheetsFactory) obj;
		return Objects.equals(timesheetID, other.timesheetID);
	}

	@Override
	public String toString() {
		return "Timesheets_Factory [timesheetID=" + timesheetID + ", date=" + date + ", quantity=" + quantity
				+ ", assignmentID=" + assignmentID + "]";
	}

}
