package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnectionIpmlClass {

	private ConnectionIpmlClass() {
		
	}
	public static Connection getConnectionImplClassObj() throws Exception {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##BATCH37","ORACLE");
		return con;
	}
	
}
