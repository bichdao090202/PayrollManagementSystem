package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;
import entity.EmployeeOffice;

public class EmployeeOfficeDAO {
	private Connection connection;
	/**
	 * fix
	 */

	public EmployeeOfficeDAO() {
		connection = ConnectDB.getInstance().getConnection();
	}

	public List<EmployeeOffice> getAllEmployeeOffice() {
		List<EmployeeOffice> listEmp = new ArrayList<EmployeeOffice>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM NHANVIENHANHCHINH");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				EmployeeOffice emp = new EmployeeOffice(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),
						rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"),
						rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"),
						rs.getDouble("LuongTheoChucDanh"), rs.getString("ChucVu"), rs.getString("MaPhongBan"));
				listEmp.add(emp);
//				System.out.print(emp.getPosition());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmp;
	}

	public boolean addEmployeeOffice(EmployeeOffice emp) {
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO NHANVIENHANHCHINH (MaNhanVien, TenNhanVien, GioiTinh, NgaySinh, DiaChi, SDT, LuongTheoChucDanh, ChucVu, MaPhongBan) values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, emp.getEmployeeID());
			stmt.setString(2, emp.getName());
			stmt.setBoolean(3, emp.isGender());
			stmt.setDate(4, new java.sql.Date(emp.getBirthday().getTime()));
			stmt.setString(5, emp.getAddress());
			stmt.setString(6, emp.getPhone());
			stmt.setDouble(7, emp.getSalary());
			stmt.setString(8, emp.getPosition());
			stmt.setString(9, emp.getDepartmentID());
			int insertResult = stmt.executeUpdate();
			if (insertResult > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getDepNameByEmpID (String empID) {
		String depName = null;
		try {
			PreparedStatement stmt = connection.prepareStatement("select D.TenPhongBan from NhanVienHanhChinh E join PhongBan D on E.MaPhongBan = D.MaPhongBan where E.MaNhanVien = ?");
			stmt.setString(1, empID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				depName = rs.getString("TenPhongBan");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return depName;
	}
	
}
