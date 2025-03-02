package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Scanner;

public class ELC {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		try(sc;Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##BATCH37","ORACLE");)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
						
					System.out.println("Enter follwing Student details.!!!");
					System.out.print("Student rollno :");
					int rollno =  Integer.parseInt(sc.nextLine());         
					
					CallableStatement call = con.prepareCall("{call fetchStudent(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
					call.setInt(1, rollno);
					
					call.registerOutParameter(2, Types.VARCHAR);
					call.registerOutParameter(3, Types.VARCHAR);
					call.registerOutParameter(4, Types.VARCHAR);
					call.registerOutParameter(5, Types.VARCHAR);
					call.registerOutParameter(6, Types.VARCHAR);
					call.registerOutParameter(7, Types.VARCHAR);
					call.registerOutParameter(8, Types.NUMERIC);
					call.registerOutParameter(9, Types.VARCHAR);
					call.registerOutParameter(10, Types.NUMERIC);
					call.registerOutParameter(11, Types.FLOAT);
					call.registerOutParameter(12, Types.FLOAT);
					call.registerOutParameter(13, Types.VARCHAR);
					call.execute();
					System.out.println("=======Student Details======");
					System.out.println("Student RollNo 		:"+rollno);
					System.out.println("Student Name 		:"+call.getString(2));
					System.out.println("Branch name 		:"+call.getString(3));
					System.out.println("=======Student Adrees======");
					System.out.println("House No		 	:"+call.getString(4));
					System.out.println("Street Name 		:"+call.getString(5));
					System.out.println("city Name 			:"+call.getString(6));
					System.out.println("State Name 			:"+call.getString(7));
					System.out.println("Area pincode 		:"+call.getString(8));
					System.out.println("=======Student Contact Details======");
					System.out.println("Mail Id 			:"+call.getString(9));
					System.out.println("Phone No 			:"+call.getLong(10));
					System.out.println("=======Student Result======");
					System.out.println("Total marks			:"+call.getDouble(11));
					System.out.println("Percentage 			:"+call.getDouble(12));
					System.out.println("Grade	 			:"+call.getString(13));
					
					
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
