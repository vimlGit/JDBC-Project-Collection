package com.jdbc.Scrollable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ProductScroll {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		PreparedStatement ps=null;
		ResultSet rs =null;
		int choice=0;
		try(sc;Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##BATCH37","ORACLE");)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			do
			{
				System.out.println("===============Product Management==============");
				System.out.println("1. Insert productdetails into product table.");
				System.out.println("2. Retrieve productdetails in forward direction.");
				System.out.println("3. Retrieve productdetails in reverse direction.");
				System.out.println("4. Retrieve 3rd record from top.");
				System.out.println("5. Retrieve 3rd record from bottom. ");
				System.out.println("6. for Exit from application.");
				System.out.print("Enter your choice :"); choice =sc.nextInt();
				
			switch(choice)
			{
			case 1:
				System.out.print("How many product,you want to enter :");
				int num =sc.nextInt();
				for(int i=1 ;i<=num;i++) {
					System.out.println("Enter  flowing product details.");
					System.out.print("Product ID :"); int id = sc.nextInt() ; 
					 sc.nextLine();//sc.nextInt();
					System.out.print("Product Name :");String name = sc.nextLine();
					System.out.print("Product price :");double price = sc.nextDouble();
					System.out.print("Product Quantity :");int qty = sc.nextInt();

					ps = con.prepareStatement("INSERT INTO PRODUCT VALUES(?,?,?,?)");
					ps.setInt(1, id); ps.setString(2, name); ps.setDouble(3, price);
					ps.setInt(4, qty);

					if(ps.executeUpdate()>0) {
						System.out.println("Data inserted successfully.");
					}
				}
				break;
			case 2:
				System.out.println("====productdetails in forward direction.===========");
				ps = con.prepareStatement("SELECT * FROM PRODUCT");
				rs = ps.executeQuery();
				
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
				}
				break;
			case 3:
				System.out.println("====productdetails in Reverce direction.===========");
				ps = con.prepareStatement("SELECT * FROM PRODUCT",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = ps.executeQuery();
				rs.afterLast();
				
				while(rs.previous()) {
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
				}
				break;
			case 4:
				System.out.println("==== 3rd productdetails.===========");
				ps = con.prepareStatement("SELECT * FROM PRODUCT",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = ps.executeQuery();
				rs.absolute(3);
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
				break;
			case 5:
				System.out.println("==== 3rd productdetails from buttom.===========");
				ps = con.prepareStatement("SELECT * FROM PRODUCT",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = ps.executeQuery();
				//rs.afterLast();
				//rs.relative();
				if(rs.absolute(-3)){				
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
				}
				break;
			case 6:
				System.out.println("=======Thank YOu ,visit again======");
				System.err.println("Successfully Logout.");
				break;
			default :
				System.err.println("Invalid choice..!!!");
			}
			
			}while(choice!=6);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
