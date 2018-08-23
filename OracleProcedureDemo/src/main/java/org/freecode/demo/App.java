package org.freecode.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.freecode.demo.util.PropertyHelper;

/**
 * Hello world!
 *
 */
public class App 
{
	public final static String APP_PROPERTY_FILE_NAME = ".\\App.properties"; // for debugging purpose, you may need to set an absolute path for .properties
	
	/**
	 * Call a stored procedure
	 * one parameter:
	 *   - stored procedure name with parameter(s)
	 * @param args
	 */
    public static void main( String[] args )
    {
    	if (args == null || args.length < 1) {
    		// java -cp OracleProcedureCall.jar org.freecode.demo.App "Tmp_Prc_Fix_Rest_By_Time(TO_DATE('2018-08-01 23:59','yyyy-mm-dd hh24:mi'))"
    		System.out.println("Usage: " + App.class.getName() + " stored_procedure_name");
    		System.out.println("e.g. \n\t" + App.class.getName() + " prc_fix_me\n");
    		System.out.println("\tor" + App.class.getName() + " Tmp_Prc_Fix_Rest_By_Time(TO_DATE('2018-08-01 23:59','yyyy-mm-dd hh24:mi'))");
    		System.exit(0);
    	}
    	
    	PropertyHelper propHlp = new PropertyHelper(APP_PROPERTY_FILE_NAME);
    	String dbUrl = propHlp.getProperty("db.connectionString");
    	String dbUser = propHlp.getProperty("db.user");
    	String dbPass = propHlp.getProperty("db.password");
    	
    	Connection conn = null;
    	CallableStatement cstat = null;
    	
    	try {
    		String procSql = args[0];
    		Class.forName("oracle.jdbc.OracleDriver");
    		conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
    		cstat = conn.prepareCall("{call " + procSql + "}");
    		cstat.execute();
    	}
    	catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		try {
    			if (cstat != null) {
        			cstat.close();
        		}
        		if (conn != null) {
        			conn.close();
        		}
    		}
    		catch (Exception ex1) {
    			System.err.println("Exception caught when releasing the resources: " + ex1.getMessage());
    		}
    		finally {
    			cstat = null;
    			conn = null;
    		}
    	}
    }
}
