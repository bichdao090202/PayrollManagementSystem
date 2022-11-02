package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
			PreparedStatement stmt1 = connection.prepareStatement("SELECT MAX(MaNhanVien) FROM NHANVIENSANXUAT");
			ResultSet rs = stmt1.executeQuery();
			int maxEmpID = 0;
			if (rs.next()) {
				maxEmpID = Integer.parseInt(rs.getString(1).substring(2));
			}
			int empID = (maxEmpID + 1);
			if (empID < 10) {
				emp.setEmployeeID("NV0000" + empID);
			} else if (empID < 100) {
				emp.setEmployeeID("NV000" + empID);
			}else if (empID < 1000) {
				emp.setEmployeeID("NV00" + empID);
			}else if (empID < 10000) {
				emp.setEmployeeID("NV0" + empID);
			}else{
				emp.setEmployeeID("NV" + empID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement stmt2 = connection.prepareStatement(
					"INSERT INTO NHANVIENSANXUAT (MaNhanVien, TenNhanVien, GioiTinh, NgaySinh, DiaChi, SDT, ChuyenMon, MaTo) values(?,?,?,?,?,?,?,?)");
			stmt2.setString(1, emp.getEmployeeID());
			stmt2.setString(2, emp.getName());
			stmt2.setBoolean(3, emp.isGender());
			stmt2.setDate(4, new java.sql.Date(emp.getBirthday().getTime()));
			stmt2.setString(5, emp.getAddress());
			stmt2.setString(6, emp.getPhone());
			stmt2.setString(7, ((Worker)emp).getSpeciality());
			stmt2.setString(8, ((Worker)emp).getTeamID());
			int insertResult = stmt2.executeUpdate();
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
					"UPDATE NHANVIENSANXUAT SET TenNhanVien = ?, GioiTinh = ?, NgaySinh = ?, DiaChi = ?, SDT = ?, ChuyenMon = ?, MaTo = ? WHERE MaNhanVien = ?");
			stmt.setString(1, emp.getName());
			stmt.setBoolean(2, emp.isGender());
			stmt.setDate(3, new java.sql.Date(emp.getBirthday().getTime()));
			stmt.setString(4, emp.getAddress());
			stmt.setString(5, emp.getPhone());
			stmt.setString(6, ((Worker)emp).getSpeciality());
			stmt.setString(7, ((Worker)emp).getTeamID());
			stmt.setString(8, emp.getEmployeeID());
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
