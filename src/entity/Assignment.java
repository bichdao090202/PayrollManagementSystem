package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Assignment {
	private String assignmentID;
	private String produreID;
	private String workerID;
	private LocalDate date;
	private String detailProductionID;

	public Assignment() {
		super();
	}
	
	public Assignment(String assignmentID, String produreID, String workerID) {
		super();
		this.assignmentID = assignmentID;
		this.produreID = produreID;
		this.workerID = workerID;
	}

	public Assignment(String assignmentID, String produreID, String workerID, LocalDate date) {
		super();
		this.assignmentID = assignmentID;
		this.produreID = produreID;
		this.workerID = workerID;
		this.date = date;
	}
	
	public Assignment(String assignmentID, String produreID, String workerID, LocalDate date, String detailProductionID) {
		super();
		this.assignmentID = assignmentID;
		this.produreID = produreID;
		this.workerID = workerID;
		this.date = date;
		this.detailProductionID = detailProductionID;
	}
	
	public Assignment(String produreID, String workerID, LocalDate date) {
		super();
		this.produreID = produreID;
		this.workerID = workerID;
		this.date = date;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDetailProductionID() {
		return detailProductionID;
	}

	public void setDetailProductionID(String detailProductionID) {
		this.detailProductionID = detailProductionID;
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
