package entity;

import java.util.Date;
import java.util.Objects;

//khen thưởng_kỹ luật
public class Bonus_Discipline {
	private String bonusDiscipline_ID;
	private String reason; // lý do
	private Date effectiveDate; // ngày áp dụng
	private double amountMoney;
	private String idEmployeeProductive;
	private String idEmployeeAdministrative;

	public Bonus_Discipline() {
		super();
	}

	public Bonus_Discipline(String bonusDiscipline_ID) {
		super();
		this.bonusDiscipline_ID = bonusDiscipline_ID;
	}

	public Bonus_Discipline(String bonusDiscipline_ID, String reason, Date effectiveDate, double amountMoney,
			String idEmployeeProductive, String idEmployeeAdministrative) {
		super();
		this.bonusDiscipline_ID = bonusDiscipline_ID;
		this.reason = reason;
		this.effectiveDate = effectiveDate;
		this.amountMoney = amountMoney;
		this.idEmployeeProductive = idEmployeeProductive;
		this.idEmployeeAdministrative = idEmployeeAdministrative;
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

	public double getAmountMoney() {
		return amountMoney;
	}

	public void setAmountMoney(double amountMoney) {
		this.amountMoney = amountMoney;
	}

	public String getIdEmployeeProductive() {
		return idEmployeeProductive;
	}

	public void setIdEmployeeProductive(String idEmployeeProductive) {
		this.idEmployeeProductive = idEmployeeProductive;
	}

	public String getIdEmployeeAdministrative() {
		return idEmployeeAdministrative;
	}

	public void setIdEmployeeAdministrative(String idEmployeeAdministrative) {
		this.idEmployeeAdministrative = idEmployeeAdministrative;
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
				+ effectiveDate + ", amountMoney=" + amountMoney + ", idEmployeeProductive=" + idEmployeeProductive
				+ ", idEmployeeAdministrative=" + idEmployeeAdministrative + "]";
	}

	
}
