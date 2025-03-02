package com.jdbc.BookDetails69;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class BookDetails69 {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		String query=null;
		
		try(sc) {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##BATCH37","ORACLE");
			Statement stmt = con.createStatement();
			System.out.println("======Enter following Book details==========");
			System.out.print("Book Code :"); int bcode=Integer.parseInt(sc.nextLine());
			System.out.print("Book name :"); String bname=sc.nextLine();
			System.out.print("Author name :"); String bAuthor=sc.nextLine();
			System.out.print("Book price :"); double bprice=sc.nextDouble();
			System.out.print("Book Quantity :"); double qty=sc.nextInt();
			query="INSERT INTO BookDetails69 VALUES("+bcode+",'"+bname+"','"+bAuthor+"',"+bprice+","+qty+")";
			if(stmt.executeUpdate(query)>0);
			  System.out.println("data inserted successfully.");
			
			System.out.println("======Getting Book details Enter book code==========");
			System.out.print("Book Code :"); int bcode1=Integer.parseInt(sc.nextLine());
			query="SELECT * FROM BookDetails69 WHERE BCODE="+bcode1+"";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getDouble(4)+"\t"+rs.getInt(5));
			}
			else {
			  System.err.println("Invalid book code.");
			}
		}
		catch(SQLIntegrityConstraintViolationException ie) {
			System.err.println("This book already there.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
