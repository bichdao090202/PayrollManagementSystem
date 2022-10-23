package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.Account;
import entity.Department;

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
}
