package org.freecode.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLite3Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
        String dbUrl = "jdbc:sqlite:/home/fedora/workspace/Programming/SQLite3Demo/src/main/resources/knowledge_base.sqlite3";
		// String dbUrl = "jdbc:sqlite::resource:/knowledge_base.sqlite3";
        String testString = "Test Title";
        try {
        	conn = DriverManager.getConnection(dbUrl);
        	System.out.println("DB Connected");
        	stmt = conn.createStatement();
        	String sql = "INSERT INTO t_knowledge_base " +
        	             "(title, category, description) " +
        			     "VALUES ('" + testString + "', 'Temporary Document', 'Check, 1, 2, 3...')";
        	int num = stmt.executeUpdate(sql);
        	System.out.println(num + " insertion(s) completed");
        	
        	sql = "SELECT * FROM t_knowledge_base";
        	res = stmt.executeQuery(sql);
        	while (res.next()) {
        		System.out.println("Title: " + res.getString("title") + "\n" +
        	                       "Category: " + res.getString("category") + "\n" +
        				           "Description: " + res.getString("description") + "\n\n");
        	}
        	
        	if (res != null) {
        		res.close();
        	}
        	
        	sql = "DELETE FROM t_knowledge_base WHERE title = '" + testString + "'";
        	num = stmt.executeUpdate(sql);
        	System.out.println(num + " record(s) with \"" + testString + "\" deleted");
        }
		catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("SQL failure");
		}
		finally {
			res = null;
			if (stmt != null) {
				try {
					stmt.close();
				}
				catch (Exception ex1) {
					ex1.printStackTrace();
				}
				finally {
					stmt = null;
				}
			}
			if (conn != null) {
				try {
					conn.close();	
				}
				catch (Exception ex2) {
					ex2.printStackTrace();
				}
				finally {
					conn = null;
				}
			}
		}
	}

}
