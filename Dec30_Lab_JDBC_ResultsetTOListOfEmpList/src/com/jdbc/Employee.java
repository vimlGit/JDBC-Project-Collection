package com.jdbc;

import java.util.Comparator;


class SortSal implements Comparator<Employee> {

	@Override
	public int compare(Employee e1, Employee e2) {
		return    - Double.valueOf(e1.getSalary()).compareTo(Double.valueOf(e2.getSalary()));
	}
	
}

public class Employee 
{

	private int empno;
	private String name;
	private int deptno;
	private double salary;
	
	
	public Employee(int empno, String name, int deptno, double salary) {
		super();
		this.empno = empno;
		this.name = name;
		this.deptno = deptno;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", name=" + name + ", deptno=" + deptno + ", salary=" + salary + "]";
	}

	public int getEmpno() {
		return empno;
	}

	public String getName() {
		return name;
	}

	public int getDeptno() {
		return deptno;
	}

	public double getSalary() {
		return salary;
	}

	
	
}
