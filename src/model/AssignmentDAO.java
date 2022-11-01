package model;

import java.sql.Connection;

public class AssignmentDAO {
private Connection connection;
	
	public AssignmentDAO() {
		connection = ConnectDB.getInstance().getConnection();
	}
	
}
