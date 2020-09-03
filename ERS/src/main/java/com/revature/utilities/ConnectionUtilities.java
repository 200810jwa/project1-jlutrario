package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtilities {

	private static Connection conn = null;
	
	private ConnectionUtilities() {
		super();
	}
	
	public static Connection getConnection() {
		try {
			
			if (conn != null && !conn.isClosed()) {
				return conn;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("FAILED TO REUSE A CONNECTION");
			return null;
		}
		
		// jdbc:postgresql://host_name:port/DB_name
		String url = "jdbc:postgresql://training.cub6v59od0nw.us-east-1.rds.amazonaws.com:5432/jwa200810";
		
		String username = "root";
		String password = "password";
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("FAILED TO GET CONNECTION");
			return null;
		}
		
		return conn;
	}
}
