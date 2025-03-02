package com.jdbc.Resume;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;



public class ELCResume {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		PreparedStatement ps=null;
		FileInputStream fis=null;
		try(sc;Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##BATCH37","ORACLE");)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Enter flowing employee details.");
			System.out.print("Employee ID :"); int id =   Integer.parseInt(sc.nextLine()) ;        //sc.nextInt();
			//sc.nextLine();
			System.out.print("Employee Name :");String name = sc.nextLine();
			System.out.print("Employee Adress :");String adress = sc.nextLine();
			System.out.print("Employee mail ID :");String mail = sc.nextLine();
			System.out.print("Employee phone no:");long phone = Long.parseLong(sc.nextLine());  //sc.nextLong();
			//sc.nextLine();
			System.out.print("Enter path of resume:");String resumePath = sc.nextLine();
			
			File filePath=new File(resumePath);
			if(filePath.exists()) {
				fis = new FileInputStream(filePath);
			}
			else {
				System.err.println("file not avl.");
			}
			
			ps = con.prepareStatement("INSERT INTO emp_enfo VALUES(?,?,?,?,?,?)");
			ps.setInt(1, id); ps.setString(2, name);  ps.setString(3, adress); ps.setString(4, mail); 
			ps.setLong(5, phone);  ps.setBinaryStream(6, fis, filePath.length());
			
			if(ps.executeUpdate()>0) {
				System.out.println("Data inserted sucessfully.");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
