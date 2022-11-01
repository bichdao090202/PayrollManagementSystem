package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;


import entity.Produre;
import entity.Product;

public class ProductDAO {
	private Connection con;
	private PreparedStatement prstm;
	private ResultSet rs;
	private ArrayList<Produre> ListProcedure;
	
	public ProductDAO() {
		con = ConnectDB.getInstance().getConnection();
	}
	
	public List<Product> getListProduct(){
		List<Product> listProduct = new ArrayList<Product>();
		String sql = "select * from SanPham";
		try {
			prstm = con.prepareStatement(sql);
			rs = prstm.executeQuery();
			while(rs.next()) {
				Product sanPham = new Product(rs.getString("MaSanPham"), rs.getString("TenSanPham"), rs.getInt("SoLuongSanXuat"));
				listProduct.add(sanPham);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listProduct;
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
	
	public boolean insertProduct(Product product) {
		String sql = "insert into SanPham values(?,?,?)";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, product.getProductID());
			prstm.setString(2, product.getName());
			prstm.setInt(3, product.getQuantity());
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
//		boolean deleteDSQuyTrinh = deleteDSQuyTrinhTheoSP(maSP);
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
		String sql = "update SanPham set MaSanPham = ?, TenSanPham = ?, SoLuongSanXuat = ? where MaSanPham = ?";
		try {
			prstm = con.prepareStatement(sql);
			prstm.setString(1, product.getProductID());
			prstm.setString(2, product.getName());
			prstm.setInt(3, product.getQuantity());
			prstm.setString(4, product.getProductID());
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
				product = new Product(rs.getString("MaSanPham"), rs.getString("TenSanPham"), rs.getInt("SoLuongSanXuat"));
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
			// TODO: handle exception
			e.printStackTrace();
		}
		return procedure;
	}
}

