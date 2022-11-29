package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Employee;
import entity.EmployeeOffice;
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
				Employee emp = new Worker(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"),
						rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"), rs.getString("ChuyenMon"), rs.getString("MaTo"), rs.getString("ChucVu"));
				listEmp.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmp;
	}
	
	public List<Worker> getListWorker() {
		List<Worker> list = new ArrayList<Worker>();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from NhanVienSanXuat");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Worker emp = new Worker(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),
						rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"),
						rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"),
						rs.getString("ChuyenMon"), rs.getString("MaTo"));
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String getWorkerNameByID(String idWorker) {
		String name = null;
		try {
			PreparedStatement stmt = connection
					.prepareStatement("select TenNhanVien from NhanVienSanXuat where MaNhanVien = ?");
			stmt.setString(1, idWorker);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next())
				return "";
			else
				name = rs.getString("TenNhanVien");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public boolean addWorker(Employee emp) {
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO NHANVIENSANXUAT (TenNganHang , TenNhanVien, GioiTinh, NgaySinh, DiaChi, SDT, ChuyenMon, MaTo, SoTaiKhoan, TenNguoiThuHuong, ChucVu) values(?,?,?,?,?,?,?,?,?,?, ?)");
			stmt.setString(1, emp.getBankName());
			stmt.setString(2, emp.getName());
			stmt.setBoolean(3, emp.isGender());
			stmt.setDate(4, new java.sql.Date(emp.getBirthday().getTime()));
			stmt.setString(5, emp.getAddress());
			stmt.setString(6, emp.getPhone());
			stmt.setString(7, ((Worker)emp).getSpeciality());
			stmt.setString(8, ((Worker)emp).getTeamID());
			stmt.setString(9, emp.getAccountNumber());
			stmt.setString(10, emp.getBeneficiany());
			stmt.setString(11, ((Worker)emp).getPosition());
			int insertResult = stmt.executeUpdate();
			if (insertResult > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateWorker(Employee emp) {
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"UPDATE NHANVIENSANXUAT SET TenNhanVien = ?, GioiTinh = ?, NgaySinh = ?, DiaChi = ?, SDT = ?, ChuyenMon = ?, MaTo = ?, TenNganHang = ?, SoTaiKhoan = ?, TenNguoiThuHuong = ?, ChucVu = ? WHERE MaNhanVien = ?");
			stmt.setString(1, emp.getName());
			stmt.setBoolean(2, emp.isGender());
			stmt.setDate(3, new java.sql.Date(emp.getBirthday().getTime()));
			stmt.setString(4, emp.getAddress());
			stmt.setString(5, emp.getPhone());
			stmt.setString(6, ((Worker)emp).getSpeciality());
			stmt.setString(7, ((Worker)emp).getTeamID());
			stmt.setString(8, emp.getBankName());
			stmt.setString(9, emp.getAccountNumber());
			stmt.setString(10, emp.getBeneficiany());
			stmt.setString(11, ((Worker)emp).getPosition());
			stmt.setString(12, emp.getEmployeeID());
			int insertResult = stmt.executeUpdate();
			if (insertResult > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
						rs.getString("ChuyenMon"), rs.getString("MaTo"), rs.getString("ChucVu"));
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
	
	public Worker getWorkerByID(String empID) {		
		Worker emp = null;
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
	
	public List<Employee> searchEmployeeByEmployeeID(String empID) {
		List<Employee> listEmp = new ArrayList<Employee>();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM NHANVIENSANXUAT WHERE MaNhanVien LIKE ?");
			stmt.setString(1, "%" + empID + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Employee emp = new Worker(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),
						rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"),
						rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"),
						rs.getString("ChuyenMon"), rs.getString("MaTo"), rs.getString("ChucVu"));
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
					.prepareStatement("SELECT * FROM NHANVIENSANXUAT WHERE TenNhanVien LIKE ?");
			stmt.setString(1, "%" + name + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Employee emp = new Worker(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),
						rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"),
						rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"),
						rs.getString("ChuyenMon"), rs.getString("MaTo"), rs.getString("ChucVu"));
				listEmp.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmp;
	}
}
