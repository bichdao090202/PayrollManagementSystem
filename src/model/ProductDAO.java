package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;


import entity.Produre;
import entity.TimesheetsFactory;
import entity.Assignment;
import entity.DetailProduction;
import entity.Product;

public class ProductDAO {
	private Connection con;
	private PreparedStatement prstm;
	private ResultSet rs;
	private ArrayList<Produre> ListProcedure;
	private DetailPRoductionDAO detailDAO = new DetailPRoductionDAO();
	
	public ProductDAO() {
		con = ConnectDB.getInstance().getConnection();
	}
	
	// Lấy tất cả sản phẩm hiện có
	public List<Product> getAllProduct() {
		List<Product> listProduct = new ArrayList<Product>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from SanPham");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Product product = new Product(rs.getString("MaSanPham"), rs.getString("TenSanPham"));
				listProduct.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listProduct;
	}
	
	// Lấy sản phẩm có mã quy trình đã cho
	public Product getProductByProdureID(String idProdure) {
		Product product = new Product();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"select sp.MaSanPham, sp.TenSanPham from  SanPham sp join QuyTrinh qt on sp.MaSanPham = qt.MaSanPham where MaQuyTrinh = ?");
			stmt.setString(1, idProdure);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getString("MaSanPham"), rs.getString("TenSanPham"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
	
	// Lấy tất cả sản phẩm hiện có
	public List<Product> getListProduct(){
		List<Product> listProduct = new ArrayList<Product>();
		String sql = "select * from SanPham";
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				Product sanPham = new Product(rs.getString("MaSanPham"), rs.getString("TenSanPham"));
				listProduct.add(sanPham);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listProduct;
	}
	
	// Lấy danh sách phân công theo mã quy trình, mã hợp đồng sản xuất và ngày lập hợp đồng
	public List<Assignment> getListAssignment(String produreID, int detailID, Date dateDetail){
		List<Assignment> listAssignment = new ArrayList<Assignment>();
		String sql = "select * from PhanCong where MaQuyTrinh = ? AND MaHopDong = ? AND NgayThamGia >= ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, produreID);
			prstm.setInt(2, detailID);
			prstm.setString(3, dateDetail+"");
			rs = prstm.executeQuery();
			while(rs.next()) {
				Assignment assignment = new Assignment(rs.getInt("MaPhanCong"), rs.getString("MaQuyTrinh"), rs.getString("MaNhanVien"), rs.getDate("NgayThamGia").toLocalDate(), rs.getInt("MaHopDong"));
				listAssignment.add(assignment);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listAssignment;
	}
	
	// Lấy tất cả quy trình hiện có
	public List<Produre> getListProcedure(){
		List<Produre> listProcedure = new ArrayList<Produre>();
		String sql = "select * from QuyTrinh";
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				Produre qt = new Produre(rs.getString("MaQuyTrinh"), rs.getString("TenQuyTrinh"), rs.getDouble("GiaQuyTrinh"), rs.getInt("ThuTuSanXuat"), rs.getString("MaSanPham"));
				listProcedure.add(qt);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listProcedure;
	}
	
	// Lấy danh sách quy trình theo mã sản phẩm
	public List<Produre> getListProcedurebyIdProduct(String idProduct){
		List<Produre> dsQuyTrinh = new ArrayList<Produre>();
		String sql = "select * from QuyTrinh where MaSanPham = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idProduct);
			rs = prstm.executeQuery();
			while(rs.next()) {
				Produre qt = new Produre(rs.getString("MaQuyTrinh"), rs.getString("TenQuyTrinh"), rs.getDouble("GiaQuyTrinh"),rs.getInt("ThuTuSanXuat"), rs.getString("MaSanPham"));
				dsQuyTrinh.add(qt);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsQuyTrinh;
	}
	
	// Lấy danh sách phân công theo mã quy trình và ngày lập hợp đồng của sản phẩm
	public List<Assignment> getListAssignmentbyIdProdure(String produreID, String date){
		List<Assignment> dsChamCong = new ArrayList<Assignment>();
		String sql = "select * from PhanCong where MaQuyTrinh = ? AND NgayThamGia >= ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, produreID);
			prstm.setString(2, date);
			rs = prstm.executeQuery();
			while(rs.next()) {
				Assignment chamcong = new Assignment(rs.getInt("MaPhanCong"), rs.getString("MaQuyTrinh"), rs.getString("MaNhanVien"),rs.getDate("NgayThamGia").toLocalDate(), rs.getInt("MaHopDong"));
				dsChamCong.add(chamcong);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsChamCong;
	}
	
	// Thêm sản phẩm vào server
	public boolean insertProduct(Product product) {
		String sql = "insert into SanPham values(?,?)";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, product.getProductID());
			prstm.setString(2, product.getName());
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
	
	// Thêm danh sách quy trình vào server
	public boolean insertListProcedure(List<Produre> listProcedure) {
		String sql = "insert into QuyTrinh values(?,?,?,?,?)";
		String announce = "";
		try {
			for(Produre procedure : listProcedure) {
				prstm = con.prepareStatement(sql);
				prstm.setString(1, procedure.getProcedureID());
				prstm.setString(2, procedure.getName());
				prstm.setDouble(3, procedure.getPrice());
				prstm.setString(4, procedure.getProductID());
				prstm.setInt(5, procedure.getNumberOrdinal());
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
	
	// Thêm quy trình vào server
	public boolean insertProcedure(Produre procedure) {
		String sql = "insert into QuyTrinh values(?,?,?,?,?)";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, procedure.getProcedureID());
			prstm.setString(2, procedure.getName());
			prstm.setDouble(3, procedure.getPrice());
			prstm.setString(4, procedure.getProductID());
			prstm.setInt(5, procedure.getNumberOrdinal());
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
	
	// Xóa danh sách quy trình theo mã sản phẩm
	public boolean deleteListProcedureByIdProduct(String idProduct) {
		String sql = "delete from QuyTrinh where MaSanPham = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idProduct);
			int n = prstm.executeUpdate();
			if(n >= 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	// Xóa sản phẩm theo mã sản phẩm
	public boolean deleteProduct(String idProduct) {
		String sql = "delete from SanPham where MaSanPham = ?";
		List<Produre> listProcedure = getListProcedurebyIdProduct(idProduct);
		if(listProcedure.size() == 0) {
			try {
				prstm = con.prepareStatement(sql);
				prstm.setString(1, idProduct);
				int n = prstm.executeUpdate();
				if(n > 0) {
					return true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(listProcedure.size() > 0) {
			boolean deleteAllProdure = deleteListProcedureByIdProduct(idProduct);
			try {
				prstm = con.prepareStatement(sql);
				prstm.setString(1, idProduct);
				int n = prstm.executeUpdate();
				if(n > 0 && deleteAllProdure) {
					return true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
	}
	
	// Xóa quy trình theo mã quy trình
	public boolean deleteProcedure(String idProcedure) {
		String sql = "delete from QuyTrinh where MaQuyTrinh = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idProcedure);
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
	
	// Cập nhật thông tin sản phẩm theo mã sản phẩm
	public boolean updateProduct(Product product) {
		String sql = "update SanPham set MaSanPham = ?, TenSanPham = ? where MaSanPham = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, product.getProductID());
			prstm.setString(2, product.getName());
			prstm.setString(3, product.getProductID());
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
	
	// Cập nhật số lượng hoàn thành của hợp đồng sản xuất theo mã hợp đồng
	public boolean updateQuantityFinished(int quantityFinished ,DetailProduction detail) {
		String sql = "update HopDongSanXuat set SoLuongHoanThanh = ? where MaHopDong = ? AND MaSanPham = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, quantityFinished);
			prstm.setInt(2, detail.getDetailProductionID());
			prstm.setString(3, detail.getProductId());
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
	
	// Cập nhật thông tin danh sách quy trình theo mã sản phẩm
	public boolean updateListProcedure(List<Produre> listProcedure, String idProduct) {
		String announce = "";
		List<Produre> listProcedurePresent = getListProcedurebyIdProduct(idProduct);
		try {
			for(Produre procedure : listProcedure) {
				if(!listProcedurePresent.contains(procedure)) {
					Produre qt = searchProcedureByIdProcedure(procedure.getProcedureID());
					if(qt == null) {
						boolean insertProcedure = insertProcedure(procedure);
						if(!insertProcedure) {
							announce += "false";
							break;
						}
					}else {
						boolean updateQT = updateProcedure(procedure);
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
	
	// Cập nhật thông tin quy trình theo mã quy trình
	public boolean updateProcedure(Produre procedure) {
		if(procedure.getProductID().isEmpty()) {
			Produre procedureById = searchProcedureByIdProcedure(procedure.getProcedureID());
			procedure.setProductID(procedureById.getProductID());
		}
		String sql = "update QuyTrinh set MaQuyTrinh = ?, TenQuyTrinh = ?, GiaQuyTrinh = ?, ThuTuSanXuat = ?, MaSanPham = ? where MaQuyTrinh = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, procedure.getProcedureID());
			prstm.setString(2, procedure.getName());
			prstm.setDouble(3, procedure.getPrice());
			prstm.setInt(4, procedure.getNumberOrdinal());
			prstm.setString(5, procedure.getProductID());
			prstm.setString(6, procedure.getProcedureID());
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
	
	// Tìm kiếm sản phẩm theo mã sản phẩm
	public Product searchProductByIdProduct(String idProduct) {
		String sql = "select * from SanPham where MaSanPham = ?";
		Product product = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, idProduct);
			rs = prstm.executeQuery();
			while(rs.next()) {
				product = new Product(rs.getString("MaSanPham"), rs.getString("TenSanPham"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return product;
	}
	
	// Tìm kiếm quy trình theo mã quy trình
	public Produre searchProcedureByIdProcedure(String idProcedure) {
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
			e.printStackTrace();
		}
		return procedure;
	}
	
	// Tìm kiếm chấm công sản xuất theo mã chấm công
	public TimesheetsFactory searchTimeSheetFactoryById(int assignmentID) {
		String sql = "select * from ChamCongSanXuat where MaPhanCong = ?";
		TimesheetsFactory timesheet = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setInt(1, assignmentID);
			rs = prstm.executeQuery();
			while(rs.next()) {
				timesheet = new TimesheetsFactory(rs.getInt("MaChamCong"), rs.getDate("NgayChamCong"), rs.getInt("SoLuongThanhPham"), rs.getInt("MaPhanCong"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timesheet;
	}
	
	// Tìm kiếm danh sách sản phẩm có mã gần giống với mã được cho
	public List<Product> searchListProduct(String productID){
		String sql = "select * from SanPham where MaSanPham like '%" + productID + "%'";
		Product product = null;
		List<Product> listProduct = new ArrayList<Product>();
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				product = new Product(rs.getString("MaSanPham"), rs.getString("TenSanPham"));
				listProduct.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listProduct;
	}
	
	// Tìm kiếm danh sách quy trình có mã gần giống với mã được cho
	public List<Produre> searchListProdure(String produreID){
		String sql = "select * from QuyTrinh where MaQuyTrinh like '%" + produreID + "%'";
		Produre produre = null;
		List<Produre> listProdure = new ArrayList<Produre>();
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				produre = new Produre(rs.getString("MaQuyTrinh"), rs.getString("TenQuyTrinh"), rs.getDouble("GiaQuyTrinh"),rs.getInt("ThuTuSanXuat"), rs.getString("MaSanPham"));
				listProdure.add(produre);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listProdure;
	}
	
	// Kiểm tra quy trình đã hoặc đang được phân công thực hiện
	public boolean isDeleteProdure(String produreID) {
		String sql = "select * from PhanCong where MaQuyTrinh = ?";
		List<Assignment> listAssignment = new ArrayList<Assignment>();
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, produreID);
			rs = prstm.executeQuery();
			while(rs.next()) {
				Assignment assignment = new Assignment(rs.getInt("MaPhanCong"), rs.getString("MaQuyTrinh"), rs.getString("MaNhanVien"), rs.getDate("NgayThamGia").toLocalDate(), rs.getInt("MaHopDong"));
				listAssignment.add(assignment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(listAssignment.size() == 0) {
			return true;
		}
		return false;
	}

}

