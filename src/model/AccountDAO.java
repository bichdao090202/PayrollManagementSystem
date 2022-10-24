package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class AccountDAO {
	
	private Connection connection;
	
	public AccountDAO() {
		connection = ConnectDB.getInstance().getConnection();
	}
	
//	public boolean checkLogin(Account account) {
//		try {
//			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM PHONGBAN");
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				Department dept = new Department(rs.getString("MaPhongBan"), rs.getString("TenPhongBan"),
//						rs.getString("MaTruongPhong"));
//				departments.add(dept);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public HashMap<String, String> getPasswordEncryption(String username) {
		HashMap<String, String> account = new HashMap<String, String>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT MatKhau, GiaTriSalt FROM TAIKHOAN WHERE TenDangNhap = ?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				account.put("username", username);
				account.put("password hash", rs.getString("MatKhau"));
				account.put("salt", rs.getString("GiaTriSalt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}
}
