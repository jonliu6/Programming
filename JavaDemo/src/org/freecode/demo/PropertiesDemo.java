package org.freecode.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertiesDemo {
	
	/**
	 * a class managing the file attributes (file name and title/description) in a properties file 
	 */
	public class FileListAttribute {
		private Properties fileList;
		private String fileListFolder;
		private String fileListName;
		
		/**
		 * initialize the object with a given folder name and file name
		 * @param aFolder
		 * @param aName
		 */
		public FileListAttribute(String aFolder, String aName) {
			this.fileListFolder = aFolder;
			this.fileListName = aName;
			fileList = new Properties();
		}
		
		/**
		 * load an existing properties file into Properties
		 * @return loaded properties
		 */
		public Properties loadFileList() {
			FileReader fReader = null;
			try {
				String fullname = fileListFolder + "/" + fileListName;
				Path p = Paths.get(fullname);
				if (Files.exists(p)) {
					fReader = new FileReader(fullname);
					if (fReader != null) {
						fileList.load(fReader);
					}
				}
				else {
					fileList.clear();
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
		
		/**
		 * store the properties into a properties file
		 */
		public void storeFileList() {
			FileWriter fWriter = null;
			String filename = fileListFolder + "/" + fileListName;
			try {
				fWriter = new FileWriter(filename);
				if (fileList != null) {
					fileList.store(fWriter, " Saved as " + filename);
				}
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
		}
		
		/**
		 * check if filename exists in the properties
		 * @param name
		 * @return
		 */
		private boolean doesContain(String name) {
			if (fileList != null && fileList.containsKey(name)) {
				return true;
			}
			return false;
		}
		
		/**
		 * add file attributes into the properties 
		 * @param name
		 * @param title
		 */
		public void addIntoFileList(String name, String title) {
			if (fileList != null && name != null) {
//				if (doesContain(name)) {
//					removeFromFileList(name);
//				}
//				fileList.put(name, title);
				fileList.setProperty(name, title); // simpler than remove and then add
			}
		}
		
		/**
		 * remove the file attributes from the properties
		 * @param name
		 */
		public void removeFromFileList(String name) {
			if (fileList != null && name != null) {
				if (doesContain(name)) {
					fileList.remove(name);
				}
			}
		}

		public Properties getFileList() {
			return fileList;
		}

		public void setFileList(Properties fileList) {
			this.fileList = fileList;
		}

		public String getFileListFolder() {
			return fileListFolder;
		}

		public void setFileListFolder(String fileListFolder) {
			this.fileListFolder = fileListFolder;
		}

		public String getFileListName() {
			return fileListName;
		}

		public void setFileListName(String fileListName) {
			this.fileListName = fileListName;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Content of " + fileListFolder + "/" + fileListName + ":\n");
			Set entrySet = null;
			if (fileList != null) {
				entrySet = fileList.entrySet();
				if (entrySet != null && entrySet.size() > 0) {
					for (Iterator<Entry> it = entrySet.iterator(); it.hasNext();) {
						Entry item = (Entry) it.next();
						if (item != null) {
							sb.append(item.getKey() + ":" + item.getValue());
						}
					}
				}
			}
			
			return sb.toString();
		}
		
	}
	
	private final static String ROOT_FOLDER_NAME = "c:/Temp";
	
	public static void main(String[] args) {
		PropertiesDemo demo = new PropertiesDemo();
		FileListAttribute fl = demo.new FileListAttribute(ROOT_FOLDER_NAME, "PA123456" + ".properties");
		fl.loadFileList();
		fl.addIntoFileList("abc.xls", "test Excel Workbook 2");
		fl.addIntoFileList("123.txt", "test Text file 2");
		fl.addIntoFileList("_a2b2c3.pdf", "test PDF 2");
		fl.storeFileList();
		
		fl.loadFileList();
		fl.addIntoFileList("_x9y8z7.pdf", "test PDF 3");
		fl.removeFromFileList("123.txt");
		fl.removeFromFileList("456.txt");
		fl.storeFileList();
	}
	
}
