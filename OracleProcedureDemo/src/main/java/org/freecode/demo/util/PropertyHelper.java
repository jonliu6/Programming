package org.freecode.demo.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * class to process application properties
 */
public class PropertyHelper {

	private Properties appProps = null;
	private String propertyFileName = null;
	
	public PropertyHelper(String fileName) {
		propertyFileName = fileName;
		appProps = getProperties();
	}
	
	public Properties getProperties() {
		if (appProps == null) {
			appProps = loadProperties(propertyFileName);
		}
		return appProps;
	}
	
	private Properties loadProperties(String propFileName) {
		InputStream is = null;
		try {
        	is = new FileInputStream(propFileName);
        	appProps = new Properties();
        	appProps.load(is);
        }
        catch (Exception ex ) {
        	ex.printStackTrace();
        }
        finally {
        	if (is != null) {
        		try {
        			is.close();
        		}
        		catch (IOException ioe2) {
        			ioe2.printStackTrace();
        		}
        	}
        	
        }
		
		return appProps;
	}
    
    public String getProperty(String key) {
    	String value = null;
    	if (appProps != null && key != null) {
    		value = appProps.getProperty(key);
    	}
    	
    	return value;
    }
}
