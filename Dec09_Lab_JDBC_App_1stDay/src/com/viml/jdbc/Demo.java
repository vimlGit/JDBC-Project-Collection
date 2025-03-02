package com.viml.jdbc;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Demo {

		
	public static  void main(String[] args) {
		
		ArrayList<Integer> al=new ArrayList<>();
		for(int i=1;i<=5;i++) {
			al.add(i);
		}
		//AbstractCollection<Integer>
		
		Iterator<Integer> it = al.iterator();
		
		
		it.forEachRemaining(System.out::println);
		
		ListIterator<Integer> listIterator = al.listIterator();

		//((a)->System.out.println(a));
	}

}
