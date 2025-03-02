package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.Scanner;

public class BankingTransaction {

	public static void main(String[] args) {
		try {
		Scanner sc =new Scanner(System.in);
		Connection con = ConnectionImplementationClassObj.getConnectionObj();
		
		System.out.println(con.getAutoCommit());
		con.setAutoCommit(false);
		PreparedStatement ps = con.prepareCall("SELECT * FROM BANK69 where accno=?");
		PreparedStatement ps2 = con.prepareStatement("UPDATE BANK69 SET BALANCE=BALANCE + ? WHERE ACCNO=?");
		Savepoint sp01 = con.setSavepoint();
		System.out.print("Enter HomeAccNo :"); long HomeAccNo =sc.nextLong();
		ps.setLong(1, HomeAccNo);
		ResultSet rs1 = ps.executeQuery();
		if(rs1.next()) {
			float balance=rs1.getFloat(3);
			System.out.print("Enter BenificierAccNo :"); long BeAccNo =sc.nextLong();
			ps.setLong(1, BeAccNo);
			if(ps.executeQuery().next()) {
				System.out.print("Enter ,How much amount to transfer :");float amt=sc.nextFloat();
				if(amt<=0) {
					throw new IllegalArgumentException("Amount can't be negative..");
				}
				if(amt<=balance)
				{
					ps2.setFloat(1, -amt);   ps2.setLong(2, HomeAccNo);
					int success1=ps2.executeUpdate();
					ps2.setFloat(1, +amt);   ps2.setLong(2, BeAccNo);
					int success2=ps2.executeUpdate();

					if(success1==success2) {
						System.out.println("Amount transfered Successfully....");
						con.setAutoCommit(true);
					}
					else {
						System.err.println("");
						con.rollback(sp01);;
					}
				}
				else {
					System.err.println("Insuffiecient fund...");
				}
			}
			else{
				System.err.println("Benifeciary Accno is Invalid...");
			}
		}
		else {
			System.err.println(HomeAccNo+" is invalid accNo...");
		}
			
		}
		catch(IllegalArgumentException iae) {
			System.err.print(iae.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
