package entity;

import java.util.Date;
import java.util.Objects;

// chấm công hành chính
public class Timesheets_Office {

	private String timesheetID;
	private Date date;
	private Date checkInAM;
	private Date checkOutAM;
	private Date checkInPM;
	private Date checkOutPm;
	private String employeeID;

	public Timesheets_Office() {
		super();
	}

	public Timesheets_Office(String timesheetID) {
		super();
		this.timesheetID = timesheetID;
	}

	public Timesheets_Office(String timesheetID, Date date, Date checkInAM, Date checkOutAM, Date checkInPM,
			Date checkOutPm, String employeeID) {
		super();
		this.timesheetID = timesheetID;
		this.date = date;
		this.checkInAM = checkInAM;
		this.checkOutAM = checkOutAM;
		this.checkInPM = checkInPM;
		this.checkOutPm = checkOutPm;
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

	public Date getCheckInAM() {
		return checkInAM;
	}

	public void setCheckInAM(Date checkInAM) {
		this.checkInAM = checkInAM;
	}

	public Date getCheckOutAM() {
		return checkOutAM;
	}

	public void setCheckOutAM(Date checkOutAM) {
		this.checkOutAM = checkOutAM;
	}

	public Date getCheckInPM() {
		return checkInPM;
	}

	public void setCheckInPM(Date checkInPM) {
		this.checkInPM = checkInPM;
	}

	public Date getCheckOutPm() {
		return checkOutPm;
	}

	public void setCheckOutPm(Date checkOutPm) {
		this.checkOutPm = checkOutPm;
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
		Timesheets_Office other = (Timesheets_Office) obj;
		return Objects.equals(timesheetID, other.timesheetID);
	}

	@Override
	public String toString() {
		return "Timesheets_Office [timesheetID=" + timesheetID + ", date=" + date + ", checkInAM=" + checkInAM
				+ ", checkOutAM=" + checkOutAM + ", checkInPM=" + checkInPM + ", checkOutPm=" + checkOutPm
				+ ", employeeID=" + employeeID + "]";
	}

}
