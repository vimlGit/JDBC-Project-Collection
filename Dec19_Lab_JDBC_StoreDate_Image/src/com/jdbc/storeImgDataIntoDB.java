package com.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;

public class storeImgDataIntoDB {

	public static void main(String[] args) {

		Scanner sc =new Scanner(System.in);
		try {
			Connection con = ConnectionIpmlClass.getConnectionImplClassObj();
			
			System.out.println("Enter follwing Employee details.!!!");
			System.out.print("Employee ID :");
			int id =  Integer.parseInt(sc.nextLine());           
			System.out.print("Employee Name :");String name = sc.nextLine();
			System.out.print("Salry :");double sal =Double.parseDouble(sc.nextLine());
			
			System.out.print("Image path :");String imgPath = sc.nextLine();
			System.out.print("DOB :");String dob = sc.nextLine();
			
			CallableStatement call = con.prepareCall("{call createEmpWithImage(?,?,?,?,?) }");
			
			call.setInt(1, id); call.setString(2, name); call.setDouble(3, sal);
			call.setString(5, dob);
			//image storing logic
			File filePath = new File(imgPath);
			FileInputStream fis =new FileInputStream(filePath);
			
			call.setBinaryStream(4, fis, filePath.length());
			
		  if( call.execute()) {
			  System.out.println("inserted...");
		  }
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
