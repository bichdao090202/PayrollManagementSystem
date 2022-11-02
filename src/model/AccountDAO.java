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
	
	public boolean createAccount(String id) {
		try {			
			PreparedStatement stmt = connection.prepareStatement(
					"insert into TaiKhoan values (?,'V+FJJVVD6UnIU7+iVlRvA9tvnDrZ8rt8lWgNjbVqWtM=','9wlad4hsa9l9fflj99f4s9lndll28s',null,?)");
			stmt.setString(1, id);
			stmt.setString(2, id);
			int insertResult = stmt.executeUpdate();
			if (insertResult > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteAccount (String id) {
		int n = 0;
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from TaiKhoan where TenDangNhap = ?");
			stmt.setString(1, id);
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean setDefaultPassword (String id) {
		int n = 0;
		try {
			PreparedStatement stmt = connection.prepareStatement("update TaiKhoan set matKhau = 'V+FJJVVD6UnIU7+iVlRvA9tvnDrZ8rt8lWgNjbVqWtM=', GiaTriSalt = '9wlad4hsa9l9fflj99f4s9lndll28s' where TenDangNhap = ?");
			stmt.setString(1, id);
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public Boolean checkAccByEmpID (String empID) {
		Integer num=0;
		try {
			PreparedStatement stmt = connection.prepareStatement("select "
			        + "number = count(MaNhanVien) from NhanVienHanhChinh E join TaiKhoan A "
			        + "on E.MaNhanVien = A.TenDangNhap where E.MaNhanVien = ?");
			stmt.setString(1, empID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("number");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num>0?true:false;
	}
	
	
	
	
}
