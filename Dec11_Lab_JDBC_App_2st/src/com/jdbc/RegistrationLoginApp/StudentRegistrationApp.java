package com.jdbc.RegistrationLoginApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentRegistrationApp {

	public static void main(String[] args) throws SQLException {

		Scanner sc =new Scanner(System.in);
		Statement stmt=null;
		String query=null;
		ResultSet rs=null;
		int choice=0;
		int op=0;
		try (sc;Connection	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##BATCH37","ORACLE");)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    stmt = con.createStatement();
			do {
				System.out.println("==========Student Managemrn.=============");
				System.out.println("1. Registration ");
				System.out.println("2. Login ");
				
				System.out.print("Select One option :"); op=sc.nextInt();
				
				switch(op) {
					case 1:
						System.out.println("Registration. Enter following student details...");
						
						System.out.print("Student RollNo:");int rollNo=sc.nextInt();sc.nextLine();
						System.out.print("Student name:");String name=sc.nextLine();
						System.out.print("Student Percentage:");double marks=sc.nextDouble();sc.nextLine();
						System.out.print("Student mailId:");String mailId=sc.nextLine();
						System.out.print("Student phone No:");long phNo=sc.nextInt();
						
						
						query = "INSERT INTO STUDENT VALUES("+rollNo+",'"+name+"',"+marks+",'"+mailId+"',"+phNo+")" ;
						if(stmt.executeUpdate(query)>0) {
							System.out.println("You are Registered successfully.");
						}
						else { 
							System.err.println("Check your data it cant be inseted..");
						}
						break;
					case 2:
						System.out.println("for Login enter you RollNo and Name:");
						System.out.print("Student RollNo:");int roll=sc.nextInt(); sc.nextLine();
						System.out.print("Student name:");String sname=sc.nextLine();
						System.out.println("1Loging..");
						query = "SELECT * FROM STUDENT WHERE ROLLNO="+roll+" AND NAME='"+sname+"'";
						 rs = stmt.executeQuery(query);
						if(rs.next()) {
							System.out.println("Your rollNo is :"+rs.getInt(1)+",\tname :"+rs.getString(2));
						
							do {
								System.out.println("====login successfully , select your choice===");
								System.out.println("1. Show students whose marks>60%.");
								System.out.println("2. Update MailId & Phno based on RollNo. ");
								System.out.println("3. Delete student whose percentage between 80% to 90% ");
								System.out.println("4. Find how many student got more than 60%. ");
								System.out.println("5. Enter 5 to LogOut ");
								System.out.println("Enter choice : ");choice=sc.nextInt();
														
								switch(choice) {
								case 1:
									System.out.println("1Student who got more 60% detatils..");
									query = "select * from student where PERCENTAGE>60 ";
									rs = stmt.executeQuery(query);
									System.out.println("2Student who got more 60% detatils..");
									System.out.println("RollNo\tName\tPercentage"+rs.next());
									if(rs.next()) {
										System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+rs.getDouble(3));

									}
									else {
										System.out.println("No daya found.");
									}
									break;
								case 2:
									System.out.println("Enter new mailId and PhoneNo to change: ");
									System.out.print("New MailID:");String newMailId=sc.next();
									System.out.print("New Phone No:");long newPhoneNo=sc.nextLong();
									query = "update student set mailid="+newMailId+",rollno="+newPhoneNo+"";
									if(stmt.executeUpdate(query)>0) {
										System.out.println("Updated successfully.");
									}
									break;
								case 3:
									query = "delete from student where PERCENTAGE between 80 and 90";
									if(stmt.executeUpdate(query)>0) {
										System.out.println("Record Deleted successfully.");
									}
									break;
								case 4:
									query = "select count(*) from student where PERCENTAGE>60 ";
									rs = stmt.executeQuery(query);
									System.out.println("Total no student who got more than 60%.");
									if(rs.next()) {
										System.out.println("total student "+rs.getInt(1));
									}
									break;
								case 5:
									System.err.println("LogOut...");
								
								break;
								default:
									System.err.println("Invalid choice.");
								}
							  }while(choice!=5);
							}
						else{System.err.println("check your roll no and name.");}
						break;
					case 3:
						System.err.println("........You are exited..");
						System.exit(0);
					default:
						System.err.println("Invalid choice.");
					
				}
				
				
			}while(op!=3);
			
		}
		catch(Exception e) {
			
		}
	}

}
