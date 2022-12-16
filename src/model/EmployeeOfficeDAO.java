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

	public EmployeeOfficeDAO() {
		connection = ConnectDB.getInstance().getConnection();
	}

	public boolean checkDelete(String empID) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT TOP 1 * FROM CHAMCONGHANHCHINH WHERE MaNhanVien = ?");
			stmt.setString(1, empID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean checkDelete_Account(String empID) {
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT TOP 1 * FROM TaiKhoan WHERE TenDangNhap = ?");
			stmt.setString(1, empID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public List<Employee> getAllEmployeeOffice() {
		List<Employee> listEmp = new ArrayList<Employee>();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM NHANVIENHANHCHINH WHERE MaNhanVien != 'NVHC00000'");
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

	public boolean addEmployee(Employee emp) {
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO NHANVIENHANHCHINH (TenNhanVien, GioiTinh, NgaySinh, DiaChi, SDT, LuongTheoChucDanh, ChucVu, MaPhongBan, TenNganHang, SoTaiKhoan, TenNguoiThuHuong) values(?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, emp.getName());
			stmt.setBoolean(2, emp.isGender());
			stmt.setDate(3, new java.sql.Date(emp.getBirthday().getTime()));
			stmt.setString(4, emp.getAddress());
			stmt.setString(5, emp.getPhone());
			stmt.setDouble(6, ((EmployeeOffice) emp).getSalary());
			stmt.setString(7, emp.getPosition());
			stmt.setString(8, ((EmployeeOffice) emp).getDepartmentID());
			stmt.setString(9, emp.getBankName());
			stmt.setString(10, emp.getAccountNumber());
			stmt.setString(11, emp.getBeneficiany());
			int insertResult = stmt.executeUpdate();
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
					"UPDATE NHANVIENHANHCHINH SET TenNhanVien = ?, GioiTinh = ?, NgaySinh = ?, DiaChi = ?, SDT = ?, LuongTheoChucDanh = ?, ChucVu = ?, MaPhongBan = ?, TenNganHang = ?, SoTaiKhoan = ?, TenNguoiThuHuong = ? WHERE MaNhanVien = ?");
			stmt.setString(1, emp.getName());
			stmt.setBoolean(2, emp.isGender());
			stmt.setDate(3, new java.sql.Date(emp.getBirthday().getTime()));
			stmt.setString(4, emp.getAddress());
			stmt.setString(5, emp.getPhone());
			stmt.setDouble(6, ((EmployeeOffice) emp).getSalary());
			stmt.setString(7, ((EmployeeOffice) emp).getPosition());
			stmt.setString(8, ((EmployeeOffice) emp).getDepartmentID());
			stmt.setString(12, emp.getEmployeeID());
			stmt.setString(9, emp.getBankName());
			stmt.setString(10, emp.getAccountNumber());
			stmt.setString(11, emp.getBeneficiany());
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
			if (result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
			PreparedStatement stmt = connection.prepareStatement(
					"SELECT MaNhanVien, TenNhanVien  FROM NHANVIENHANHCHINH WHERE MaNhanVien != 'NVHC00000'");
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
			PreparedStatement stmt = connection.prepareStatement(
					"SELECT * FROM NHANVIENHANHCHINH WHERE MaNhanVien LIKE ? AND MaNhanVien != 'NVHC00000'");
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
			PreparedStatement stmt = connection.prepareStatement(
					"SELECT * FROM NHANVIENHANHCHINH WHERE TenNhanVien LIKE ?  AND MaNhanVien != 'NVHC00000'");
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
