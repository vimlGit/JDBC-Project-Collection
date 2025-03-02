package com.jdbc.ResumeFetchFromDB;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;



public class ELCResume {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		PreparedStatement ps=null;
		try(sc;Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##BATCH37","ORACLE");)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Enter flowing employee details.");
			System.out.print("Employee ID :"); int id =   Integer.parseInt(sc.nextLine()) ;        //sc.nextInt();
			
			System.out.print("Enter path ,where you want store:");String resumePath = sc.nextLine();
			
			File filePath=new File(resumePath);
			ps = con.prepareStatement("SELECT * FROM emp_enfo WHERE empid=?");
			ps.setInt(1, id); 	
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())		
			{
				Blob blob = rs.getBlob(6);
				byte[] bytes = blob.getBytes(1, (int) blob.length());
				
				FileOutputStream fos =new FileOutputStream(filePath);
				fos.write(bytes);
				System.out.println("resume file fetched sucessfully.");
				fos.close();
			}
			else {
				System.err.println("Invalid ID.");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
