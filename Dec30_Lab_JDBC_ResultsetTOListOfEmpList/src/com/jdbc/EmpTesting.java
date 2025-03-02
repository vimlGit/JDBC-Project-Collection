package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EmpTesting {

	public static void main(String[] args) {

		try {
			ArrayList<Employee> empList=new ArrayList<>();
			ArrayList<Employee> filteredEmpList=new ArrayList<>();
			
			Connection con = ConnectionIpmlClass.getConnectionImplClassObj();
			PreparedStatement stmt = con.prepareStatement("SELECT EMPNO,ENAME,DEPTNO,SAL FROM EMP");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				empList.add(new Employee(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getDouble(4)));
			}
			
			//Filter the employees with a salary greater than 50,000.
			
			for(Employee emp: empList) {
				if(emp.getSalary()>1500) {
					filteredEmpList.add(emp);
				}
			}
		//Sort the employees by sal in asc order using a custom comparator.
			//empList.sort((e1,e2)->Double.valueOf(e1.getSalary()).compareTo(Double.valueOf(e2.getSalary())));

		//Double.valueOf(e1.getSalary()).compareTo(Double.valuef(e2.getSalary()));	
			Map<Integer, List<Employee>> deptWiseEmpGroup=new HashMap<>();
			ArrayList<Employee> TenthEmpList=new ArrayList<>();
			ArrayList<Employee> TwenthEmpList=new ArrayList<>();
			ArrayList<Employee> ThirtEmpList=new ArrayList<>();
				for(Employee e :empList) {
					if(e.getDeptno()==10) {
						TenthEmpList.add(e);
						deptWiseEmpGroup.putIfAbsent(e.getDeptno(), TenthEmpList);
					}
					else if(e.getDeptno()==20) {
						TwenthEmpList.add(e);
						deptWiseEmpGroup.putIfAbsent(e.getDeptno(), TwenthEmpList);
					}
					else if(e.getDeptno()==30) {
						ThirtEmpList.add(e);
						deptWiseEmpGroup.putIfAbsent(e.getDeptno(), ThirtEmpList);
					}
					
				}
				
				Iterator<Entry<Integer, List<Employee>>> iterator = deptWiseEmpGroup.entrySet().iterator();
				iterator.forEachRemaining(System.out::println);
	  
		//employee with the highest salary and store it in a separate variable.
		//System.out.println("Highest sal Emp:\n"+ empList.get(empList.size()-1));
				
		//.Count the number of emps in each dept and store the counts in a Map<Integer, Integer>.
		Map<Integer,Integer> deptWiseCounts =new HashMap<>();
		int tenthC=0;int twenthC=0;int thirthC=0;
		for(Employee e : empList) {
			if(e.getDeptno()==10) {
				tenthC++;
				deptWiseCounts.put(e.getDeptno(),tenthC);
			}
			else if(e.getDeptno()==20) {
				twenthC++;
				deptWiseCounts.put(e.getDeptno(),twenthC);
			}
			else if(e.getDeptno()==30) {
				thirthC++;
				deptWiseCounts.put(e.getDeptno(),thirthC);
			}
		}
		
	//	System.out.println(deptWiseCounts);
	
		//6.Print the list of employees in each department.
		
		empList.sort(new SortSal());
		//empList.forEach(System.out::println);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
