package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import entity.TimesheetsOffice;

public class TimesheetsDAO {
	private Connection connection;

	public TimesheetsDAO() {
		connection = ConnectDB.getInstance().getConnection();
	}

	public List<TimesheetsOffice> getAllTimesheetsOffices() {
		List<TimesheetsOffice> listTimesheet = new ArrayList<TimesheetsOffice>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM VIEW_CHAMCONGHANHCHINH");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				TimesheetsOffice timesheet = new TimesheetsOffice(
						rs.getDate("NgayChamCong"), rs.getTime("CheckInSang"), rs.getTime("CheckOutSang"),
						rs.getTime("CheckInChieu"), rs.getTime("CheckOutChieu"), rs.getString("NhanVien"));
				listTimesheet.add(timesheet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTimesheet;
	}
	
	public boolean addTimesheetsOffice(TimesheetsOffice timesheetsOffice) {
		try {
            PreparedStatement stmt = connection
                    .prepareStatement("INSERT INTO CHAMCONGHANHCHINH(NgayChamCong, CheckInSang, CheckOutSang, CheckInChieu, CheckOutChieu, MaNhanVien) values(?,?,?,?,?,?)");
            stmt.setDate(1, new Date(timesheetsOffice.getDate().getTime()));
            stmt.setTime(2, new Time(timesheetsOffice.getCheckInAM().getTime()));
            stmt.setTime(3, new Time(timesheetsOffice.getCheckOutAM().getTime()));
            stmt.setTime(4, new Time(timesheetsOffice.getCheckInPM().getTime()));
            stmt.setTime(5, new Time(timesheetsOffice.getCheckOutPM().getTime()));
            stmt.setString(6, timesheetsOffice.getEmployeeID());
            int insertResult = stmt.executeUpdate();
            if (insertResult > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public boolean updateTimesheetsOffice(TimesheetsOffice timesheetsOffice) {
		try {
            PreparedStatement stmt = connection
                    .prepareStatement("UPDATE CHAMCONGHANHCHINH SET CheckInSang = ?, CheckOutSang = ?, CheckInChieu = ?, CheckOutChieu = ? WHERE MaNhanVien = ? AND NgayChamCong = ?");
            stmt.setTime(1, new Time(timesheetsOffice.getCheckInAM().getTime()));
            stmt.setTime(2, new Time(timesheetsOffice.getCheckOutAM().getTime()));
            stmt.setTime(3, new Time(timesheetsOffice.getCheckInPM().getTime()));
            stmt.setTime(4, new Time(timesheetsOffice.getCheckOutPM().getTime()));
            stmt.setString(5, timesheetsOffice.getEmployeeID());
            stmt.setDate(6, new Date(timesheetsOffice.getDate().getTime()));
            int insertResult = stmt.executeUpdate();
            if (insertResult > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
}
