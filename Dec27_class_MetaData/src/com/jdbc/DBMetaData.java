package com.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

public class DBMetaData {

	public static void main(String[] args) {

		try {
			Connection con = ConnectionImplementationClassObj.getConnectionObj();
			DatabaseMetaData metaData = con.getMetaData();
			String databaseProductName = metaData.getDatabaseProductName();
			System.out.println("databaseProductName :"+databaseProductName);
			int databaseMajorVersion = metaData.getDatabaseMajorVersion();
			System.out.println("databaseMajorVersion :"+databaseMajorVersion);
			String driverVersion = metaData.getDriverVersion();
			System.out.println("driverVersion :"+driverVersion);
			String driverName = metaData.getDriverName();
			System.out.println("driverName :"+driverName);
			int maxColumnsInTable = metaData.getMaxColumnsInTable();
			System.out.println("maxColumnsInTable :"+maxColumnsInTable);
		}
		catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

}
