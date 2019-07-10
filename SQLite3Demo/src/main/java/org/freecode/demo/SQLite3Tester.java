package org.freecode.demo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class SQLite3Tester {

	public static void main(String[] args) {
		Properties props = new Properties();
		String propertyFileName = "/app.properties";
		String appName = null;
		try {
			props.load(SQLite3Tester.class.getResourceAsStream(propertyFileName));
			appName = props.getProperty("application.name");
		}
		catch (Exception ex) {
			System.err.println("Exception caught when loading the properties from " + propertyFileName + "\n" + ex.getMessage());
			appName = "SQLite3Tester";
		}
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
        // String dbUrl = "jdbc:sqlite:/home/fedora/workspace/Programming/SQLite3Demo/src/main/resources/knowledge_base.sqlite3"; // absolute path in a Linux/Unix file system
		// String dbUrl = "jdbc:sqlite::resource:knowledge_base.sqlite"; // since the sqlite3 database needs writable permission, cannot build into the .jar file
		StringBuilder dbUrl = new StringBuilder("jdbc:sqlite:");
		String dbFileName = null;
		if (args == null || args.length < 1) {
			dbFileName = appName + ".sqlite3";
		}
		else {
			dbFileName = args[0];
		}
		
		File dbFile = new File(dbFileName);
		if (!dbFile.exists()) {
			System.out.println("Error: cannot find a SQLite 3 database file - " + dbFileName + ".\nTry java -jar <.jar file name> <your database file full name>");
			System.exit(0);
		}
		else {
			dbUrl.append(dbFileName);
		}
		
		// String dbUrl = "jdbc:sqlite:knowledge_base.sqlite"; // current folder (with the application)
        String testString = "Test Title";
        int num = 0;
        try {
        	conn = DriverManager.getConnection(dbUrl.toString());
        	System.out.println("DB Connected");
        	stmt = conn.createStatement();
        	String sql = "INSERT INTO t_knowledge_base " +
        	             "(title, category, description) " +
        			     "VALUES ('" + testString + "', 'Temporary Document', 'Check, 1, 2, 3...')";
        	num = stmt.executeUpdate(sql);
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
