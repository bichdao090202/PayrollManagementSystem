package model;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entity.TimesheetsOffice;
import entity.Department;
import entity.EmployeeOffice;
import entity.Worker;
import entity.Factory;
import entity.Produre;
import entity.TimesheetsFactory;
import entity.Bonus_Discipline;
import entity.TeamProducing;

public class SalaryDAO {
	private Connection con;
	private PreparedStatement prstm;
	private ResultSet rs;
	
	public SalaryDAO() {
		con = ConnectDB.getInstance().getConnection();
	}
	
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
	
	public List<EmployeeOffice> getListEmployeeAdministrative(){
		String sql = "select * from NhanVienHanhChinh";
		EmployeeOffice employeeAdministrative = new EmployeeOffice();
		List<EmployeeOffice> listEmployeeAdministrative = new ArrayList<EmployeeOffice>();
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				employeeAdministrative = new EmployeeOffice(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"), rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"), rs.getDouble("LuongTheoChucDanh"),rs.getString("ChucVu"), rs.getString("MaPhongBan"));
				listEmployeeAdministrative.add(employeeAdministrative);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listEmployeeAdministrative;
	}
	
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
	
	public List<EmployeeOffice> getListEmployeeByIdDepartment(String idDepartment){
		List<EmployeeOffice> listEmployee = new ArrayList<EmployeeOffice>();
		String sql = "select * from NhanVienHanhChinh where MaPhongBan = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idDepartment);
			rs = prstm.executeQuery();
			while(rs.next()) {
				EmployeeOffice employee = new EmployeeOffice(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"), rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"),rs.getString("TenNguoiThuHuong"), rs.getDouble("LuongTheoChucDanh"),rs.getString("ChucVu"), rs.getString("MaPhongBan"));
				listEmployee.add(employee);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listEmployee;
	}
	
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
	
	public EmployeeOffice searchEmployeeAdministrativeById(String idEmployee) {
		String sql = "select * from NhanVienHanhChinh where MaNhanVien = ?";
		EmployeeOffice eAdministrative = null;
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
		return eAdministrative;
	}
	
	public double priceProcedure(String idProcedure) {
		String sql = "select * from QuyTrinh where MaQuyTrinh = ?";
		Produre procedure = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idProcedure);
			rs = prstm.executeQuery();
			while(rs.next()) {
				procedure = new Produre(rs.getString("MaQuyTrinh"), rs.getString("TenQuyTrinh"), rs.getFloat("GiaQuyTrinh"), rs.getInt("SoThuTu"), rs.getString("MaSanPham"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return procedure.getPrice();
	}
	
	public List<TimesheetsFactory> workDayOfEmployyProductive(String idEmployee) {
		String sql = "select * from ChamCongSanXuat where MaNhanVien = ?";
		List<TimesheetsFactory> listTimeKeep = new ArrayList<TimesheetsFactory>();
		TimesheetsFactory timeKeep = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idEmployee);
			rs = prstm.executeQuery();
			while(rs.next()) {
				timeKeep = new TimesheetsFactory(rs.getString("MaChamCong"), rs.getDate("NgayChamCong"), rs.getInt("SoLuongThanhPham"), rs.getString("MaQuyTrinh"), rs.getString("MaNhanVien"));
				if(timeKeep != null) {
					listTimeKeep.add(timeKeep);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTimeKeep;
	}
	
	public List<TimesheetsOffice> hourWorkOfEmployeeAdministrative(String idEmployee){
		LocalDateTime date = LocalDateTime.now();
		String sql = "SELECT * from ChamCongHanhChinh where DATEPART(MONTH, NgayChamCong) = ? AND DATEPART(YEAR, NgayChamCong) = ? AND MaNhanVien = ?";
		List<TimesheetsOffice> listTimeKeep = new ArrayList<TimesheetsOffice>();
		TimesheetsOffice timeKeep = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, date.getMonthValue());
			prstm.setInt(2, date.getYear());
			prstm.setString(3, idEmployee);
			rs = prstm.executeQuery();
			while(rs.next()) {
				timeKeep = new TimesheetsOffice(rs.getString("MaChamCong"), rs.getDate("NgayChamCong"), rs.getTime("CheckInSang"), rs.getTime("CheckOutSang"), rs.getTime("CheckInChieu"), rs.getTime("CheckOutChieu"), rs.getString("MaNhanVien"));
				if(timeKeep != null) {
					listTimeKeep.add(timeKeep);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTimeKeep;
	}
	
	public List<Bonus_Discipline> listRDEmployeeProductive(String idEmployee) {
		String sql = "select * from KhenThuongKyLuat where MaNVSX = ?";
		List<Bonus_Discipline> listRD = new ArrayList<Bonus_Discipline>();
		Bonus_Discipline rd = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idEmployee);
			rs = prstm.executeQuery();
			while(rs.next()) {
				rd = new Bonus_Discipline(rs.getString("MaKT_KL"), rs.getString("LyDo"), rs.getDate("NgayApDung"), rs.getFloat("SoTien"), rs.getString("MaNVSX"));
				if(rd != null) {
					listRD.add(rd);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRD;
	}
	
	public List<Bonus_Discipline> listRDEmployeeAdministrative(String idEmployee) {
		String sql = "select * from KhenThuongKyLuat where MaNVHC = ?";
		List<Bonus_Discipline> listRD = new ArrayList<Bonus_Discipline>();
		Bonus_Discipline rd = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idEmployee);
			rs = prstm.executeQuery();
			while(rs.next()) {
				rd = new Bonus_Discipline(rs.getString("MaKT_KL"), rs.getString("LyDo"), rs.getDate("NgayApDung"), rs.getFloat("SoTien"), rs.getString("MaNVHC"));
				if(rd != null) {
					listRD.add(rd);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRD;
	}

}
