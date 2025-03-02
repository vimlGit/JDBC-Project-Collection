package com.StringMethod;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		 
	String s="    f           dfs                    dsff       dffsdf sfd    ";
	String s1=s.replace(" ","");
	System.out.println(s1);
	String[] s2=	s1.split("");
	System.out.println(Arrays.toString(s2));
	}
}
