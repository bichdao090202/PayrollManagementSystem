package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Produre;

public class ProdureDAO {
	private Connection connection;

	public ProdureDAO() {
		connection = ConnectDB.getInstance().getConnection();
	}

	public List<Produre> getAllProdureByProductID(String id) {
		List<Produre> listProdure = new ArrayList<Produre>();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from QuyTrinh where MaSanPham = ?");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produre Produre = new Produre(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(5),
						rs.getString(4));
				listProdure.add(Produre);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listProdure;
	}

	public Produre getProdureByID(String id) {
		Produre p = new Produre();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from QuyTrinh where MaQuyTrinh = ?");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				p = new Produre(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(5), rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
