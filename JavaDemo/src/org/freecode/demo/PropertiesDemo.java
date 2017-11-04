package org.freecode.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertiesDemo {
	private Properties fileList;
	
	public static void main(String[] args) {
		PropertiesDemo pd = new PropertiesDemo();
		pd.initializeFileList();
		pd.storeFileList(pd.getFileList(), "c:/Temp", "PA12345");
		pd.loadFileList("c:/Temp", "PA12345");
		pd.appendFileList(pd.getFileList(), "a1b2c3.xlsx", "MS Excel workbook");
		pd.printProperties(pd.getFileList());
	}
	
	public Properties getFileList() {
		return fileList;
	}
	
	public Properties initializeFileList() {
		if (fileList == null) {
			fileList = new Properties();
		}
		fileList.put("abc.jpg", "test image");
		fileList.put("xyz.pdf", "test PDF file");
		fileList.put("123.docx", "test MS Word document");
		
		return fileList;
	}
	
	public void appendFileList(Properties prop, String key, String value) {
		if (prop != null && key != null) {
			prop.put(key, value);
		}
	}

	public Properties loadFileList(String folderName, String recordId) {
		fileList = new Properties();
		FileReader fReader = null;
		
		try {
			fReader = new FileReader(folderName + "/" + recordId + ".properties");
			if (fReader != null) {
				fileList.load(fReader);
			}
		}
		catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}		
		
		return fileList;
	}
	
	public void storeFileList(Properties prop, String folderName, String recordId) {
		FileWriter fWriter = null;
		
		try {
			fWriter = new FileWriter(folderName + "/" + recordId + ".properties");
			if (prop != null) {
				prop.store(fWriter, recordId + " files");
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	public void printProperties(Properties prop) {
		Set entrySet = null;
		if (prop != null) {
			entrySet = prop.entrySet();
			if (entrySet != null && entrySet.size() > 0) {
				for (Iterator<Entry> it = entrySet.iterator(); it.hasNext();) {
					Entry item = (Entry) it.next();
					if (item != null) {
						System.out.println(item.getKey() + ":" + item.getValue());
					}
				}
			}
		}
	}
}
