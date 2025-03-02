package com.viml.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CrudAppWithJDBC {

	public static void main(String[] args){
		
		Scanner sc=new Scanner(System.in);
		Connection con=null;
		ResultSet rs=null;
		String decision=null;
		PreparedStatement pstmt=null;
		int OneOrZero=0;
		String query=null;
		
		try(sc;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##BATCH37", "ORACLE");
			Statement stmt = con.createStatement();
			System.out.println(".....Successfully Connected....");
			
			do {
			System.out.println("1.Insert data into Employee Table.");
			System.out.println("2.Retrieve all Employee data.");
			System.out.println("3.Retrieve employee whose name stats with 'S'.");
			System.out.println("4.Retrieve employees whose salary between 10000 to 20000.");
			System.out.println("5.Update employee salary with the help of eid.");
			System.out.println("6.delete employee who is getting maximum salary.");
			System.out.println("7.delete employee whose name ends with 'a';");
			System.out.println("8.Exit.");
			
			System.out.print("Select one option :");
			int op=sc.nextInt();
			
			switch(op) {
				case 1: 
					System.out.println("Enter value in following field :");
					System.out.print("empId :");
					int empId=sc.nextInt();
					System.out.print("Name :");
					String name=sc.next();
					System.out.print("Salary :");
					double salary=sc.nextDouble();
					System.out.print("Address :");
					String address=sc.next();
					System.out.print("MainId :");
					String mail =sc.next();
					System.out.print("Phone No :");
					long phoneNo =sc.nextLong();
					//int r=stmt.executeUpdate("INSERT INTO EMPLOYEE_INFO VALUES(empId,name,salary,address,mail,phoneNo)");
					query = "INSERT INTO EMPLOYEE_INFO VALUES(?, ?, ?, ?, ?, ?)";
					pstmt = con.prepareStatement(query);
					// Set the values for the placeholders
					pstmt.setInt(1, empId);
					pstmt.setString(2, name);
					pstmt.setDouble(3, salary);
					pstmt.setString(4, address);
					pstmt.setString(5, mail);
					pstmt.setLong(6, phoneNo);
					// Execute the statement
					OneOrZero = pstmt.executeUpdate();
					if(OneOrZero==1) {System.out.println("SuccessFully Enterd..");}
					break;
				case 2:
					rs=stmt.executeQuery("SELECT * FROM EMPLOYEE_INFO");
					System.out.println("===============Employee Details===================");
					while(rs.next()) {
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getString(4)+"\t\t\t"+rs.getString(5)+"\t\t\t"+rs.getLong(6));
					}
					break;
				case 3:
					query= "SELECT * FROM EMPLOYEE_INFO where empName like 's%'" ;
					rs=stmt.executeQuery(query);
					System.out.println("\n===============Employee Details name start with s ===================");
					if(!rs.next()) {System.err.println("No record found Whose name start from s.");}
					while(rs.next()) {
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getLong(6));
					}
					break; 
				case 4:
					query = "SELECT * FROM EMPLOYEE_INFO where empSalary between 10000 and 20000" ;
					rs=stmt.executeQuery(query);
					System.out.println("===============Employee Details Whose salary b/w 10000 to 20000 ===================");
					while(rs.next()) {
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getLong(6));
					}
					break; 
				case 5:
					System.out.println("Enter empId and Salary to update:");
					System.out.print("Employee Id :"); int id=sc.nextInt();
					System.out.print("Employee updated salary :"); double newSal=sc.nextDouble();
					query = "UPDATE  EMPLOYEE_INFO SET EMPSALARY=? where empId=?";
					pstmt = con.prepareStatement(query);
					// Set the values for the placeholders
					pstmt.setInt(1, id);
					pstmt.setDouble(2, newSal);
					// Execute the statement
					OneOrZero = pstmt.executeUpdate();
					if(OneOrZero==1) {System.out.println("SuccessFully Enterd..");}
					break;
				case 6:
					query = "DELETE FROM EMPLOYEE_INFO WHERE EMPSALARY=(SELECT MAX(EMPSALARY) FROM EMPLOYEE_INFO)" ;
					OneOrZero = stmt.executeUpdate(query);
					if(OneOrZero==1) {System.out.println("SuccessFully Deleted Max salary employee data..");}
					break;
				case 7:
					query = "DELETE FROM EMPLOYEE_INFO WHERE EMPNAME LIKE '%a'" ;
					OneOrZero = stmt.executeUpdate(query);
					if(OneOrZero==1) {System.err.println("SuccessFully Deleted employee ,whose name ends with a .");}
					else
						System.err.println("No record found whose name ends with a");
					break;
				case 8:
					System.err.println("============Exited===================");
					System.exit(0);
				default:
					System.out.println("Invalid input");
			}
				System.out.print("For continue press yes/no :");
				decision=sc.next();
				
			}while(decision.equalsIgnoreCase("yes"));
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
