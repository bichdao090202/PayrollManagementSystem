package entity;

import java.util.Date;

public class Worker extends Employee {
	private String speciality; // chuyên môn
	private String teamID;

	public Worker() {
		super();
	}
	
	public Worker(String name, boolean gender, Date birthday, String address, String phone,
			String bankName, String accountNumber, String beneficiany, String speciality, String teamID) {
		super(name, gender, birthday, address, phone, bankName, accountNumber, beneficiany);
		this.speciality = speciality;
		this.teamID = teamID;
	}

	public Worker(String employeeID, String name, boolean gender, Date birthday, String address, String phone,
			String bankName, String accountNumber, String beneficiany, String speciality, String teamID) {
		super(employeeID, name, gender, birthday, address, phone, bankName, accountNumber, beneficiany);
		this.speciality = speciality;
		this.teamID = teamID;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	@Override
	public String toString() {
		if (isGender() == true) {
			return getEmployeeID() + ";" + getName() + ";Nam;" + getBirthday() + ";" + getAddress() + ";" + getPhone();
		} else {
			return getEmployeeID() + ";" + getName() + ";Nữ;" + getBirthday() + ";" + getAddress() + ";" + getPhone();
		}
	}

}
