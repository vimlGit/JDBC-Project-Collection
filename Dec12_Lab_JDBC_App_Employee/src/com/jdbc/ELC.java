package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ELC {

	public static void main(String[] args) {

		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##BATCH37","ORACLE");)
		{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ArrayList<Employee> al=new ArrayList();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");
			while(rs.next()) {
				al.add(new Employee(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDouble(4)));
			}
			
			//Collections.sort(al, new AgeComparator());
			//Collections.sort(al, (e1,e2)-> e1.age-e2.age);
			//Collections.sort(al, (e1,e2)-> (int)e1.salary-(int)e2.salary);
			Collections.sort(al, (e1,e2)-> e1.name.length()-e2.name.length());
			
			List<Employee> filterEmpAgeMore25 = new ArrayList<>();
			
			for(Employee emp : al) {
				if(emp.getAge()>25)
				filterEmpAgeMore25.add(emp);
			}
			
			al.forEach(System.out::println);
			System.out.println("===========age>25 =========");
			filterEmpAgeMore25.forEach(System.out::println);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
