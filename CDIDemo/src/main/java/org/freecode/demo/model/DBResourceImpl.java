package org.freecode.demo.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

@ApplicationScoped
public class DBResourceImpl{

	@Resource(lookup="jdbc/po")
	private DataSource ds;
	
	@Produces
	@RequestScoped
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	public void closeConnection(@Disposes Connection conn) throws SQLException {
		if (conn != null && conn.isClosed() == false) {
			conn.close();
		}
	}
}
