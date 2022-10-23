package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.EmployeeOffice;

public class EmployeeOfficeDAO {
	private Connection connection;

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
						rs.getFloat("LuongTheoChucDanh"), rs.getString("ChucVu"), rs.getString("MaPhongBan"));
				listEmp.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmp;
	}
}
