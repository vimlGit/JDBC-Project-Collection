package com.jdbc.assign02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ELC {

	public static void main(String[] args) {

		long start=0 , end=0;
		ResultSet rs=null;
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##BATCH37","ORACLE");)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			start = System.currentTimeMillis();
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT EMPNO,ENAME,SAL,DEPTNO FROM EMP");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
			}
			end = System.currentTimeMillis();
			System.out.println("createStatement "+(end-start));
			start = System.currentTimeMillis();
			PreparedStatement pstmt = con.prepareStatement("SELECT EMPNO,ENAME,SAL,DEPTNO FROM EMP");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
			}
			
			end = System.currentTimeMillis();
			System.out.println("prepareStatement "+(end-start));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
