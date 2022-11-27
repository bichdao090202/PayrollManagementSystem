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

	public List<Assignment> getAllAssignments() {
		List<Assignment> Assignment = new ArrayList<Assignment>();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from PhanCong");
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

}
