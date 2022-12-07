package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
	private Connection connection;

	public TeamDAO() {
		connection = ConnectDB.getInstance().getConnection();
	}
	
	// Lấy tất cả tên của tổ theo mã phân xưởng
	public List<String> getAllNameTeam(String factoryID) {
		List<String> listName = new ArrayList<String>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT TenTo, MaTo FROM TOSANXUAT WHERE MaPhanXuong = ?");
			stmt.setString(1, factoryID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String name = new String(rs.getString("MaTo") + " - " + rs.getString("TenTo"));
				listName.add(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listName;
	}
	
	// lấy tên của tổ theo mã tổ
	public String getNameTeamByID(String teamID) {
		String name = null;
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT TenTo, MaTo FROM TOSANXUAT WHERE MaTo = ?");
			stmt.setString(1, teamID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				name = new String(rs.getString("MaTo") + " - " + rs.getString("TenTo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
}
