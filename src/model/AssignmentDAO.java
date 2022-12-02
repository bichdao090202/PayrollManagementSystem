package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Assignment;

public class AssignmentDAO {
	private Connection connection;

	public AssignmentDAO() {
		connection = ConnectDB.getInstance().getConnection();
	}

	public List<Assignment> getAllAssignments(String teamID) {
		List<Assignment> Assignment = new ArrayList<Assignment>();
		try {
			PreparedStatement stmt = null;
			if (teamID.equals("admin"))
				stmt = connection.prepareStatement("select * from PhanCong WHERE EXISTS (select * from NhanVienSanXuat where NhanVienSanXuat.MaNhanVien = PhanCong.MaNhanVien) and EXISTS (select * from  SanPham sp join ChiTietSanXuat ctsx on sp.MaSanPham = ctsx.MaSanPham where ctsx.TinhTrang = N'Sản Xuất' and ctsx.MaSanPham = SUBSTRING(PhanCong.MaQuyTrinh,1,5)) and not EXISTS (select * from PhanXuong where PhanXuong.MaQuanDoc = PhanCong.MaNhanVien) ORDER BY NgayThamGia ASC");
			else {
				stmt = connection.prepareStatement("select * from PhanCong WHERE EXISTS (select * from NhanVienSanXuat where MaTo = ? and NhanVienSanXuat.MaNhanVien = PhanCong.MaNhanVien) and EXISTS (select * from  SanPham sp join ChiTietSanXuat ctsx on sp.MaSanPham = ctsx.MaSanPham where ctsx.TinhTrang = N'Sản Xuất' and ctsx.MaSanPham = SUBSTRING(PhanCong.MaQuyTrinh,1,5)) and not EXISTS (select * from PhanXuong where PhanXuong.MaQuanDoc = PhanCong.MaNhanVien) ORDER BY NgayThamGia ASC");
				stmt.setString(1, teamID);
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Assignment ass = new Assignment(rs.getString("MaPhanCong"), rs.getString("MaQuyTrinh"),
						rs.getString("MaNhanVien"), rs.getTimestamp("NgayThamGia").toLocalDateTime().toLocalDate());
				Assignment.add(ass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Assignment;
	}

	public boolean createAssignment(Assignment assignment) {
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO PhanCong(MaPhanCong,MaQuyTrinh,MaNhanVien,NgayThamGia) values(?,?,?,?)");
			stmt.setString(1, assignment.getAssignmentID());
			stmt.setString(2, assignment.getProdureID());
			stmt.setString(3, assignment.getWorkerID());
			stmt.setString(4, assignment.getDate().toString());
			int insertResult = stmt.executeUpdate();
			if (insertResult > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public int getNewAssignmentID() {
		int id = 0;
		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT TOP 1 MaPhanCong FROM PhanCong ORDER BY cast(MaPhanCong as int) DESC");
			ResultSet rs = stmt.executeQuery();
			if (!rs.next())
				return 1;
			else
				id = rs.getInt("MaPhanCong");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		id++;
		return id;
	}

	public boolean checkExistDateOfWorker(String idWorker, String stringDate) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("select * from PhanCong where MaNhanVien = ? and NgayThamGia = ?");
			stmt.setString(1, idWorker);
			stmt.setString(2, stringDate);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteAssignment(String assignmentID) {
		try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Phancong WHERE maphancong = ?");
            stmt.setString(1, assignmentID);
            int deleteResult = stmt.executeUpdate();
            if (deleteResult > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	public Assignment getAssignmentByID(String assignmentID) {
		Assignment assignment = new Assignment();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from PhanCong where maphancong = ?");
			stmt.setString(1, assignmentID);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next())
				return null;
			else {
				assignment = new Assignment(rs.getString("MaPhanCong"), rs.getString("MaQuyTrinh"),
						rs.getString("MaNhanVien"), rs.getTimestamp("NgayThamGia").toLocalDateTime().toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assignment;
	}
	
	public boolean updateAssignment(Assignment assignment) {
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"update PhanCong set MaNhanVien = ?, MaQuyTrinh = ?, NgayThamGia = ? where MaPhanCong = ?");
			stmt.setString(1, assignment.getWorkerID());
			stmt.setString(2, assignment.getProdureID());
			stmt.setString(3, assignment.getDate().toString());
			stmt.setString(4, assignment.getAssignmentID());
			int insertResult = stmt.executeUpdate();
			if (insertResult > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public int getQuanProduct (String productID) {
		int num = 0;
		try {
			PreparedStatement stmt = connection
					.prepareStatement("select ctsx.SoLuongSanXuat from ChiTietSanXuat ctsx join SanPham sp on ctsx.MaSanPham = sp.MaSanPham where ctsx.TinhTrang = N'Sản Xuất' and sp.MaSanPham = ?");
			stmt.setString(1, productID);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next())
				return 0;
			else
				num = rs.getInt("SoLuongSanXuat");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public int getQuanDoneProduct (String productID) {
		int num = 0;
		try {
			PreparedStatement stmt = connection
					.prepareStatement("select ctsx.SoLuongHoanThanh from ChiTietSanXuat ctsx join SanPham sp on ctsx.MaSanPham = sp.MaSanPham where ctsx.TinhTrang = N'Sản Xuất' and sp.MaSanPham = ?");
			stmt.setString(1, productID);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next())
				return 1;
			else
				num = rs.getInt("SoLuongHoanThanh");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public Boolean checkExistsAssignmenDetail (String assignmentID) {
		Integer num=0;
		try {
			PreparedStatement stmt = connection.prepareStatement("select count(PhanCong.MaPhanCong) as number from ChamCongSanXuat join PhanCong on ChamCongSanXuat.MaPhanCong = PhanCong.MaPhanCong WHERE PhanCong.MaPhanCong =?");
			stmt.setString(1, assignmentID);
			ResultSet rs = stmt.executeQuery();
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
	

}
