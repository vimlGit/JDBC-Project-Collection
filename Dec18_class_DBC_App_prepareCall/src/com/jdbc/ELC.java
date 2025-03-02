package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
					System.out.print("Student Name :");String name = sc.nextLine();
					System.out.print("Branch name :");String branch = sc.nextLine();
					System.out.println("=========Enter Student Address.!!!");
					System.out.print("House No :");String hno = sc.nextLine();
					System.out.print("Street Name :");String sname = sc.nextLine();
					System.out.print("City name :");String city = sc.nextLine();
					System.out.print("State name :");String state = sc.nextLine();
					System.out.print("Pincode :");
					int pincode =  Integer.parseInt(sc.nextLine());
					System.out.println("=========Enter Student contact details===.!!!");
					System.out.print("Mail Id :");String mail = sc.nextLine();
					System.out.print("phone No.:");
					int phone =  Integer.parseInt(sc.nextLine());
					System.out.println("=========Enter Student got six subject marks ===.!!!");
					System.out.print("C language marks :");int c=sc.nextInt();
					System.out.print("Java  marks :");int java=sc.nextInt();
					System.out.print("Python marks :");int python=sc.nextInt();
					System.out.print(".Net marks :");int net=sc.nextInt();
					System.out.print("Java Script marks :");int js=sc.nextInt();
					System.out.print("Spring marks :");int spring=sc.nextInt();
					
					CallableStatement call = con.prepareCall("{call createStudent(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
					call.setInt(1,rollno); call.setString(2, name);   call.setString(3, branch); 
					call.setString(4, hno); call.setString(5,sname); call.setString(6, city); call.setString(7, state); call.setInt(8,pincode);
					call.setString(9, mail); call.setInt(10,phone);
					call.setInt(11,c); call.setInt(12,java); call.setInt(13,python); call.setInt(14,net); call.setInt(15,js); call.setInt(16,spring); 
		
					if(call.execute()) {
						System.out.println("Succesfully");
					}
					else {
						System.err.println("Error..");
					}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
