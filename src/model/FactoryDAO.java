package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entity.Worker;
import entity.Factory;
import entity.Product;
import entity.Produre;
import entity.TeamProducing;

public class FactoryDAO {
	private Connection con;
	private PreparedStatement prstm;
	private ResultSet rs;
	
	public FactoryDAO() {
		con = ConnectDB.getInstance().getConnection();
	}
	
	// Lấy tất cả phân xưởng hiện có
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
	
	// Lấy tất cả tổ hiện có
	public List<TeamProducing> getListTeam(){
		List<TeamProducing> listTeam = new ArrayList<TeamProducing>();
		String sql = "select * from ToSanXuat";
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				TeamProducing team = new TeamProducing(rs.getString("MaTo"), rs.getString("TenTo"), rs.getString("MaToTruong"), rs.getString("MaPhanXuong"));
				listTeam.add(team);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listTeam;
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
				TeamProducing team = new TeamProducing(rs.getString("MaTo"), rs.getString("TenTo"), rs.getString("MaToTruong"), rs.getString("MaPhanXuong"));
				listTeam.add(team);
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
	
	// Thêm phân xưởng vào server
	public boolean insertFactory(Factory factory) {
		String sql = "insert into PhanXuong values(?,?,?)";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, factory.getFactoryID());
			prstm.setString(2, factory.getName());
			prstm.setString(3, factory.getHeadForemanID());
			int n = prstm.executeUpdate();
			if(n > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	// Thêm danh sách tổ vào server
	public boolean insertListTeam(List<TeamProducing> listTeam) {
		String sql = "insert into ToSanXuat values(?,?,?,?)";
		String announce = "";
		try {
			for(TeamProducing team : listTeam) {
				prstm = con.prepareStatement(sql);
				prstm.setString(1, team.getTeamID());
				prstm.setString(2, team.getName());
				prstm.setString(3, team.getLeaderID());
				prstm.setString(4, team.getFactoryID());
				int n = prstm.executeUpdate();
				if(!(n > 0)) {
					announce += "false";
					break;
				}
			}
			if(announce.isEmpty()) {
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	// thêm tổ vào server
	public boolean insertTeam(TeamProducing team) {
		String sql = "insert into ToSanXuat values(?,?,?,?)";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, team.getTeamID());
			prstm.setString(2, team.getName());
			prstm.setString(3, team.getLeaderID());
			prstm.setString(4, team.getFactoryID());
			int n = prstm.executeUpdate();
			if(n > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	// Xóa phân xưởng hiện có trên server
	public boolean deleteFactory(String idFactory) {
		String sql = "delete from PhanXuong where MaPhanXuong = ?";
		List<TeamProducing> listTeam = getListTeamByIdFactory(idFactory);
		if(listTeam.size() == 0) {
			try {
				prstm = con.prepareStatement(sql);
				prstm.setString(1, idFactory);
				int n = prstm.executeUpdate();
				if(n > 0) {
					return true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
	}
	
	// Xóa tổ hiện có trên server
	public boolean deleteTeam(String idTeam) {
		String sql = "delete from ToSanXuat where MaTo = ?";
		boolean checkEmployeeOfTeam = checkEmployeeOfTeam(idTeam);
		if(!checkEmployeeOfTeam) {
			try {
				prstm = con.prepareStatement(sql);
				prstm.setString(1, idTeam);
				int n = prstm.executeUpdate();
				if(n > 0) {
					return true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
	}
	
	// Cập nhật phân xưởng
	public boolean updateFactory(Factory factory) {
		String sql = "update PhanXuong set MaPhanXuong = ?, TenPhanXuong = ?, MaQuanDoc = ? where MaPhanXuong = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, factory.getFactoryID());
			prstm.setString(2, factory.getName());
			prstm.setString(3, factory.getHeadForemanID());
			prstm.setString(4, factory.getFactoryID());
			int n = prstm.executeUpdate();
			if(n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// Cập nhật danh sách tổ theo mã phân xưởng
	public boolean updateListTeam(List<TeamProducing> listTeam, String idFactory) {
		String announce = "";
		List<TeamProducing> listTeamPresent = getListTeamByIdFactory(idFactory);
		try {
			for(TeamProducing team : listTeam) {
				if(!listTeamPresent.contains(team)) {
					TeamProducing teamById = searchTeamByIdTeam(team.getTeamID());
					if(teamById == null) {
						boolean insertTeam = insertTeam(team);
						if(!insertTeam) {
							announce += "false";
							break;
						}
					}else {
						boolean updateQT = updateTeam(team);
						if(!updateQT) {
							announce += "false";
							break;
						}
					}
				
				}
			}
			if(announce.isEmpty()) {
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	// Cập nhật tổ theo mã tổ
	public boolean updateTeam(TeamProducing team) {
		if(team.getFactoryID().isEmpty()) {
			TeamProducing teamById = searchTeamByIdTeam(team.getTeamID());
			team.setFactoryID(teamById.getFactoryID());
		}
		String sql = "update ToSanXuat set MaTo = ?, TenTo = ?, MaToTruong = ?, MaPhanXuong = ? where MaTo = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, team.getTeamID());
			prstm.setString(2, team.getName());
			prstm.setString(3, team.getLeaderID());
			prstm.setString(4, team.getFactoryID());
			prstm.setString(5, team.getTeamID());
			int n = prstm.executeUpdate();
			if(n > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	// Tìm kiếm phân xưởng theo mã phân xưởng
	public Factory searchFactoryByIdFactory(String idFactory) {
		String sql = "select * from PhanXuong where MaPhanXuong = ?";
		Factory factory = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idFactory);
			rs = prstm.executeQuery();
			while(rs.next()) {
				factory = new Factory(rs.getString("MaPhanXuong"), rs.getString("TenPhanXuong"), rs.getString("MaQuanDoc"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return factory;
	}
	
	// Tìm kiếm tổ theo mã tổ
	public TeamProducing searchTeamByIdTeam(String idTeam) {
		String sql = "select * from ToSanXuat where MaTo = ?";
		TeamProducing team = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idTeam);
			rs = prstm.executeQuery();
			while(rs.next()) {
				team = new TeamProducing(rs.getString("MaTo"), rs.getString("TenTo"), rs.getString("MaToTruong"), rs.getString("MaPhanXuong"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return team;
	}
	
	// Kiểm tra nhân viên có trong tổ
	public boolean checkEmployeeOfTeam(String idTeam) {
		String sql = "select * from NhanVienSanXuat where MaTo = ?";
		Worker employee = new Worker();
		List<Worker> listEmployee = new ArrayList<Worker>();
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idTeam);
			rs = prstm.executeQuery();
			while(rs.next()) {
				employee = new Worker(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("SDT"), rs.getString("TenNganHang"), rs.getString("SoTaiKhoan"), rs.getString("TenNguoiThuHuong"), rs.getString("ChuyenMon"), rs.getString("MaTo"));
				listEmployee.add(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(listEmployee.size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	// Lấy tất cả tên của phân xưởng
	public List<String> getAllNameFactory(){
		List<String> listName = new ArrayList<String>();
		try {
			prstm = con.prepareStatement("SELECT MaPhanXuong ,TenPhanXuong FROM PHANXUONG");
			rs = prstm.executeQuery();
			while(rs.next()) {
				String name = new String(rs.getString("MaPhanXuong") +" - " + rs.getString("TenPhanXuong"));
				listName.add(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listName;
	}
	
	// Kiểm tra thứ tự phân xưởng hiện tại
	public int checkOrderFactoryPresent() {
		List<Factory> listFactory = getListFactory();
		int order = 0;
		for(Factory factory : listFactory) {
			int orderIdFactory = Integer.parseInt(factory.getFactoryID().substring(2));
			if(orderIdFactory > order) {
				order = orderIdFactory;
			}
		}
		return order;
	}
	
	// Kiểm tra thứ tự tổ hiện tại của phân xưởng
	public int checkOrderTeamOfFactory(String factoryID) {
		List<TeamProducing> listTeam = getListTeamByIdFactory(factoryID);
		int order = 0;
		for(TeamProducing team : listTeam) {
			int orderTeam = Integer.parseInt(team.getTeamID().substring(4));
			if(orderTeam > order) {
				order = orderTeam;
			}
		}
		return order;
	}
	
	// Tìm kiếm danh sách phân xưởng có mã phân xưởng gần giống với mã hiện có
	public List<Factory> searchListFactory(String factoryID){
		String sql = "select * from PhanXuong where MaPhanXuong like '%" + factoryID + "%'";
		Factory factory = null;
		List<Factory> listFactory = new ArrayList<Factory>();
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				factory = new Factory(rs.getString("MaPhanXuong"), rs.getString("TenPhanXuong"), rs.getString("MaQuanDoc"));
				listFactory.add(factory);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listFactory;
	}
	
	// Tìm kiếm danh sách tổ có mã tổ gần giống với mã hiện có
	public List<TeamProducing> searchListTeam(String TeamID){
		String sql = "select * from ToSanXuat where MaTo like '%" + TeamID + "%'";
		TeamProducing team = null;
		List<TeamProducing> listTeam = new ArrayList<TeamProducing>();
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				team = new TeamProducing(rs.getString("MaTo"), rs.getString("TenTo"), rs.getString("MaToTruong"), rs.getString("MaPhanXuong"));
				listTeam.add(team);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listTeam;
	}
}
