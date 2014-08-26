package net.kronosjp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.kronosjp.exception.MayoException;

public class KronosDBConnection {
	
	private String url = "jdbc:mysql://localhost/kronosDB";
	private String root = "root";
	private String password = "tatsuya0505";

	public Connection getConnection () throws MayoException {
		Connection conn = null;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, root, password);
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new MayoException("コネクション取得失敗");
		} 
		return conn;
	}
}