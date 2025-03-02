package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

public class FetchingEmpNameFromDBWithFunction {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		try(sc) {
			Connection con = ConnectionImplementationClassObj.getConnectionObj();
			System.out.print("Enter Emp id :");int id=sc.nextInt();
			CallableStatement call = con.prepareCall("{call ?:=funcForName(?)}");
			call.setInt(2, id);
			call.registerOutParameter(1,Types.VARCHAR);
			call.execute();
			System.out.println("Employee Name :"+call.getString(1)+" , ID :"+id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
