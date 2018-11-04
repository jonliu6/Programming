package org.freecode.demo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBService {

	private static final Logger log = Logger.getLogger(DBService.class.getName());
	
	private Connection connection;
	
	public String testDBConn() throws SQLException, NamingException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = null;
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("jdbc/po"); // DataSource, java:/jdbc/po, configured in application container
        connection = ds.getConnection();
		
		if (connection != null) {
			ps = connection.prepareStatement("SELECT TO_CHAR(SYSDATE,'yyyy-mm-dd hh24:mi') AS CURR_TIME FROM DUAL");
			if (ps != null) {
				rs = ps.executeQuery();
				if (rs != null) {
					rs.next();
					result = rs.getString("CURR_TIME");
				}
				else {
					log.warning("testDBConn(): ResultSet is null");
				}
			}
			else {
				log.warning("testDBConn(): PreparedStatement is null");
			}
		}
		else {
			log.severe("testDBConn(): Connection injection failed");
		}
		
		return result;
	}
}
