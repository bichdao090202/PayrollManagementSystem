package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.Employee;
import entity.EmployeeOffice;
import entity.Worker;


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
			PreparedStatement stmt = null;
			if (id.contains("NVHC"))
				stmt = connection.prepareStatement("insert into TaiKhoan values (?,'V+FJJVVD6UnIU7+iVlRvA9tvnDrZ8rt8lWgNjbVqWtM=','9wlad4hsa9l9fflj99f4s9lndll28s',null,?)");
			else 
				stmt = connection.prepareStatement("insert into TaiKhoan values (?,'V+FJJVVD6UnIU7+iVlRvA9tvnDrZ8rt8lWgNjbVqWtM=','9wlad4hsa9l9fflj99f4s9lndll28s',?,null)");
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
			PreparedStatement stmt = connection.prepareStatement("select number = count(MaNhanVien) from NhanVienHanhChinh E join TaiKhoan A on E.MaNhanVien = A.MaNVHC where E.MaNhanVien = ?");
			stmt.setString(1, empID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("number");
			}
			if (num>0)
				return true;
			stmt = connection.prepareStatement("select number = count(MaNhanVien) from NhanVienSanXuat E join TaiKhoan A on E.MaNhanVien = A.MaNVSX where E.MaNhanVien = ?");
			stmt.setString(1, empID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("number");
			}
			if (num>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Worker> getListAccountWorker() {
		List<Worker> list = new ArrayList<Worker>();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from NhanVienSanXuat WHERE EXISTS (SELECT * FROM PhanXuong WHERE NhanVienSanXuat.MaNhanVien = PhanXuong.MaQuanDoc) or EXISTS (SELECT * FROM ToSanXuat WHERE NhanVienSanXuat.MaNhanVien = ToSanXuat.MaToTruong) ORDER BY MaTo ASC");
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
	
	public List<Employee> getListAccountOffice() {
		List<Employee> listEmp = new ArrayList<Employee>();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from NHANVIENHANHCHINH o join PhongBan d on o.MaPhongBan = d.MaPhongBan where d.TenPhongBan = N'Phòng nhân sự' or d.TenPhongBan = N'Phòng tài chính kế toán' ORDER BY d.TenPhongBan, o.ChucVu DESC");
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
	
	public String getPositionWorker (String workerID) {
		Integer num=0;
		try {
			PreparedStatement stmt = connection.prepareStatement("select number = count(MaNhanVien) from NhanVienSanXuat w join PhanXuong f on w.MaNhanVien = f.MaQuanDoc where w.MaNhanVien = ?");
			stmt.setString(1, workerID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("number");
			}
			return num>0?"Quản đốc phân xưởng":"Tổ trưởng";
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
}
