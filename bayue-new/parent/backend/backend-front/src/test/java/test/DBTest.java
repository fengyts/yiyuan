package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBTest {
	
	private Logger logger = LoggerFactory.getLogger(DBTest.class);
	
	private Connection conn = null;
	
	public Connection getConn(){
		String url="";
		try {
			String driver = "";
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			logger.error("", e);
		} 
		
		return conn;
	}

}
