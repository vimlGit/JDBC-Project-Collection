package com.jdbc;

import java.util.Comparator;

class SalComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {

		if(o1.salary>o2.salary)
		return 1;
		else if(o1.salary<o2.salary)
			return -1;
		else
			return 0;
	}
	
}

public class Employee {

	int id;
	String name;
	int deptNo;
	double salary;
	
	public Employee(int id, String name, double salary , int deptNo) {
		super();
		this.id = id;
		this.name = name;
		this.deptNo = deptNo;
		this.salary = salary;
	}

	public double getSal() {
		return this.salary ;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", deptNo=" + deptNo + ", salary=" + salary + "]";
	}

	
}
