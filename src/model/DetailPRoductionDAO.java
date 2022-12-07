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
	
	// lấy danh sách hợp đồng sản xuất theo mã sản phẩm
	public List<DetailProduction> getListDetailbyIdProduct(String idProduct){
		List<DetailProduction> listDetail = new ArrayList<DetailProduction>();
		String sql = "select * from HopDongSanXuat where MaSanPham = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idProduct);
			rs = prstm.executeQuery();
			while(rs.next()) {
				DetailProduction detail = new DetailProduction(rs.getInt("MaHopDong"), rs.getInt("SoLuongSanXuat"),rs.getString("TinhTrang"), rs.getString("MaSanPham"), rs.getDate("ThoiGian"));
				detail.setQuantityFinished(rs.getInt("SoLuongHoanThanh"));
				listDetail.add(detail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listDetail;
	}
	
	// Lấy danh sách hợp đồng sản xuất theo mã sản phẩm và tình trạng
	public List<DetailProduction> getListDetailbyIdProductAndState(String idProduct, String state){
		List<DetailProduction> listDetail = new ArrayList<DetailProduction>();
		String sql = "select * from HopDongSanXuat where MaSanPham = ? AND TinhTrang = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idProduct);
			prstm.setString(2, state);
			rs = prstm.executeQuery();
			while(rs.next()) {
				DetailProduction detail = new DetailProduction(rs.getInt("MaHopDong"), rs.getInt("SoLuongSanXuat"),rs.getString("TinhTrang"), rs.getString("MaSanPham"), rs.getDate("ThoiGian"));
				detail.setQuantityFinished(rs.getInt("SoLuongHoanThanh"));
				listDetail.add(detail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listDetail;
	}
	
	// Thêm hợp đồng sản xuất
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
		String sql = "insert HopDongSanXuat(SoLuongSanXuat, SoLuongHoanThanh, TinhTrang, MaSanPham, ThoiGian) values(?,?,?,?,?)";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, detail.getQuantityProduction());
			prstm.setInt(2, 0);
			prstm.setString(3, detail.getState());
			prstm.setString(4, detail.getProductId());
			prstm.setDate(5,sqlDate);
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
	
	// Cập nhật hợp đồng sản xuất
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
		String sql = "update HopDongSanXuat set SoLuongSanXuat = ?, TinhTrang = ?, ThoiGian = ? where MaHopDong = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, detail.getQuantityProduction());
			prstm.setString(2, detail.getState());
			prstm.setDate(3, sqlDate);
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
	
	// Cập nhật hợp đồng sản xuât nhưng không cập nhật trạng thái
	public boolean updateDetailNotDate(DetailProduction detail) {
		String sql = "update HopDongSanXuat set MaHopDong = ?, SoLuongSanXuat = ?, TinhTrang = ? where MaHopDong = ?";
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
	
	// Cập nhật số lượng hoàn thành của của hợp đồng sản xuất
	public boolean updateQuantityFinishDetail(int quantityFinish, int detailID) {
		String sql = "update HopDongSanXuat set SoLuongHoanThanh = ? where MaHopDong = ?";
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
	
	// Cập nhật tình trạng hoàn thành của hợp đồng
	public boolean updateStateFinishDetailProduct(int detailID ) {
		String sql = "update HopDongSanXuat set TinhTrang = ? where MaHopDong = ?";
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
	
	// Cập nhật trạng thái
	public void updateState(String idProduct){
		List<DetailProduction> listDetail = new ArrayList<DetailProduction>();
		String sql = "select * from HopDongSanXuat where MaSanPham = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idProduct);
			rs = prstm.executeQuery();
			while(rs.next()) {
				if(rs.getInt("SoLuongSanXuat") == rs.getInt("SoLuongHoanThanh")) {
					updateStateFinishDetailProduct(rs.getInt("MaHopDong"));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// Xóa hợp đồng sản xuất
	public boolean deleteDetail(int detailID) {
		String sql = "delete from HopDongSanXuat where MaHopDong = ?";
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
	
	// Xóa danh sách hợp đồng sản xuất
	public boolean deleteListDetail(String productID) {
		String sql = "delete from HopDongSanXuat where MaSanPham = ?";
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
	
	// Tìm kiếm danh sách hợp đồng sản xuất theo mã sản phẩm
	public DetailProduction searchDetailProductionById(String productID) {
		String sql = "select * from HopDongSanXuat where MaSanPham = ?";
		List<DetailProduction> listDetail = new ArrayList<DetailProduction>();
		DetailProduction detail = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, productID);
			rs = prstm.executeQuery();
			while(rs.next()) {
				detail = new DetailProduction(rs.getInt("MaHopDong"), rs.getInt("SoLuongSanXuat"), rs.getString("TinhTrang"), rs.getString("MaSanPham"), rs.getDate("ThoiGian"));
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
	
	// Lấy mã hợp đồng hiện tại
	public int getOrderDetailPresent() {
		String sql = "select IDENT_CURRENT('HopDongSanXuat') as MaHopDongHienTai";
		int order = 0;
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				order = rs.getInt("MaHopDongHienTai");			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return order;
	}
	
	// Tìm kiếm hợp đồng sản xuất
	public DetailProduction searchDetailByIdDetail(String detailID, String productID) {
		String sql = "select * from HopDongSanXuat where MaHopDong = ? AND MaSanPham = ?";
		DetailProduction detail = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, detailID);
			prstm.setString(2, productID);
			rs = prstm.executeQuery();
			while(rs.next()) {
				detail = new DetailProduction(rs.getInt("MaHopDong"), rs.getInt("SoLuongSanXuat"), rs.getString("TinhTrang"), rs.getString("MaSanPham"), rs.getDate("ThoiGian"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return detail;
	}
	
	// Tìm kiếm danh sách sản phẩm có mã gần giống với mã được cho
		public List<DetailProduction> searchListDetailProduction(String detailProductionID){
			String sql = "select * from HopDongSanXuat where MaHopDong like '%" + detailProductionID + "%'";
			DetailProduction detailProduction = null;
			List<DetailProduction> listDetailProduction = new ArrayList<DetailProduction>();
			try {
				prstm = con.prepareStatement(sql);
				rs = prstm.executeQuery();
				while(rs.next()) {
					detailProduction = new DetailProduction(rs.getInt("MaHopDong"), rs.getInt("SoLuongSanXuat"), rs.getString("TinhTrang"), rs.getString("MaSanPham"), rs.getDate("ThoiGian"));
					listDetailProduction.add(detailProduction);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return listDetailProduction;
		}
	
	// Kiểm tra hợp đồng có được phép xóa
	public boolean isDeleteDetail(int detailID) {
		String sql = "select COUNT(*) as soluong from PhanCong where MaHopDong = ?";
		int quantity = 0;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, detailID);
			rs = prstm.executeQuery();
			while(rs.next()) {
				quantity = rs.getInt("soluong");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(quantity == 0) {
			return true;
		}
		return false;
	}

}
