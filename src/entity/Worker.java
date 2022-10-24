package entity;

import java.util.Date;

public class Worker extends Employee {
	private String speciality; // chuyên môn
	private String teamID;

	public Worker() {
		super();
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
		return "Worker [employeeID=" + getEmployeeID() + ", name=" + getName() + ", gender=" + isGender() + ", birthday=" + getBirthday()
				+ ", address=" + getAddress() + ", phone=" + getPhone() + ", bankName=" + getBankName() + ", accountNumber="
				+ getAccountNumber() + ", beneficiany=" + getBeneficiany() + ", speciality=" + speciality + ", teamID=" + teamID
				+ "]";
	}

}
