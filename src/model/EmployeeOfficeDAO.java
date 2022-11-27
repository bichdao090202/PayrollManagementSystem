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

	public List<Employee> getAllEmployeeOffice() {
		List<Employee> listEmp = new ArrayList<Employee>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM NHANVIENHANHCHINH");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Employee emp = new EmployeeOffice(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),
						rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"),
						rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"),
						rs.getDouble("LuongTheoChucDanh"), rs.getString("ChucVu"), rs.getString("MaPhongBan"));
				listEmp.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmp;
	}

	public Employee getEmployeeOffice(String empID) {
		Employee emp = null;
		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM NHANVIENHANHCHINH WHERE MaNhanVien = ?");
			stmt.setString(1, empID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				emp = new EmployeeOffice(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),
						rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"),
						rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"),
						rs.getDouble("LuongTheoChucDanh"), rs.getString("ChucVu"), rs.getString("MaPhongBan"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	public boolean addEmployeeOffice(Employee emp) {
		try {
			PreparedStatement stmt2 = connection.prepareStatement(
					"INSERT INTO NHANVIENHANHCHINH (TenNhanVien, GioiTinh, NgaySinh, DiaChi, SDT, LuongTheoChucDanh, ChucVu, MaPhongBan) values(?,?,?,?,?,?,?,?)");
			stmt2.setString(1, emp.getName());
			stmt2.setBoolean(2, emp.isGender());
			stmt2.setDate(3, new java.sql.Date(emp.getBirthday().getTime()));
			stmt2.setString(4, emp.getAddress());
			stmt2.setString(5, emp.getPhone());
			stmt2.setDouble(6, ((EmployeeOffice) emp).getSalary());
			stmt2.setString(7, ((EmployeeOffice) emp).getPosition());
			stmt2.setString(8, ((EmployeeOffice) emp).getDepartmentID());
			int insertResult = stmt2.executeUpdate();
			if (insertResult > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateEmployeeOffice(Employee emp) {
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"UPDATE NHANVIENHANHCHINH SET TenNhanVien = ?, GioiTinh = ?, NgaySinh = ?, DiaChi = ?, SDT = ?, LuongTheoChucDanh = ?, ChucVu = ?, MaPhongBan = ? WHERE MaNhanVien = ?");
			stmt.setString(1, emp.getName());
			stmt.setBoolean(2, emp.isGender());
			stmt.setDate(3, new java.sql.Date(emp.getBirthday().getTime()));
			stmt.setString(4, emp.getAddress());
			stmt.setString(5, emp.getPhone());
			stmt.setDouble(6, ((EmployeeOffice) emp).getSalary());
			stmt.setString(7, ((EmployeeOffice) emp).getPosition());
			stmt.setString(8, ((EmployeeOffice) emp).getDepartmentID());
			stmt.setString(9, emp.getEmployeeID());
			int insertResult = stmt.executeUpdate();
			if (insertResult > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteEmployee(String empID) {
		try {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM NHANVIENHANHCHINH WHERE MaNhanVien = ?");
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

	public String getDepNameByEmpID(String empID) {
		String depName = null;
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"select D.TenPhongBan from NhanVienHanhChinh E join PhongBan D on E.MaPhongBan = D.MaPhongBan where E.MaNhanVien = ?");
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

	public List<String> getAllName() {
		List<String> listEmp = new ArrayList<String>();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT MaNhanVien, TenNhanVien  FROM NHANVIENHANHCHINH");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				listEmp.add(rs.getString("MaNhanVien") + " - " + rs.getString("TenNhanVien"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmp;
	}

	public List<Employee> searchEmployeeByEmployeeID(String empID) {
		List<Employee> listEmp = new ArrayList<Employee>();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM NHANVIENHANHCHINH WHERE MaNhanVien LIKE ?");
			stmt.setString(1, "%" + empID + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Employee emp = new EmployeeOffice(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),
						rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"),
						rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"),
						rs.getDouble("LuongTheoChucDanh"), rs.getString("ChucVu"), rs.getString("MaPhongBan"));
				listEmp.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmp;
	}
	
	public List<Employee> searchEmployeeByName(String name) {
		List<Employee> listEmp = new ArrayList<Employee>();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM NHANVIENHANHCHINH WHERE TenNhanVien LIKE ?");
			stmt.setString(1, "%" + name + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Employee emp = new EmployeeOffice(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),
						rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"),
						rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"),
						rs.getDouble("LuongTheoChucDanh"), rs.getString("ChucVu"), rs.getString("MaPhongBan"));
				listEmp.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmp;
	}
}
