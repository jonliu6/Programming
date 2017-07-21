package org.freecode.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // System.out.println( "Hello World!" );
    	System.out.println("OPTION 1: ");
    	File folder = new File("c:/Temp");
    	File[] files = folder.listFiles();
    	for (int i=0, len=files.length; i<len; i++) {
    		File f = files[i];
    		if (f != null) {
    			System.out.println(f.getName());
    		}
    	}
    	
    	System.out.println("OPTION 2: ");
    	Path path = Paths.get("c:/Temp");
    	DirectoryStream<Path> stream = null;
    	try {
    		stream = Files.newDirectoryStream(path);
    		for (Path f: stream) {
    			if (f != null) {
    				System.out.println(f.getFileName());
    			}
    		}
    	}
    	catch (IOException ioe) {
    		ioe.printStackTrace();
    	}
    	
    }
}
