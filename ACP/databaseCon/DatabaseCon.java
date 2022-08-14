package databaseCon;
import java.sql.Connection;
import java.sql.DriverManager;

import getIp.getIpAddress;

public class DatabaseCon {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static String DATABASE_URL = "jdbc:mysql://localhost/acd";
    static Connection con;
    public static void main(String[] args) {
		connection();
	}
    
	public static Connection connection() { 
		
		try {
			Class.forName(JDBC_DRIVER);
			
			try {
				con = DriverManager.getConnection(DATABASE_URL, "root", "");
			} catch (Exception e) {
				e.printStackTrace();
				DATABASE_URL = "jdbc:mysql://"+getIpAddress.getIp()+"/acd";
				con = DriverManager.getConnection(DATABASE_URL, "root", "");
			}
			
			System.out.println("Connection is succesful");
			return con;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
