package com.viml.MedataQ2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.jdbc.ConnectionImplementationClassObj;

public class SudentInfo {

	public static void main(String[] args) {

		try {
		Connection con = ConnectionImplementationClassObj.getConnectionObj();
		PreparedStatement ps = con.prepareStatement("select * from student_info");
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		System.out.println("columnCount :"+columnCount);
		for(int i=1 ;i<=columnCount;i++) {
			String columnName = metaData.getColumnName(i);
		   System.out.println(i+" columnName :"+columnName);
		}
		System.out.println("========================");
		for(int i=1 ;i<=columnCount;i++) {
		String columnTypeName = metaData.getColumnTypeName(i);
		System.out.println(i+" columnTypeName :"+columnTypeName);
		}
		
		CallableStatement call = con.prepareCall("{call createStudent_info(?,?,?,?) }");
		call.setInt(1,101); call.setString(2, "viml"); call.setString(3,"abc101");
		call.setString(4, "Gorakhpur");
		
		call.execute();
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
