package trainingdiary;

import java.sql.*;
import  java.util.Properties;

public abstract class DBConn {
	protected Connection conn;
	public DBConn() {}
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Properties p = new Properties();
			
			p.put("user", "myuser");
			p.put("password", "mypassword");
			
			conn = DriverManager.getConnection("jdb:mysql://127.0.0.1/");
		} catch (Exception e) {
			throw new RuntimeException("Unable to connect", e);
		}
	}
}
