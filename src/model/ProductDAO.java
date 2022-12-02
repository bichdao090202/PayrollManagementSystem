package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;


import entity.Produre;
import entity.TimesheetFactory;
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
	
	public List<Product> getAllProduct() {
		List<Product> listProduct = new ArrayList<Product>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from  SanPham sp join ChiTietSanXuat ctsx on sp.MaSanPham = ctsx.MaSanPham where ctsx.TinhTrang = N'Sản Xuất'");
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
	
	public List<Product> getListProduct(String state){
		List<Product> listProduct = new ArrayList<Product>();
		List<Product> listProductByState = new ArrayList<Product>();
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
		for(Product product : listProduct) {
			DetailProduction detail = detailDAO.searchDetailProductionById(product.getProductID());
			if(detail != null && detail.getState().equals(state)) {
				listProductByState.add(product);
			}
		}
		return listProductByState;
//		return listProduct;
	}
	
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
	
	public List<Assignment> getListAssignmentbyIdProdure(String produreID, String date){
		List<Assignment> dsChamCong = new ArrayList<Assignment>();
		String sql = "select * from PhanCong where MaQuyTrinh = ? AND NgayThamGia >= ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, produreID);
			prstm.setString(2, date);
			rs = prstm.executeQuery();
			while(rs.next()) {
				Assignment chamcong = new Assignment(rs.getString("MaPhanCong"), rs.getString("MaQuyTrinh"), rs.getString("MaNhanVien"),rs.getDate("NgayThamGia").toLocalDate());
				dsChamCong.add(chamcong);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsChamCong;
	}
	
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
	
	public List<TimesheetFactory> searchTimeSheetFactoryById(String assignmentID) {
		String sql = "select * from ChamCongSanXuat where MaPhanCong = ?";
		List<TimesheetFactory> listTimesheet = new ArrayList<TimesheetFactory>();
		TimesheetFactory timesheet = null;
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, assignmentID);
			rs = prstm.executeQuery();
			while(rs.next()) {
				timesheet = new TimesheetFactory(rs.getInt("MaChamCong"), rs.getDate("NgayChamCong"), rs.getInt("SoLuongThanhPham"), rs.getInt("MaPhanCong"));
				listTimesheet.add(timesheet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTimesheet;
	}
	
	public int quantityProductFinish(String productID) {
		DetailProduction detail = detailDAO.searchDetailProductionById(productID);
		List<Produre> listProdure = this.getListProcedurebyIdProduct(productID);
		List<Assignment> listAssignment = new ArrayList<Assignment>();
		List<TimesheetFactory> listTimeSheetFactory = new ArrayList<TimesheetFactory>();
		int quantitySmall = detail.getQuantityProduction();
		if(listProdure.size() > 0) {
			for(Produre produre : listProdure) {
				int quantityTotal = 0;
				listAssignment = getListAssignmentbyIdProdure(produre.getProcedureID(), detail.getDate().toString());
				if(listAssignment.size() > 0) {
					for(Assignment assignment : listAssignment) {
						LocalDate localDate = Instant.ofEpochMilli(detail.getDate().getTime())
							      .atZone(ZoneId.systemDefault())
							      .toLocalDate();
						if(assignment.getDate().compareTo(localDate) >= 0) {
							List<TimesheetFactory> listTimesheet = searchTimeSheetFactoryById(assignment.getAssignmentID());
							if(listAssignment.size() > 0) {
								for(TimesheetFactory timesheet : listTimesheet) {
									if(timesheet != null) {
										quantityTotal += timesheet.getQuantity();
									}
								}
							}
						}
					}
				}
				else {
					return 0;
				}
				if(quantityTotal < quantitySmall) {
					quantitySmall = quantityTotal;
				}
			}
		}
		else {
			return 0;
		}
		return quantitySmall;
	}
	
	public int getNumProdure(String idProduct) {
		Integer num=0;
		try {
			PreparedStatement stmt = con.prepareStatement("select number = count(MaNhanVien) from NhanVienHanhChinh E join TaiKhoan A on E.MaNhanVien = A.MaNVHC where E.MaNhanVien = ?");
			stmt.setString(1, idProduct);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("number");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
}

