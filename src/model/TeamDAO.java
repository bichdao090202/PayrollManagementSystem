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
	
	public List<String> getAllNameTeam(String factoryID) {
		List<String> listName = new ArrayList<String>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT TenTo FROM TOSANXUAT WHERE MaPhanXuong = ?");
			stmt.setString(1, factoryID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String name = new String(rs.getString("TenTo"));
				listName.add(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listName;
	}
}
