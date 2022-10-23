package entity;

import java.util.Date;
import java.util.Objects;

//khen thưởng_kỹ luật
public class Bonus_Discipline {
	private String bonusDiscipline_ID;
	private String reason; // lý do
	private Date effectiveDate; // ngày áp dụng
	private float amountMoney;
	private String employeeID;

	public Bonus_Discipline() {
		super();
	}

	public Bonus_Discipline(String bonusDiscipline_ID) {
		super();
		this.bonusDiscipline_ID = bonusDiscipline_ID;
	}

	public Bonus_Discipline(String bonusDiscipline_ID, String reason, Date effectiveDate, float amountMoney,
			String employeeID) {
		super();
		this.bonusDiscipline_ID = bonusDiscipline_ID;
		this.reason = reason;
		this.effectiveDate = effectiveDate;
		this.amountMoney = amountMoney;
		this.employeeID = employeeID;
	}

	public String getBonusDiscipline_ID() {
		return bonusDiscipline_ID;
	}

	public void setBonusDiscipline_ID(String bonusDiscipline_ID) {
		this.bonusDiscipline_ID = bonusDiscipline_ID;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public float getAmountMoney() {
		return amountMoney;
	}

	public void setAmountMoney(float amountMoney) {
		this.amountMoney = amountMoney;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bonusDiscipline_ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bonus_Discipline other = (Bonus_Discipline) obj;
		return Objects.equals(bonusDiscipline_ID, other.bonusDiscipline_ID);
	}

	@Override
	public String toString() {
		return "Bonus_Discipline [bonusDiscipline_ID=" + bonusDiscipline_ID + ", reason=" + reason + ", effectiveDate="
				+ effectiveDate + ", amountMoney=" + amountMoney + ", employeeID=" + employeeID + "]";
	}

}
