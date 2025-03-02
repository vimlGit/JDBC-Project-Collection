package com.jdbc;

import java.util.Comparator;

class AgeComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {

		return o1.age-o2.age;
	}
	
}

public class Employee {

	int id;
	String name;
	int age;
	double salary;
	
	public Employee(int id, String name, int age, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public int getAge() {
		return this.age ;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
	}

	
}
