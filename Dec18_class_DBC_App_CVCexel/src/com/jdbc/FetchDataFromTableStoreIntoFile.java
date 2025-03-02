package com.jdbc;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FetchDataFromTableStoreIntoFile {

	public static void main(String[] args) {

		try {
			Connection con = ConnectionIpmlClass.getConnectionImplClassObj();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM EMP_CSV");
			ResultSet rs = ps.executeQuery();
			FileWriter fw=new FileWriter("D:\\new\\emp1.text");
			
			fw.write("id    fname     lname      mailId         gender");
			while(rs.next()) {
				String format=String.format("%s,%s,%s,%s,%s",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5) );
				fw.write("\n");
				fw.write(format);
				
			}
		System.out.println("Succesfully stored into file.");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
