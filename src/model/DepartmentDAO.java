package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Department;

public class DepartmentDAO {
	private Connection connection;

	public DepartmentDAO() {
		connection = ConnectDB.getInstance().getConnection();
	}

	public boolean addDepartment(Department department) {
		if (department.getManagerID() != null) {
			try {
				PreparedStatement stmt = connection
						.prepareStatement("INSERT INTO PHONGBAN (MaPhongBan,TenPhongBan, MaTruongPhong) values(?,?,?)");
				stmt.setString(1, department.getDepartmentID());
				stmt.setString(2, department.getName());
				stmt.setString(3, department.getManagerID());
				int insertResult = stmt.executeUpdate();
				if (insertResult > 0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			try {
				PreparedStatement stmt = connection
						.prepareStatement("INSERT INTO PHONGBAN (MaPhongBan,TenPhongBan) values(?,?)");
				stmt.setString(1, department.getDepartmentID());
				stmt.setString(2, department.getName());
				int insertResult = stmt.executeUpdate();
				if (insertResult > 0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public boolean deleteDepartment(String departmentID) {
		try {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM PHONGBAN WHERE MaPhongBan = ?");
			stmt.setString(1, departmentID);
			int deleteResult = stmt.executeUpdate();
			if (deleteResult > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Department> getAllDepartments() {
		List<Department> departments = new ArrayList<Department>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM PHONGBAN");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Department dept = new Department(rs.getString("MaPhongBan"), rs.getString("TenPhongBan"),
						rs.getString("MaTruongPhong"));
				departments.add(dept);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departments;
	}
}
