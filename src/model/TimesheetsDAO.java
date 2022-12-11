package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import entity.TimesheetOffice;

public class TimesheetsDAO {
	private Connection connection;

	public TimesheetsDAO() {
		connection = ConnectDB.getInstance().getConnection();
	}

	public List<TimesheetOffice> getAllTimesheetOffices() {
		List<TimesheetOffice> listTimesheet = new ArrayList<TimesheetOffice>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM VIEW_CHAMCONGHANHCHINH");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				TimesheetOffice timesheet = new TimesheetOffice(
						rs.getDate("NgayChamCong"), rs.getTime("CheckInSang"), rs.getTime("CheckOutSang"),
						rs.getTime("CheckInChieu"), rs.getTime("CheckOutChieu"), rs.getString("NhanVien"));
				listTimesheet.add(timesheet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTimesheet;
	}
	
	public boolean addTimesheetOffice(TimesheetOffice TimesheetOffice) {
		try {
			PreparedStatement stmt = null;
			if (TimesheetOffice.getCheckInAM() == null) {
				stmt = connection
	                    .prepareStatement("INSERT INTO CHAMCONGHANHCHINH(NgayChamCong, CheckInChieu, CheckOutChieu, MaNhanVien) values(?,?,?,?)");
	            stmt.setDate(1, new Date(TimesheetOffice.getDate().getTime()));
	            stmt.setTime(2, new Time(TimesheetOffice.getCheckInPM().getTime()));
	            stmt.setTime(3, new Time(TimesheetOffice.getCheckOutPM().getTime()));
	            stmt.setString(4, TimesheetOffice.getEmployeeID());
			}else if (TimesheetOffice.getCheckInPM() == null) {
				stmt = connection
	                    .prepareStatement("INSERT INTO CHAMCONGHANHCHINH(NgayChamCong, CheckInSang, CheckOutSang, MaNhanVien) values(?,?,?,?)");
	            stmt.setDate(1, new Date(TimesheetOffice.getDate().getTime()));
	            stmt.setTime(2, new Time(TimesheetOffice.getCheckInAM().getTime()));
	            stmt.setTime(3, new Time(TimesheetOffice.getCheckOutAM().getTime()));
	            stmt.setString(4, TimesheetOffice.getEmployeeID());
			}else {
				stmt = connection
	                    .prepareStatement("INSERT INTO CHAMCONGHANHCHINH(NgayChamCong, CheckInSang, CheckOutSang, CheckInChieu, CheckOutChieu, MaNhanVien) values(?,?,?,?,?,?)");
	            stmt.setDate(1, new Date(TimesheetOffice.getDate().getTime()));
	            stmt.setTime(2, new Time(TimesheetOffice.getCheckInAM().getTime()));
	            stmt.setTime(3, new Time(TimesheetOffice.getCheckOutAM().getTime()));
	            stmt.setTime(4, new Time(TimesheetOffice.getCheckInPM().getTime()));
	            stmt.setTime(5, new Time(TimesheetOffice.getCheckOutPM().getTime()));
	            stmt.setString(6, TimesheetOffice.getEmployeeID());
			}
            int insertResult = stmt.executeUpdate();
            if (insertResult > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public List<TimesheetOffice> searchByEmp(String emp) {
		List<TimesheetOffice> listTimesheet = new ArrayList<TimesheetOffice>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM VIEW_CHAMCONGHANHCHINH WHERE NhanVien = ?");
			stmt.setString(1, emp);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				TimesheetOffice timesheet = new TimesheetOffice(
						rs.getDate("NgayChamCong"), rs.getTime("CheckInSang"), rs.getTime("CheckOutSang"),
						rs.getTime("CheckInChieu"), rs.getTime("CheckOutChieu"), rs.getString("NhanVien"));
				listTimesheet.add(timesheet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTimesheet;
	}
	
	public List<TimesheetOffice> searchByDateAndEmp(String emp, java.util.Date date) {
		List<TimesheetOffice> listTimesheet = new ArrayList<TimesheetOffice>();
		if (emp.equals("Tất cả nhân viên")) {
			try {
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM VIEW_CHAMCONGHANHCHINH WHERE NgayChamCong = ?");
				stmt.setDate(1, new java.sql.Date(date.getTime()));
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					TimesheetOffice timesheet = new TimesheetOffice(
							rs.getDate("NgayChamCong"), rs.getTime("CheckInSang"), rs.getTime("CheckOutSang"),
							rs.getTime("CheckInChieu"), rs.getTime("CheckOutChieu"), rs.getString("NhanVien"));
					listTimesheet.add(timesheet);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM VIEW_CHAMCONGHANHCHINH WHERE NhanVien = ? AND NgayChamCong = ?");
				stmt.setString(1, emp);
				stmt.setDate(2, new java.sql.Date(date.getTime()));
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					TimesheetOffice timesheet = new TimesheetOffice(
							rs.getDate("NgayChamCong"), rs.getTime("CheckInSang"), rs.getTime("CheckOutSang"),
							rs.getTime("CheckInChieu"), rs.getTime("CheckOutChieu"), rs.getString("NhanVien"));
					listTimesheet.add(timesheet);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listTimesheet;
	}
	
	public boolean updateTimesheetOffice(TimesheetOffice TimesheetOffice) {
		try {
            PreparedStatement stmt = connection
                    .prepareStatement("UPDATE CHAMCONGHANHCHINH SET CheckInSang = ?, CheckOutSang = ?, CheckInChieu = ?, CheckOutChieu = ? WHERE MaNhanVien = ? AND NgayChamCong = ?");
            stmt.setTime(1, new Time(TimesheetOffice.getCheckInAM().getTime()));
            stmt.setTime(2, new Time(TimesheetOffice.getCheckOutAM().getTime()));
            stmt.setTime(3, new Time(TimesheetOffice.getCheckInPM().getTime()));
            stmt.setTime(4, new Time(TimesheetOffice.getCheckOutPM().getTime()));
            stmt.setString(5, TimesheetOffice.getEmployeeID());
            stmt.setDate(6, new Date(TimesheetOffice.getDate().getTime()));
            int insertResult = stmt.executeUpdate();
            if (insertResult > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public List<String> getAllNameEmp(){
		List<String> lstName = new ArrayList<String>();
		lstName.add("Tất cả nhân viên");
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT NhanVien FROM VIEW_CHAMCONGHANHCHINH GROUP BY NhanVien");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				lstName.add(rs.getString("NhanVien"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstName;
	}
}
