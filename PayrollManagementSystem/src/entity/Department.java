package entity;

import java.util.Objects;

public class Department {
	private String departmentID;
	private String name;
	private String managerID;

	public Department() {
		super();
	}

	/*Xem xét bỏ*/
	public Department(String departmentID) {
		super();
		this.departmentID = departmentID;
	}

	public Department(String departmentID, String name) {
		super();
		this.departmentID = departmentID;
		this.name = name;
	}

	public Department(String departmentID, String name, String managerID) {
		super();
		this.departmentID = departmentID;
		this.name = name;
		this.managerID = managerID;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManagerID() {
		return managerID;
	}

	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departmentID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(departmentID, other.departmentID);
	}

	@Override
	public String toString() {
		return "Department [departmentID=" + departmentID + ", name=" + name + ", managerID=" + managerID + "]";
	}

}
