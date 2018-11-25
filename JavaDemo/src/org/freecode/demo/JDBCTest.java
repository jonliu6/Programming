package org.freecode.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTest {

	public static void main(String[] args) {
		String connStr = "jdbc:oracle:thin:@kdcpoorcd01:1521:PO42DEV";
//		Properties connProps = new Properties();
//		connProps.put("user", "poweron");
//		connProps.put("password", "poweron");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM po_bch_region_code";
		try {
			conn = DriverManager.getConnection(connStr, "poweron", "poweron");
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("Regions: ");
			while (rs.next()) {
				System.out.println(rs.getString("region_code") + "-" + rs.getString("description"));
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
			finally {
				rs = null;
				ps = null;
				conn = null;
			}
		}
	}
}
