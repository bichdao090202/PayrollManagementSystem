package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.TimesheetFactory;


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
				String assignment = rs.getInt("MaPhanCong") +";"+ rs.getString("NhanVien") + ";" + rs.getString("SanPham")+ ";" + rs.getString("QuyTrinh") + ";" + rs.getDate("NgayThamGia");
				listAssignment.add(assignment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listAssignment;
	}
	
	public List<String> getAllTimesheetFactory() {
		List<String> listTimesheets = new ArrayList<String>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM VIEW_CHAMCONGSX");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String timesheet = rs.getInt("MaChamCong") +";"+ rs.getString("NhanVien") + ";" + rs.getString("SanPham")+ ";" + rs.getString("QuyTrinh") + ";" + rs.getDate("NgayChamCong") +";"+ rs.getInt("SoLuongThanhPham");
				listTimesheets.add(timesheet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTimesheets;
	}
	
	public boolean addTimesheetsFactory(TimesheetFactory timesheetsFactory) {
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO CHAMCONGSANXUAT (NgayChamCong, SoLuongThanhPham, MaPhanCong) values(?,?,?)");
			stmt.setDate(1, new java.sql.Date(timesheetsFactory.getDate().getTime()));
			stmt.setInt(2, timesheetsFactory.getQuantity());
			stmt.setInt(3, timesheetsFactory.getAssignmentID());
			int insertResult = stmt.executeUpdate();
			if (insertResult > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateTimesheetsFactory(TimesheetFactory timesheetsFactory) {
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"UPDATE CHAMCONGSANXUAT SET NgayChamCong = ?, SoLuongThanhPham = ? WHERE MaChamCong = ?");
			stmt.setDate(1, new java.sql.Date(timesheetsFactory.getDate().getTime()));
			stmt.setInt(2, timesheetsFactory.getQuantity());
			stmt.setInt(3, timesheetsFactory.getTimesheetID());
			int insertResult = stmt.executeUpdate();
			if (insertResult > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
