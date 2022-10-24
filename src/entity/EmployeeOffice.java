package entity;

import java.util.Date;

//nhân viên hành chính
public class EmployeeOffice extends Employee {
	private double salary; // lương theo chức danh
	private String position;
	private String departmentID;

	public EmployeeOffice() {
		super();
	}

	public EmployeeOffice(String employeeID, String name, boolean gender, Date birthday, String address, String phone,
			String bankName, String accountNumber, String beneficiany, double salary, String position,
			String departmentID) {
		super(employeeID, name, gender, birthday, address, phone, bankName, accountNumber, beneficiany);
		this.salary = salary;
		this.position = position;
		this.departmentID = departmentID;
	}

	public EmployeeOffice(double salary, String position, String departmentID) {
		super();
		this.salary = salary;
		this.position = position;
		this.departmentID = departmentID;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	@Override
	public String toString() {
		if (isGender() == true) {
			return getEmployeeID() + "," + getName() + ",Nam," + getBirthday() + "," + getAddress() + "," + getPhone();
		} else {
			return getEmployeeID() + "," + getName() + ",Nữ," + getBirthday() + "," + getAddress() + "," + getPhone();
		}
	}

}