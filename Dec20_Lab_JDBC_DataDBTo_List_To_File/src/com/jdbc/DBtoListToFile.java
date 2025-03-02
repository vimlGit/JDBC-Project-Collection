package com.jdbc;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DBtoListToFile {

	public static void main(String[] args) {

		Scanner sc =new Scanner(System.in);
		List<EmpInfo> listOfEmp=new ArrayList<>();
		try {
			
			List<Integer> l=new ArrayList<Integer>();
			l.add(23);l.add(2);
			Collections.sort(l);
			System.out.println(l);
			
			Connection con = ConnectionIpmlClass.getConnectionImplClassObj();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM EMP_CSV");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				listOfEmp.add(new EmpInfo(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			System.out.println("sjdfhsf");
			listOfEmp.forEach(System.out::println);
			System.out.println("====================================\n===============================");
			listOfEmp.sort((e1,e2)-> e1.fname().compareTo(e2.fname()));
			
			listOfEmp.forEach(System.out::println);
			
			/*FileWriter fw=new FileWriter("D:\\new\\listTO.csv");			
			for(EmpInfo ei : listOfEmp) {
				
			}*/
			
			FileOutputStream fos=new FileOutputStream("D:\\new\\listTO55.csv");
//			ObjectOutputStream ous = new ObjectOutputStream(fos);
//			ous.writeObject(listOfEmp);
			for(EmpInfo e:listOfEmp) {
				String s=""+e.fname()+","+e.lName()+","+e.mailId()+","+e.gender()+"\n";
				fos.write(s.getBytes());
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
