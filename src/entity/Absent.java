package entity;

import java.util.Date;
import java.util.Objects;

//nghỉ phép
public class Absent {
	private String absentID;
	private Date startDay;
	private Date endDay;
	private String reason; // lý do
	private float extraMoney; // hưởng lương
	private String employeeID;

	public Absent() {
		super();
	}

	public Absent(String absentID) {
		super();
		this.absentID = absentID;
	}

	public Absent(String absentID, Date startDay, Date endDay, String reason, float extraMoney, String employeeID) {
		super();
		this.absentID = absentID;
		this.startDay = startDay;
		this.endDay = endDay;
		this.reason = reason;
		this.extraMoney = extraMoney;
		this.employeeID = employeeID;
	}

	public String getAbsentID() {
		return absentID;
	}

	public void setAbsentID(String absentID) {
		this.absentID = absentID;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public float getExtraMoney() {
		return extraMoney;
	}

	public void setExtraMoney(float extraMoney) {
		this.extraMoney = extraMoney;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	@Override
	public String toString() {
		return "Absent [absentID=" + absentID + ", startDay=" + startDay + ", endDay=" + endDay + ", reason=" + reason
				+ ", extraMoney=" + extraMoney + ", employeeID=" + employeeID + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(absentID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Absent other = (Absent) obj;
		return Objects.equals(absentID, other.absentID);
	}

}
