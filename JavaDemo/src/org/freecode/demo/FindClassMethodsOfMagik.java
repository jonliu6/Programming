package org.freecode.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class FindClassMethodsOfMagik {
	private Map<String, Set<String>> classMethods = null;
	private final static String MAGIK_FILE_EXTENSION = ".magik";
	private final static String METHOD_KEYWORD = "_method ";
	private final static String CLASS_METHOD_DELIMITER = ".";

	public static void main(String[] args) {
		FindClassMethodsOfMagik driver = new FindClassMethodsOfMagik();
		
		if (args == null || args.length < 1) {
			System.out.println("Usage: java FindClassMethodsOfMagik magik_file_name or folder name");
			System.exit(0);
		}
		else {
			if (args[0].indexOf(MAGIK_FILE_EXTENSION) < 0) {
				driver.generateClassMethodsOfMagikFolder(args[0]); // .\POMagik\magik421\potc_server_product\modules\potc_poweron_modules\archiver
			}
			else {
				driver.generateClassMethodsOfMagikFile(args[0]); // .\POMagik\magik421\potc_server_product\modules\potc_poweron_modules\archiver\po!archiving_task.magik
			}
			
			driver.printClassMethods();
		}
		// .\swprod\PowerOn421\product\magik\modules\core\source\data_model\po!order.magik
		// .\\POMagik\\magik421\\potc_server_product\\modules\\potc_poweron_modules\\archiver\\po!ami_data_mods.magik
		// driver.printClassMethodsOfMagikFile(".\\swprod\\PowerOn421\\product\\magik\\modules\\core\\source\\data_model\\po!order.magik");
	}
	
	public void generateClassMethodsOfMagikFolder(String folderName) {
		File folder = new File(folderName);
//		File[] files = folder.listFiles(new FilenameFilter(){
//			public boolean accept(File dir, String name) {
//				return name.toLowerCase().endsWith(MAGIK_FILE_EXTENSION);
//			}
//		});
		File[] files = folder.listFiles(new FileFilter(){
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith(MAGIK_FILE_EXTENSION);
			}
		});
		
		for (File f : files) {
			if (f != null && f.exists()) {
				// System.out.println(folderName + "\\" + f.getName());
				if (f.isDirectory()) {
					generateClassMethodsOfMagikFolder(folderName + "\\" + f.getName());
				}
				else if (f.isFile()) {
					generateClassMethodsOfMagikFile(folderName + "\\" + f.getName());
				}
			}
		}
	}
	
	public void generateClassMethodsOfMagikFile(String magikFileName) {
		List<String> lines = readTextFileToLines(magikFileName);
		for (String line : lines) {
			if (line != null) {
				int startIdx = line.toLowerCase().indexOf(METHOD_KEYWORD);
				if (startIdx > -1) {
					if (classMethods == null) {
						classMethods = new TreeMap<String, Set<String>>();
					}
					startIdx += METHOD_KEYWORD.length();
					String classMethod = line.substring(startIdx);
					String className = "";
					String methodName = "";
					int delimiterIdx = classMethod.indexOf(CLASS_METHOD_DELIMITER);
					if (delimiterIdx > -1) {
						className = classMethod.substring(0, delimiterIdx);
						methodName = classMethod.substring(delimiterIdx + CLASS_METHOD_DELIMITER.length(), classMethod.length());
					}
					else {
						methodName = classMethod; // method without a class name is not possible in Magik
					}
					Set<String> methods = null;
					if (classMethods.containsKey(className)) {
						methods = classMethods.get(className);
						if (methods.contains(methodName) == false) {
							methods.add(methodName);
						}
					}
					else {
						methods = new TreeSet<String>();
						methods.add(methodName);
						classMethods.put(className, methods);
					}
					// System.out.println(className + "," + methodName);
				}
			}
		}
	}
	
	public void printClassMethods() {
		if (classMethods != null && classMethods.size() > 0) {
			for (String className : classMethods.keySet()) {
				System.out.println(className);
				Set<String> methods = classMethods.get(className);
				if (methods != null && methods.size() > 0) {
					for (String methodName : methods) {
						System.out.println("\t" + methodName);
					}
				}
			}
		}
	}
	
	private List<String> readTextFileToLines(String fname) {
		List<String> lines = null;
		File f = null;
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(new File(fname)));
			String buffer = "";
			lines = new ArrayList<String>();
			while ((buffer = br.readLine()) != null) {
				if (buffer != null && buffer.trim().length() > 0) {
					lines.add(buffer);
				}
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return lines;
	}
}
