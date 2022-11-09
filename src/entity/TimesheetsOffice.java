package entity;

import java.util.Date;
import java.util.Objects;

// chấm công hành chính
public class TimesheetsOffice {

	private String timesheetID;
	private Date date;
	private Date checkInAM;
	private Date checkOutAM;
	private Date checkInPM;
	private Date checkOutPM;
	private String employeeID;

	public TimesheetsOffice() {
		super();
	}

	public TimesheetsOffice(Date date, String employeeID) {
		super();
		this.date = date;
		this.employeeID = employeeID;
	}

	public TimesheetsOffice(Date date, Date checkInAM, Date checkOutAM, Date checkInPM, Date checkOutPM,
			String employeeID) {
		super();
		this.date = date;
		this.checkInAM = checkInAM;
		this.checkOutAM = checkOutAM;
		this.checkInPM = checkInPM;
		this.checkOutPM = checkOutPM;
		this.employeeID = employeeID;
	}

	public TimesheetsOffice(String timesheetID, Date date, Date checkInAM, Date checkOutAM, Date checkInPM,
			Date checkOutPm, String employeeID) {
		super();
		this.timesheetID = timesheetID;
		this.date = date;
		this.checkInAM = checkInAM;
		this.checkOutAM = checkOutAM;
		this.checkInPM = checkInPM;
		this.checkOutPM = checkOutPm;
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

	public Date getCheckOutPM() {
		return checkOutPM;
	}

	public void setCheckOutPM(Date checkOutPm) {
		this.checkOutPM = checkOutPm;
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
		TimesheetsOffice other = (TimesheetsOffice) obj;
		return Objects.equals(timesheetID, other.timesheetID);
	}

	@Override
	public String toString() {
		if (checkInAM == null) {
			return employeeID + "," + date + ", , ," + checkInPM + "," + checkOutPM;
		} else if (checkInPM == null) {
			return employeeID + "," + date + "," + checkInAM + "," + checkOutAM + ", , ";
		} else {
			return employeeID + "," + date + "," + checkInAM + "," + checkOutAM + "," + checkInPM + "," + checkOutPM;
		}

	}

}
