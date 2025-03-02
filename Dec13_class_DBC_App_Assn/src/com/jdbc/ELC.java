package com.jdbc;

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
			PreparedStatement pstmt=null;
			ResultSet rs =null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			int choice=0;
			do {
				System.out.println("1. Add employee.\n2. View All employoees.");
				System.out.println("3. View employee by ID.\n4. UpdateEmployeeById(bSal,hra,da) ");
				System.out.println("5. Delete employee by ID.\n6. for Exit.");
				System.out.print("Enter your choice :");
				choice = sc.nextInt();
				
				switch(choice) {
				case 1:
					System.out.print("How many employees you want enter :");
					int n=sc.nextInt();
					for(int i=1;i<=n;i++) {
						
					System.out.println("Enter follwing Employee details.!!!");
					System.out.print("Employee ID :");
					int id =  Integer.parseInt(sc.nextLine());             //sc.nextInt(); 
					//sc.nextLine();
					System.out.print("Employee Name :");String name = sc.nextLine();
					System.out.print("Employee designation :");String designation = sc.nextLine();
					System.out.print("Employee Basic salary :");double sal = sc.nextDouble();
					System.out.print("Employee HRA percentage :");double hra = sc.nextDouble();
					System.out.print("Employee DA percentage:");double da = sc.nextDouble();
					
					pstmt = con.prepareStatement("INSERT INTO EMPLOYEE69 VALUES(?,?,?,?,?,?,?)");
					pstmt.setInt(1, id);pstmt.setString(2, name);pstmt.setString(3, designation);
					pstmt.setDouble(4,sal);pstmt.setDouble(5,hra);pstmt.setDouble(6,da);
					pstmt.setDouble(7, (sal+(sal*hra/100)+sal*da/100) );
					if(pstmt.executeUpdate()>0) {
						System.out.println("Inserted successfully.");
					}
					}
					break;
				case 2:
					System.out.println("=======All Employee Details================");
					pstmt = con.prepareStatement("SELECT * FROM EMPLOYEE69");
					rs = pstmt.executeQuery();
					while(rs.next()) {
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getDouble(4)+"\t"+rs.getDouble(5)
						+"\t"+rs.getDouble(6)+"\t"+rs.getDouble(7));
					}
					break;
				case 3:
					System.out.println("Enter Employee id to display details :");
					int id=sc.nextInt();
					pstmt=con.prepareStatement("SELECT * FROM EMPLOYEE69 WHERE EID=?");
					pstmt.setInt(1, id);
					rs=pstmt.executeQuery();
					while(rs.next()) {
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getDouble(4)+"\t"+rs.getDouble(5)
						+"\t"+rs.getDouble(6)+"\t"+rs.getDouble(7)+"\n");
					}
					break;
				case 4:
					System.out.println("Enter Id of employee to update BasicSalary,HRA and DA. ");
					System.out.print("Enter ID :"); int empID=sc.nextInt();
					System.out.println("Enter new BasicSalary,HRA and DA :");
					System.out.print("Basic Salary :");double newSal=sc.nextDouble();
					System.out.print("HRA :");double newHRA=sc.nextDouble();
					System.out.print("DA :");double newDA=sc.nextDouble();
					
					pstmt=con.prepareStatement("UPDATE EMPLOYEE69 SET BSAL=?,HRA=?,DA=?,TOTSAL=? WHERE EID=?");
					pstmt.setDouble(1,newSal);pstmt.setDouble(2, newHRA);
					pstmt.setDouble(3, newDA);pstmt.setDouble(4, (newSal+(newSal*newHRA/100)+newSal*newDA/100) );
					pstmt.setInt(4, empID);
					if(pstmt.executeUpdate()>0){
						System.out.println("Updated Successfully.");
					}
					break;
				case 5:
					System.out.println("Enter employee id to delete record :");
					int idToDelete=sc.nextInt();
					pstmt=con.prepareStatement("DELETE FROM EMPLOYEE69 WHERE EID=?");
					pstmt.setInt(1, idToDelete);
					if(pstmt.executeUpdate()>0) {
						System.err.println(idToDelete+" id employee deleted successfully.");
					}
					break;
				case 6:
					System.out.println("Thanks visit again...!!!!.");
					break;
				default:
					System.err.println("Invalid input..");
				}
				
					
			}while(choice!=6);
			
			
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
