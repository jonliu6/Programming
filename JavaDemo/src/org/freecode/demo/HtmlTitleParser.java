package org.freecode.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Usage:
 * The code looks for the title in the given files (e.g. *.html,*.xml), and write the folder name, file name and title into a Java Object (PageObject) and then into a JSON file - HtmlTitleParser.json
 * The File/Directory lookups are by Apache Common IO 2.6. File Parsing is by Regular Expressions. JSON and Object conversion is done by GSon 2.3.1.

 * to compile the Java code:
   javac -classpath ".;gson-2.3.1.jar;commons-io-2.6.jar" HtmlTitleParser.java

 * to run:
   java -classpath ".;gson-2.3.1.jar;commons-io-2.6.jar" HtmlTitleParser .\ "html,xml"
 */
@SuppressWarnings("unchecked")
public class HtmlTitleParser {

	public static void main(String[] args) {
		if (args.length < 2)
  	    { // if less than 2 arguments, print out usage asking the correct program arguments
  	  	    System.out.println("Usage: java HtmlTitleParser [Directory Name] [File Extension(s) delimited by commas]");
  	  	    System.exit(-1);
  	    }
		else {
			String dirName = args[0];
			String extensions = args[1];
			String[] extNames = extensions.split(","); // convert comma-delimited file extension String into a String array
			FileReader fr = null;
			BufferedReader br = null;
			File jsonFile = null;
			try {
				
				if (dirName != null && extNames != null) {
				
					File dir = new File(dirName);
					Collection files = FileUtils.listFiles(dir, extNames, true); // parameters of FileUtils.listFiles: directory to look up, String array of file extensions, recursive or not for sub-folders
					String line = null;
					
					StringBuilder sb = null;
					HtmlTitleParser parser = new HtmlTitleParser();
					List<PageObject> pos = new ArrayList<PageObject>();
					for (Iterator<File> it = files.iterator(); it.hasNext();) {
					
						File f = it.next();
						// System.out.println(f.getAbsolutePath());
						PageObject po = parser.new PageObject(); 
						po.folderName = f.getPath();
				    	po.fileName = f.getName();
				    	
						fr = new FileReader(f);
						br = new BufferedReader(fr);
	
						sb = new StringBuilder();
						while ((line = br.readLine()) != null) { // IO operation to put the file content (line-by-line) into StringBuilder
							sb.append(line);
						}
						sb.toString().replaceAll("\\s+", " "); // replace whitespaces
						
						Pattern p = Pattern.compile("<title>(.*?)</title>"); // Regular Expression to match the pattern of HTML Title tag for getting the value of title
					    Matcher m = p.matcher(sb.toString());
					    while (m.find() == true) { // when find a title in a file, store the attributes to a PageObject instance and then add it into an ArrayList
					    	po.title = m.group(1);
					        break;
					    }
					    pos.add(po);						
					}
					
					// Initialize GSON objects for JSON parsing and construction
					GsonBuilder gb = new GsonBuilder().serializeNulls();
					Gson gs = gb.create();
					
					jsonFile = new File(HtmlTitleParser.class.getName() + ".json"); // Create a new file for saving the JSON string
					FileUtils.writeStringToFile(jsonFile, gs.toJson(pos), Charset.defaultCharset()); // Save the JSON string into a JSON file with the default encoding - from the platform.
				}
			
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			finally { // regardless of failures, close the file objects
				try {
					if (br != null) {
						br.close();
					}
					if (fr != null) {
						fr.close();
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	class PageObject {
		private String folderName;
		private String fileName;
		private String title;
		public String getFolderName() {
			return folderName;
		}
		public void setFolderName(String folderName) {
			this.folderName = folderName;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		@Override
		public String toString() {
			return "PageObject [folderName=" + folderName + ", fileName=" + fileName + ", title=" + title + "]";
		}
		
	}
}
