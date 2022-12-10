package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import entity.Department;
import entity.Employee;
import entity.EmployeeOffice;
import entity.Worker;
import entity.Factory;
import entity.Produre;
import entity.Assignment;
import entity.Bonus_Discipline;
import entity.TeamProducing;
import entity.TimesheetFactory;
import entity.TimesheetOffice;

public class SalaryDAO {
	private Connection con;
	private PreparedStatement prstm;
	private ResultSet rs;
	
	public SalaryDAO() {
		con = ConnectDB.getInstance().getConnection();
	}
	
	// Lấy tất cả nhân viên sản xuất
	public List<Worker> getListEmployeeProductive(){
		String sql = "select * from NhanVienSanXuat";
		Worker employeeProductive = new Worker();
		List<Worker> listEmployeeProductive = new ArrayList<Worker>();
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				employeeProductive = new Worker(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"), rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"), rs.getString("ChuyenMon"), rs.getString("MaTo"));
				listEmployeeProductive.add(employeeProductive);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listEmployeeProductive;
	}
	
	// lấy tất cả nhân viên hành chính
	public List<EmployeeOffice> getListEmployeeAdministrative(){
		String sql = "select * from NhanVienHanhChinh";
		Employee employeeAdministrative = new EmployeeOffice();
		List<EmployeeOffice> listEmployeeAdministrative = new ArrayList<EmployeeOffice>();
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				employeeAdministrative = new EmployeeOffice(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"), rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"), rs.getDouble("LuongTheoChucDanh"),rs.getString("ChucVu"), rs.getString("MaPhongBan"));
				listEmployeeAdministrative.add((EmployeeOffice) employeeAdministrative);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listEmployeeAdministrative;
	}
	
	// Lấy tất cả phân xưởng
	public List<Factory> getListFactory(){
		List<Factory> listFactory = new ArrayList<Factory>();
		String sql = "select * from PhanXuong";
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				Factory factory = new Factory(rs.getString("MaPhanXuong"), rs.getString("TenPhanXuong"), rs.getString("MaQuanDoc"));
				listFactory.add(factory);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listFactory;
	}
	
	// Lấy tất cả phòng ban
	public List<Department> getListDepartment(){
		List<Department> listDepartment = new ArrayList<Department>();
		String sql = "select * from PhongBan";
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				Department department = new Department(rs.getString("MaPhongBan"), rs.getString("TenPhongBan"), rs.getString("MaTruongPhong"));
				listDepartment.add(department);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listDepartment;
	}
	
	// Lấy danh sách tổ theo mã phân xưởng
	public List<TeamProducing> getListTeamByIdFactory(String idFactory){
		List<TeamProducing> listTeam = new ArrayList<TeamProducing>();
		String sql = "select * from ToSanXuat where MaPhanXuong = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idFactory);
			rs = prstm.executeQuery();
			while(rs.next()) {
				TeamProducing px = new TeamProducing(rs.getString("MaTo"), rs.getString("TenTo"), rs.getString("MaToTruong"), rs.getString("MaPhanXuong"));
				listTeam.add(px);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listTeam;
	}
	
	// Lấy danh sách nhân viên theo mã tổ
	public List<Worker> getListEmployeeByIdTeam(String idTeam){
		List<Worker> listEmployee = new ArrayList<Worker>();
		String sql = "select * from NhanVienSanXuat where MaTo = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idTeam);
			rs = prstm.executeQuery();
			while(rs.next()) {
				Worker employee = new Worker(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"), rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"),rs.getString("TenNguoiThuHuong"), rs.getString("ChuyenMon"), rs.getString("MaTo"));
				listEmployee.add(employee);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listEmployee;
	}
	
	// Lấy danh sách nhân viên theo mã phòng ban
	public List<EmployeeOffice> getListEmployeeByIdDepartment(String idDepartment){
		List<EmployeeOffice> listEmployee = new ArrayList<EmployeeOffice>();
		String sql = "select * from NhanVienHanhChinh where MaPhongBan = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idDepartment);
			rs = prstm.executeQuery();
			while(rs.next()) {
				Employee employee = new EmployeeOffice(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"), rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"),rs.getString("TenNguoiThuHuong"), rs.getDouble("LuongTheoChucDanh"),rs.getString("ChucVu"), rs.getString("MaPhongBan"));
				listEmployee.add((EmployeeOffice) employee);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listEmployee;
	}
	
	// Tìm kiếm nhân viên sản xuất theo mã nhân viên
	public Worker searchEmployeeProductiveById(String idEmployee) {
		String sql = "select * from NhanVienSanXuat where MaNhanVien = ?";
		Worker eProductive = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idEmployee);
			rs = prstm.executeQuery();
			while(rs.next()) {
				eProductive = new Worker(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"), rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"), rs.getString("ChuyenMon"), rs.getString("MaTo"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return eProductive;
	}
	
	// Tìm kiếm nhân viên hành chính theo mã nhân viên
	public EmployeeOffice searchEmployeeAdministrativeById(String idEmployee) {
		String sql = "select * from NhanVienHanhChinh where MaNhanVien = ?";
		Employee eAdministrative = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idEmployee);
			rs = prstm.executeQuery();
			while(rs.next()) {
				eAdministrative = new EmployeeOffice(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"), rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"), rs.getDouble("LuongTheoChucDanh"),rs.getString("ChucVu"), rs.getString("MaPhongBan"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return (EmployeeOffice) eAdministrative;
	}
	
	// Tìm kiếm chấm công sản xuất theo mã phân công
	public TimesheetFactory searchTimeSheetsFactoryById(int idAssignment) {
		String sql = "select * from ChamCongSanXuat where MaPhanCong = ?";
		TimesheetFactory time = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, idAssignment);
			rs = prstm.executeQuery();
			while(rs.next()) {
				time = new TimesheetFactory(rs.getInt("MaChamCong"), rs.getDate("NgayChamCong"), rs.getInt("SoLuongThanhPham"), rs.getInt("MaPhanCong"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return time;
	}
	
	// Tìm kiếm phân công theo mã phân công
	public Assignment searchAssignmentById(int assignmentID) {
		String sql = "select * from PhanCong where MaPhanCong = ?";
		Assignment assignment = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, assignmentID);
			rs = prstm.executeQuery();
			while(rs.next()) {
				assignment = new Assignment(rs.getInt("MaPhanCong"), rs.getString("MaQuyTrinh"), rs.getString("MaNhanVien"), rs.getDate("NgayThamGia").toLocalDate(), rs.getInt("MaHopDong"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return assignment;
	}
	
	// Lấy chi phí làm việc của quy trình
	public double getPriceProcedure(String idProcedure) {
		String sql = "select * from QuyTrinh where MaQuyTrinh = ?";
		Produre procedure = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idProcedure);
			rs = prstm.executeQuery();
			while(rs.next()) {
				procedure = new Produre(rs.getString("MaQuyTrinh"), rs.getString("TenQuyTrinh"), rs.getDouble("GiaQuyTrinh"),rs.getInt("ThuTuSanXuat"), rs.getString("MaSanPham"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return procedure.getPrice();
	}
	
	// Lấy danh sách phân công của nhân viên theo mã nhân viên và tháng, năm
	public List<Assignment> getListAssignmentOfEmployee(String idEmployee, int month, int year){
		String sql = "select * from PhanCong where DATEPART(MONTH, NgayThamGia) = ? AND DATEPART(YEAR, NgayThamGia) = ? AND MaNhanVien = ?";
		List<Assignment> listAssignment = new ArrayList<Assignment>();
		Assignment assignment = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, month);
			prstm.setInt(2, year);
			prstm.setString(3, idEmployee);
			rs = prstm.executeQuery();
			while(rs.next()) {
				assignment = new Assignment(rs.getInt("MaPhanCong"), rs.getString("MaQuyTrinh"), rs.getString("MaNhanVien"), rs.getDate("NgayThamGia").toLocalDate(), rs.getInt("MaHopDong"));
				if(assignment != null) {
					listAssignment.add(assignment);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listAssignment;
	}
	
	// Lấy số ngày làm việc của nhân viên sản xuất
	public List<TimesheetFactory> getWorkDayOfEmployyProductive(String idEmployee, int month, int year) {
		List<Assignment> listAssignment = getListAssignmentOfEmployee(idEmployee, month, year);
		List<TimesheetFactory> listTime = new ArrayList<TimesheetFactory>();
		
		for(Assignment a : listAssignment) {
			listTime.add(searchTimeSheetsFactoryById(a.getAssignmentID()));
		}
		return listTime;
	}
	
	// Tính tổng lương của nhân viên
	public double calculateTotalSalaryOfE(String idEmployee, int month, int year) {
		double totalSalary = 0;
		List<Assignment> listAssignment = getListAssignmentOfEmployee(idEmployee, month, year);
		List<TimesheetFactory> listTime = new ArrayList<TimesheetFactory>();
		ProductDAO productDao = new ProductDAO();
		for(Assignment a : listAssignment) {
			listTime.add(searchTimeSheetsFactoryById(a.getAssignmentID()));
		}
		for(TimesheetFactory time : listTime) {
			Assignment assignment = searchAssignmentById(time.getAssignmentID());
			Produre produre = productDao.searchProcedureByIdProcedure(assignment.getProdureID());
			totalSalary += time.getQuantity() * produre.getPrice();
		}
		return totalSalary;
	}
	
	// lấy số ngày làm việc của nhân viên hành chính
	public List<TimesheetOffice> getNumberWorkOfEmployeeAdministrative(String idEmployee, int month, int year){
		String sql = "SELECT * from ChamCongHanhChinh where DATEPART(MONTH, NgayChamCong) = ? AND DATEPART(YEAR, NgayChamCong) = ? AND MaNhanVien = ?";
		List<TimesheetOffice> listTimeKeep = new ArrayList<TimesheetOffice>();
		TimesheetOffice timeKeep = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, month);
			prstm.setInt(2, year);
			prstm.setString(3, idEmployee);
			rs = prstm.executeQuery();
			while(rs.next()) {
				timeKeep = new TimesheetOffice(rs.getDate("NgayChamCong"), rs.getTime("CheckInSang"), rs.getTime("CheckOutSang"), rs.getTime("CheckInChieu"), rs.getTime("CheckOutChieu"), rs.getString("MaNhanVien"));
				if(timeKeep != null) {
					listTimeKeep.add(timeKeep);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listTimeKeep;
	}
	
	// Tìm kiếm danh sách nhân viên có mã gần giống với mã được cho
	public List<Employee> searchListEmployee(String employeeID){
		List<Employee> listEmployee = new ArrayList<Employee>();
		List<Employee> listWorker = searchListWorker(employeeID);
		List<Employee> listEmployeeOffice = searchListEmployeeOffice(employeeID);
		boolean isInserListworker = listEmployee.addAll(listWorker);
		boolean isInserListEmployeeOffice = listEmployee.addAll(listEmployeeOffice);
		if((isInserListworker && isInserListEmployeeOffice) || (isInserListworker || isInserListEmployeeOffice)) {
			return listEmployee;
		}
		return null;
	}
	
	public List<Employee> searchListWorker(String employeeID){
		String sql = "select * from NhanVienSanXuat where MaNhanVien like '%" + employeeID + "%'";
		Employee worker = null;
		List<Employee> listEmployee = new ArrayList<Employee>();
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				worker = new Worker(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"), rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"),rs.getString("TenNguoiThuHuong"), rs.getString("ChuyenMon"), rs.getString("MaTo"));
				if(worker != null) {
					listEmployee.add(worker);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listEmployee;
	}
	
	public List<Employee> searchListEmployeeOffice(String employeeID){
		String sql = "select * from NhanVienHanhChinh where MaNhanVien like '%" + employeeID + "%'";
		Employee employeeOffice = null;
		List<Employee> listEmployee = new ArrayList<Employee>();
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				employeeOffice = new EmployeeOffice(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"), rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"), rs.getDouble("LuongTheoChucDanh"),rs.getString("ChucVu"), rs.getString("MaPhongBan"));
				if(employeeOffice != null) {
					listEmployee.add(employeeOffice);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listEmployee;
	}
}
