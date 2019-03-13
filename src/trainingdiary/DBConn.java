package trainingdiary;

import java.sql.*;
import  java.util.Properties;

public abstract class DBConn {
	protected Connection conn;
	public DBConn() {}
	
	public void connect() {
		try {
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			// Class.forName("com.mariadb.jdbc.Driver").newInstance();
			//Properties p = new Properties();
			
			//p.put("user", "askbk_tdt4145");
			//p.put("password", "kuledatabaser");
			
			conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no/askbk_tdt4145", "askbk_tdt4145", "kuledatabaser");
		} catch (Exception e) {
			throw new RuntimeException("Unable to connect", e);
		}
	}
}
