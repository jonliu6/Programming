package com.freecode.xproject.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MySQLHelper {
	private Connection theConnection;
	private InitialContext theContext;
	private DataSource theDataSource;
	private Statement theStatement;

	public MySQLHelper() {
		try {
			theContext = new InitialContext();
			if (theContext != null) {
				theDataSource = (DataSource) theContext.lookup("jdbc/XProjDB");
			}
		} catch (NamingException ne) {
			throw new RuntimeException(
					"NamingException caught when MySQLHelper()");
		}
	}

	public Connection openConnection() {
		if (theConnection == null) {
			if (theDataSource != null) {
				try {
					theConnection = theDataSource.getConnection();
				} catch (SQLException se) {
					throw new RuntimeException(
							"SQLExcption caught when getConnection()");
				}
			}
		}
		return theConnection;
	}

	public void closeConnection() {
		if (theConnection != null) {
			try {
				theConnection.close();
				theConnection = null;
			} catch (SQLException se) {
				throw new RuntimeException(
						"SQLExcption caught when closeConnection()");
			}
		}
	}

	public ResultSet executeQuery(String aSQLString) {
		ResultSet rs = null;

		if (theConnection != null) {
			try {
				theStatement = theConnection.createStatement();
				rs = theStatement.executeQuery(aSQLString);
			} catch (SQLException se) {
				throw new RuntimeException(
						"SQLException caught when executeQuery()");
			} finally {
				theStatement = null;
			}
		}

		return rs;
	}
	
	public int executeUpdate(String aSQLString) {
		int rowCount = 0;
		
		if (theConnection != null) {
			try {
				theStatement = theConnection.createStatement();
				rowCount = theStatement.executeUpdate(aSQLString);
			} catch (SQLException se) {
				throw new RuntimeException(
						"SQLException caught when executeQuery()");
			} finally {
				theStatement = null;
			}
		}
		
		return rowCount;
	}

}
