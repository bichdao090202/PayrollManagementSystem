package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	private Connection connection;
	private static ConnectDB instance;
	
	public ConnectDB() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=PayrollManagementSystem2";
		String user = "sa";
//		String pwd = "sa";
		String pwd = "123456789";
		try {
			connection = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public synchronized static ConnectDB getInstance() {
		if (instance == null)
			instance = new ConnectDB();
		return instance;
	}
	
	
}
