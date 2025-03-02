package com.jdbc;

import java.io.Serializable;
import java.util.Comparator;

public record EmpInfo(int id,String fname,String lName,String mailId,String gender) implements Serializable, Comparator<EmpInfo>{

	@Override
	public int compare(EmpInfo o1, EmpInfo o2) {
		
		return o1.fname.compareTo(o2.fname());
	}

}
