package entity;

import java.util.Date;

public class Worker extends Employee {
	private String speciality; // chuyên môn
	private String teamID;
//	private String position;

	public Worker() {
		super();
	}

	public Worker(String name, boolean gender, Date birthday, String address, String phone, String bankName,
			String accountNumber, String beneficiany, String speciality, String teamID) {
		super(name, gender, birthday, address, phone, bankName, accountNumber, beneficiany, null);
		this.speciality = speciality;
		this.teamID = teamID;
	}

	public Worker(String employeeID, String name, boolean gender, Date birthday, String address, String phone,
			String bankName, String accountNumber, String beneficiany, String speciality, String teamID) {
		super(employeeID, name, gender, birthday, address, phone, bankName, accountNumber, beneficiany, null);
		this.speciality = speciality;
		this.teamID = teamID;
	}

	public Worker(String employeeID, String name, boolean gender, Date birthday, String address, String phone,
			String bankName, String accountNumber, String beneficiany, String speciality, String teamID,
			String position) {
		super(employeeID, name, gender, birthday, address, phone, bankName, accountNumber, beneficiany, position);
		this.speciality = speciality;
		this.teamID = teamID;
//		this.position = position;
	}

	public Worker(String name, boolean gender, Date birthday, String address, String phone, String bankName,
			String accountNumber, String beneficiany, String speciality, String teamID, String position) {
		super(name, gender, birthday, address, phone, bankName, accountNumber, beneficiany, position);
		this.speciality = speciality;
		this.teamID = teamID;
//		this.position = position;
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
			return super.getEmployeeID() + ";" + getName() + ";Nam;" + getBirthday() + ";" + getAddress() + ";"
					+ getPhone();
		} else {
			return super.getEmployeeID() + ";" + getName() + ";Nữ;" + getBirthday() + ";" + getAddress() + ";"
					+ getPhone();
		}
	}
//
//	public String getPosition() {
//		return position;
//	}
//
//	public void setPosition(String position) {
//		this.position = position;
//	}

}
