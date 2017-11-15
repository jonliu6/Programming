package org.freecode.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MSSQLDemo {
	
	public static void main(String[] args) {
		// for Windows Authentication, when running the application add VM param: -Djava.library.path=C:/DevApps, which has sqljdbc_auth.dll; or put into %PATH%
		String connStr = "jdbc:sqlserver://localhost\\SQLEXPRESS:54126;databaseName=TestDB;user=posupport;password=FujitsuOM5;integratedSecurity=true;";
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connStr);
			String sql = "SELECT * FROM t_user_info";
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("userId") + "," + rs.getString("userName"));
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
				  rs.close();
				}
				catch (Exception ex1) {
					ex1.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				}
				catch (Exception ex2) {
					ex2.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				}
				catch (Exception ex3) {
					ex3.printStackTrace();
				}
			}
		}
	}

}
