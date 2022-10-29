package entity;

import java.util.Date;
import java.util.Objects;

public class Assignment {
	private String assignmentID;
	private String produreID;
	private String workerID;
	private Date date;

	public Assignment() {
		super();
	}

	public Assignment(String assignmentID, String produreID, String workerID) {
		super();
		this.assignmentID = assignmentID;
		this.produreID = produreID;
		this.workerID = workerID;
		this.date = new Date();
	}

	public String getAssignmentID() {
		return assignmentID;
	}

	public void setAssignmentID(String assignmentID) {
		this.assignmentID = assignmentID;
	}

	public String getProdureID() {
		return produreID;
	}

	public void setProdureID(String produreID) {
		this.produreID = produreID;
	}

	public String getWorkerID() {
		return workerID;
	}

	public void setWorkerID(String workerID) {
		this.workerID = workerID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(assignmentID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assignment other = (Assignment) obj;
		return Objects.equals(assignmentID, other.assignmentID);
	}

	@Override
	public String toString() {
		return "Assignment [assignmentID=" + assignmentID + ", produreID=" + produreID + ", workerID=" + workerID
				+ ", date=" + date + "]";
	}

}
