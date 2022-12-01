package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entity.DetailProduction;
import entity.Product;
import entity.Produre;

public class DetailPRoductionDAO {
	private Connection con;
	private PreparedStatement prstm;
	private ResultSet rs;
	
	public DetailPRoductionDAO() {
		con = ConnectDB.getInstance().getConnection();
	}
	
	public List<DetailProduction> getListDetailbyIdProduct(String idProduct){
		List<DetailProduction> listDetail = new ArrayList<DetailProduction>();
		String sql = "select * from ChiTietSanXuat where MaSanPham = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idProduct);
			rs = prstm.executeQuery();
			while(rs.next()) {
				DetailProduction detail = new DetailProduction(rs.getInt("MaCTSX"), rs.getInt("SoLuongSanXuat"),rs.getString("TinhTrang"), rs.getString("MaSanPham"), rs.getDate("ThoiGian"));
				listDetail.add(detail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listDetail;
	}
	
	public boolean insertDetailProduction(DetailProduction detail) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );
		String date = format.format(cal.getTime());// United States style of format.
		java.util.Date myDate = null;
		java.sql.Date sqlDate = null;
		try {
			myDate = format.parse(date);
			sqlDate = new java.sql.Date( myDate.getTime() );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "insert into ChiTietSanXuat values(?,?,?,?,?,?)";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, detail.getDetailProductionID());
			prstm.setInt(2, detail.getQuantityProduction());
			prstm.setInt(3, 0);
			prstm.setString(4, detail.getState());
			prstm.setString(5, detail.getProductId());
			prstm.setDate(6,sqlDate);
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
	
	public boolean updateDetail(DetailProduction detail) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );
		String date = format.format(cal.getTime());// United States style of format.
		java.util.Date myDate = null;
		java.sql.Date sqlDate = null;
		try {
			myDate = format.parse(date);
			sqlDate = new java.sql.Date( myDate.getTime() );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "update ChiTietSanXuat set MaCTSX = ?, SoLuongSanXuat = ?, TinhTrang = ?, ThoiGian = ? where MaCTSX = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, detail.getDetailProductionID());
			prstm.setInt(2, detail.getQuantityProduction());
			prstm.setString(3, detail.getState());
			prstm.setDate(4, sqlDate);
			prstm.setInt(5, detail.getDetailProductionID());
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
	
	public boolean updateDetailNotDate(DetailProduction detail) {
		String sql = "update ChiTietSanXuat set MaCTSX = ?, SoLuongSanXuat = ?, TinhTrang = ? where MaCTSX = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, detail.getDetailProductionID());
			prstm.setInt(2, detail.getQuantityProduction());
			prstm.setString(3, detail.getState());
			prstm.setInt(4, detail.getDetailProductionID());
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
	
	public boolean updateQuantityFinishDetail(int quantityFinish, int detailID) {
		String sql = "update ChiTietSanXuat set SoLuongHoanThanh = ? where MaCTSX = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, quantityFinish);
			prstm.setInt(2, detailID);
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
	
	public boolean updateStateFinishDetailProduct(int detailID ) {
		String sql = "update ChiTietSanXuat set TinhTrang = ? where MaCTSX = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, "Hoàn Thành");
			prstm.setInt(2, detailID);
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
	
	public void updateState(String idProduct){
		List<DetailProduction> listDetail = new ArrayList<DetailProduction>();
		String sql = "select * from ChiTietSanXuat where MaSanPham = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idProduct);
			rs = prstm.executeQuery();
			while(rs.next()) {
				if(rs.getInt("SoLuongSanXuat") == rs.getInt("SoLuongHoanThanh")) {
					updateStateFinishDetailProduct(rs.getInt("MaCTSx"));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public boolean deleteDetail(int detailID) {
		String sql = "delete from ChiTietSanXuat where MaCTSX = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, detailID);
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
	
	public boolean deleteListDetail(String productID) {
		String sql = "delete from ChiTietSanXuat where MaSanPham = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, productID);
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
	
	public DetailProduction searchDetailProductionById(String productID) {
//		int order = searchOrderDetailByProductionById(productID);
		String sql = "select * from ChiTietSanXuat where MaSanPham = ?";
		List<DetailProduction> listDetail = new ArrayList<DetailProduction>();
		DetailProduction detail = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, productID);
			rs = prstm.executeQuery();
			while(rs.next()) {
				detail = new DetailProduction(rs.getInt("MaCTSX"), rs.getInt("SoLuongSanXuat"), rs.getString("TinhTrang"), rs.getString("MaSanPham"), rs.getDate("ThoiGian"));
				listDetail.add(detail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(listDetail.size() > 0) {
			detail = listDetail.get(0);
			for(DetailProduction details : listDetail) {
				if(details.getDetailProductionID() > detail.getDetailProductionID()) {
					detail = details;
				}
			}
			return detail;
		}
		else {
			return null;
		}
	}
	
	public int searchOrderDetailByProductionById(String productID) {
		String sql = "select * from ChiTietSanXuat where MaSanPham = ?";
		List<DetailProduction> listDetail = new ArrayList<DetailProduction>();
		DetailProduction detail = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, productID);
			rs = prstm.executeQuery();
			while(rs.next()) {
				detail = new DetailProduction(rs.getInt("MaCTSX"), rs.getInt("SoLuongSanXuat"), rs.getString("TinhTrang"), rs.getString("MaSanPham"), rs.getDate("ThoiGian"));
				listDetail.add(detail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if(listDetail.size() > 0) {
			int order = 0;
			for(DetailProduction details : listDetail) {
				if(details.getDetailProductionID() > order) {
					order = details.getDetailProductionID();
				}
			}
			return order;
		}
		return 0;
	}
	
	public int getOrderDetailPresent() {
		String sql = "select MAX(MaCTSX) as 'MaLonNhat' from ChiTietSanXuat";
//		List<DetailProduction> listDetail = new ArrayList<DetailProduction>();
//		DetailProduction detail = null;
		int order = 0;
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
//				detail = new DetailProduction(rs.getInt("MaCTSX"), rs.getInt("SoLuongSanXuat"), rs.getString("TinhTrang"), rs.getString("MaSanPham"), rs.getDate("ThoiGian"));
//				listDetail.add(detail);
				order = rs.getInt("MaLonNhat");			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return order;
	}

}
