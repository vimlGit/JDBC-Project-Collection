package com.viml.batchProcessing;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.jdbc.ConnectionImplementationClassObj;

public class BatchProcessing {

	public static void main(String[] args) {

		try {
			Connection con = ConnectionImplementationClassObj.getConnectionObj();
			PreparedStatement ps = con.prepareStatement("INSERT INTO EMPLOYEE_INFO VALUES(?,?,?,?,?)");
			ps.setInt(1,111);ps.setString(2, "Mukesh");
			ps.setFloat(3,543234.434f); 
			ps.setString(4, "Deoria");ps.setString(5,"v@gmail.in");
			ps.setLong(6,97786543);
			
			ps.addBatch();
			
			 ps = con.prepareStatement("update employee_info set empsalary=? where empid=?");
			 ps.setDouble(1,11223344.98); ps.setInt(2,123);
			 ps.addBatch();
			
			 ps = con.prepareStatement("delete from employee_info  where empsalary=(select min(empsalary) from employee_info)");
			ps.addBatch();
			
			ps.executeBatch();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
