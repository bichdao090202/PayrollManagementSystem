package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;
import entity.Worker;

public class WorkerDAO {
	private Connection connection;

	public WorkerDAO() {
		connection = ConnectDB.getInstance().getConnection();
	}
	
	public List<Employee> getAllEmployeeOffice() {
		List<Employee> listEmp = new ArrayList<Employee>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM NHANVIENHANHCHINH");
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
	
}
