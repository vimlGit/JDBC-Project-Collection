package com.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class StoreCSVfileIntoDB {

	public static void main(String[] args) {
		
		try
		{
			Connection con=ConnectionIpmlClass.getConnectionImplClassObj();
			PreparedStatement ps = con.prepareStatement("INSERT INTO EMP_CSV VALUES(?,?,?,?,?)");
			int k=0;
			FileInputStream fis =new FileInputStream("C:\\Users\\vimle\\Downloads\\csvFile.csv");
			Scanner sc =new Scanner(fis);
			sc.nextLine();//for skipped the heading line record
			
			while(sc.hasNext()) {
				String[] strArray = sc.nextLine().split(",");
				
				for(int i=0;i<strArray.length;i++) {
					ps.setString(i+1,strArray[i]);
				}
				k = ps.executeUpdate();
			}
			System.out.println("K="+k);
						
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
