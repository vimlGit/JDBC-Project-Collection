package com.jdbc.ResumeFetchFromDB;

public class A {
	public static void main(String[] args) {
		String s1="abc";//1000x
		String s2=new String("abc");
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		
		System.out.println(s1.equals(s2));
		Integer i=65;//
		String s="A";//[A]
		System.out.println(i.hashCode());
		System.out.println(s.hashCode());
		System.out.println(i.equals(s));
	}

}
