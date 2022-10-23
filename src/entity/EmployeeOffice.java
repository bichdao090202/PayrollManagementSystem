package entity;

import java.util.Date;
import java.util.Objects;

//nhân viên hành chính
public class EmployeeOffice {
	private String employeeID;
	private String name;
	private boolean gender;
	private Date birthday;
	private String address;
	private String phone;
	private String bankName;
	private String accountNumber;
	private String beneficiany; // tên người thụ hưởng
	private float salary; // lương theo chức danh
	private String position;
	private String departmentID;

	public EmployeeOffice() {
		super();
	}

	public EmployeeOffice(String employeeID) {
		super();
		this.employeeID = employeeID;
	}

	public EmployeeOffice(String employeeID, String name, boolean gender, Date birthday, String address, String phone,
			String bankName, String accountNumber, String beneficiany, float salary, String position,
			String departmentID) {
		super();
		this.employeeID = employeeID;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
		this.phone = phone;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.beneficiany = beneficiany;
		this.salary = salary;
		this.position = position;
		this.departmentID = departmentID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBeneficiany() {
		return beneficiany;
	}

	public void setBeneficiany(String beneficiany) {
		this.beneficiany = beneficiany;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
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
	public int hashCode() {
		return Objects.hash(employeeID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeOffice other = (EmployeeOffice) obj;
		return Objects.equals(employeeID, other.employeeID);
	}

	@Override
	public String toString() {
//		return "Employee_Office [employeeID=" + employeeID + ", name=" + name + ", gender=" + gender + ", birthday="
//				+ birthday + ", address=" + address + ", phone=" + phone + ", bankName=" + bankName + ", accountNumber="
//				+ accountNumber + ", beneficiany=" + beneficiany + ", salary=" + salary + ", position=" + position
//				+ ", departmentID=" + departmentID + "]";
		if (gender == true) {
			return employeeID + "," + name + ",Nam," + birthday + "," + address  + "," + phone;
		}
		else {
			return employeeID + "," + name + ",Nữ," + birthday + "," + address  + "," + phone;
		}
	}
	
}