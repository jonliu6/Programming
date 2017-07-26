package org.freecode.demo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value="dbService")
@RequestScoped
public class DBService {
	
	@Inject
	private Connection conn;
	
	public String findSystemTime() throws SQLException {
		String val = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ps = conn.prepareStatement("SELECT TO_CHAR(SYSDATE,'yyyy-mm-dd hh24:mi') AS CURR_TIME FROM DUAL");
		rs = ps.executeQuery();
		if (rs != null) {
			rs.next();
			val = rs.getString("CURR_TIME");
		}
		
		return val;
	}

}
