package entity;

import java.util.Objects;

public class TeamProducing {
	private String teamID;
	private String name;
	private String leaderID; // =empID
	private String factoryID;

	public TeamProducing() {
		super();
	}

	public TeamProducing(String teamID) {
		super();
		this.teamID = teamID;
	}

	public TeamProducing(String teamID, String name, String leaderID, String factoryID) {
		super();
		this.teamID = teamID;
		this.name = name;
		this.leaderID = leaderID;
		this.factoryID = factoryID;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeaderID() {
		return leaderID;
	}

	public void setLeaderID(String leaderID) {
		this.leaderID = leaderID;
	}

	public String getFactoryID() {
		return factoryID;
	}

	public void setFactoryID(String factoryID) {
		this.factoryID = factoryID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(teamID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamProducing other = (TeamProducing) obj;
		return Objects.equals(teamID, other.teamID);
	}

	@Override
	public String toString() {
		return "TeamProducing [teamID=" + teamID + ", name=" + name + ", leaderID=" + leaderID + ", factoryID="
				+ factoryID + "]";
	}

	
}
