package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class TimesheetsFactoryDAO {
	private Connection connection;
	
	public TimesheetsFactoryDAO() {
		connection = ConnectDB.getInstance().getConnection();
	}
	
	public List<String> getAllAssignment() {
		List<String> listAssignment = new ArrayList<String>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM VIEW_PHANCONG");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String assignment = rs.getString("MaNhanVien") + ";" + rs.getString("TenNhanVien") + ";" + rs.getString("MaQuyTrinh")+ ";" + rs.getString("TenQuyTrinh") + ";" + rs.getDate("NgayThamGia");
				listAssignment.add(assignment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listAssignment;
	}
}
