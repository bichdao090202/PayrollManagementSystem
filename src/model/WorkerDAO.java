package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;
import entity.Worker;

public class WorkerDAO {
	private Connection connection;

	public WorkerDAO() {
		connection = ConnectDB.getInstance().getConnection();
	}
	
	public List<Employee> getAllWorker() {
		List<Employee> listEmp = new ArrayList<Employee>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM NHANVIENSANXUAT");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Employee emp = new Worker(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),
						rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"),
						rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"),
						rs.getString("ChuyenMon"), rs.getString("MaTo"));
				listEmp.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmp;
	}
	
	public Employee getWorker(String empID) {
		Employee emp = null;
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM NHANVIENSANXUAT WHERE MaNhanVien = ?");
			stmt.setString(1, empID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				emp = new Worker(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),
						rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"),
						rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"),
						rs.getString("ChuyenMon"), rs.getString("MaTo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	public boolean deleteEmployee(String empID) {
		try {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM NHANVIENSANXUAT WHERE MaNhanVien = ?");
			stmt.setString(1, empID);
			int result = stmt.executeUpdate();
			if (result < 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
