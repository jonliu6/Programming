package org.freecode.demo.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.freecode.demo.model.DBService;

public class TestBean implements Serializable {
	
	private static final Logger log = Logger.getLogger(TestBean.class.getName());

	private DBService dbs;
	
	public TestBean() {
		dbs = new DBService();
	}
	
	public String testDB() {
		String val = null;
		try {
			if (dbs != null) {
				val = dbs.testDBConn();
			}
			else {
				log.severe("testDB(): DBService injection failed");
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (NamingException ne) {
			ne.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return val;
	}
}
