package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;
import entity.TimesheetsOffice;
import entity.Worker;

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
				TimesheetsOffice timesheet = new TimesheetsOffice(rs.getString("MaChamCong"),
						rs.getDate("NgayChamCong"), rs.getTime("CheckInSang"), rs.getTime("CheckOutSang"),
						rs.getTime("CheckInChieu"), rs.getTime("CheckOutChieu"), rs.getString("NhanVien"));
				listTimesheet.add(timesheet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTimesheet;
	}
}
