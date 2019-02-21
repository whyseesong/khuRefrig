package Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
	private Connection connection;
	private Statement stmt;
	private static DatabaseConnector mInstance;
	
	private DatabaseConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://163.180.116.169:3306/khurefri?autoReconnect=true&useSSL=false", "root", "1234");
			stmt = connection.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}	
	
	private static void initailze() {
		if (mInstance == null) {
			mInstance = new DatabaseConnector();
		}
	}

	public static final DatabaseConnector getInstance() {
		initailze();
		return mInstance;
	}

	public void close() throws SQLException {
		if (mInstance != null) {
			stmt.close();
			mInstance = null;
		}
	}

	public ResultSet query(String sql) throws SQLException {
		return stmt.executeQuery(sql);
	}

	public int update(String sql) throws SQLException {
		return stmt.executeUpdate(sql);
	}
}
