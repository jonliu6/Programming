package org.freecode.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDemo {
	
	public final static String DB_URL = "jdbc:sqlite:C:/temp/testdb.sqlite";
	private String dbUrl = null;
	
	public String getDbUrl() {
		if (dbUrl == null) {
			setDbUrl(DB_URL);
		}
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SQLiteDemo sd = new SQLiteDemo();
		Connection conn = sd.connectToDB();
		sd.executeDML(conn, "INSERT INTO t_user_info (username, contact) VALUES ('Joe Doe', 'Timer Ave, Washington DC')");
		ResultSet rs = sd.runQuery(conn, "SELECT * FROM t_user_info");
		System.out.println("Query Result:\n" + sd.printResultSet(rs));
		sd.executeDML(conn, "UPDATE t_user_info SET username = 'Rose Mary' WHERE username = 'Joe Doe'");
		rs = sd.runQuery(conn, "SELECT * FROM t_user_info");
		System.out.println("Query Result:\n" + sd.printResultSet(rs));
		sd.executeDML(conn, "DELETE FROM t_user_info WHERE username = 'Rose Mary'");
		rs = sd.runQuery(conn, "SELECT * FROM t_user_info");
		System.out.println("Query Result:\n" + sd.printResultSet(rs));
		sd.disconnectFromDB(conn);
	}
	
	public Connection connectToDB() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(getDbUrl());
			System.out.println("Got a SQLite Connection");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return conn;
	}

	public ResultSet runQuery(Connection conn, String querySQL) {
		ResultSet rs = null;
		if (conn != null) {
			try {
			    Statement stat = conn.createStatement();
			    rs = stat.executeQuery(querySQL);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return rs;
	}
	
	/**
	 * Todo: handle field values as generic objects
	 * @param rs
	 * @return
	 */
	public String printResultSet(ResultSet rs) {
		StringBuilder sb = new StringBuilder();
		ResultSetMetaData mrs = null;
		if (rs != null) {
			try {
				mrs = rs.getMetaData();
				int colnum = mrs.getColumnCount();
				// construct field names
				for (int i = 1; i <= colnum; ++i) { // starting from column 1
					if (i > 1) {
						sb.append("\t|");
					}
					sb.append("[" + mrs.getColumnLabel(i) + "]");
				}
				sb.append("\n");
				
				// construct data rows
				while (rs.next()) {
					for (int i = 1; i <= colnum; ++i) { // starting from column 1
						if (i > 1) {
							sb.append("\t|");
						}
						sb.append(rs.getString(i));
					}
					sb.append("\n");
				}
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return sb.toString();
	}
	
	public void executeDML(Connection conn, String dmlSQL) {
		if (conn != null) {
			try {
			    PreparedStatement pstat = conn.prepareStatement(dmlSQL);
			    int i = pstat.executeUpdate();
			    System.out.println(i + " record(s) were manipulated.");
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void disconnectFromDB(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("DB Connection is closed.");
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			finally {
				conn = null;
			}
		}
	}
}
