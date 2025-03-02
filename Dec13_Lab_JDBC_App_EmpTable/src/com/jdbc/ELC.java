package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ELC {

	public static void main(String[] args) {

		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##BATCH37","ORACLE");)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ArrayList<Employee> al=new ArrayList();
			PreparedStatement pstmt = con.prepareStatement("SELECT EMPNO,ENAME,SAL,DEPTNO FROM EMP");
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				al.add(new Employee(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4)));
			}
			
			al.forEach(System.out::println);
			
			List<Employee> listOfEmpSalMoreThanFifty=new ArrayList<>();
			
			for(Employee emp: al) {
				if(emp.getSal()>50000) {
					listOfEmpSalMoreThanFifty.add(emp);
				}
			}
			System.err.println("Employee Who earn more than 50K .");
			listOfEmpSalMoreThanFifty.forEach(System.out::println);
			
			//sorting salary in ascending order
			
			Collections.sort(al, new SalComparator() );
			al.forEach(System.out::println);
			
			//top 5 employees
			System.out.println("TOp 5 employees...");
			int count =0;
			for(Employee emp: al) {
				count++;
				if(count>5) {
					break;
				}
				System.out.println(emp);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
