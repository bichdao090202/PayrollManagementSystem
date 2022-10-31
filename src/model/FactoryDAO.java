package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Worker;
import entity.Factory;
import entity.TeamProducing;

public class FactoryDAO {
	private Connection con;
	private PreparedStatement prstm;
	private ResultSet rs;
	
	public FactoryDAO() {
		con = ConnectDB.getInstance().getConnection();
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
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateListTeam(List<TeamProducing> listTeam, String idFactory) {
		String sqlUpdate = "update ToSanXuat set MaTo = ?, TenTo = ?, MaToTruong = ?, MaPhanXuong = ? where MaTo = ?";
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
			// TODO: handle exception
			e.printStackTrace();
		}
		return factory;
	}
	
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
			// TODO: handle exception
			e.printStackTrace();
		}
		if(listEmployee.size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	
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
}
