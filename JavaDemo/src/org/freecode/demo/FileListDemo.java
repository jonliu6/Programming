package org.freecode.demo;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;
import java.util.TreeSet;

/**
 * Use Java IO to list files/directories of a given folder
 *
 */
public class FileListDemo {
	private String rootFolder = null;
	
	public static void main(String[] args) {
		FileListDemo drv = new FileListDemo();
		if (args != null && args.length > 0) {
		    drv.setRootFolder(args[0]);	
		    Set<String> fileNames = drv.listFilesUsingIO(args[0]);
		    System.out.println("File names in " + args[0] + " are:");
		    for (String fn: fileNames) {
		    	System.out.println(fn);
		    }
		}
	}
	
	public void setRootFolder(String folderName) {
		rootFolder = folderName;
	}
	
	public Set<String> listFilesUsingIO(String folderName) {
		Set<String> fileLst = new TreeSet<String>();
		
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(folderName));
			for (Path path: stream) {
				if (Files.isDirectory(path)) {
					fileLst.add("[" + path.getFileName() + "]");
				}
				else {
					fileLst.add(path.getFileName().toString());
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return fileLst;
	}
	
	public Set<String> listFilesUsingWalk(String folderName){
		final Set<String> fileLst = new TreeSet<String>();
		
		try {
			Files.walkFileTree(Paths.get(folderName), new SimpleFileVisitor<Path>(){
				
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
					fileLst.add("[" + dir.toString() + "]");
					return FileVisitResult.CONTINUE;
				}
				
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
					fileLst.add(file.getFileName().toString());
					return FileVisitResult.CONTINUE;
				}
			});
		}
		catch( Exception ex) {
			ex.printStackTrace();
		}
		
		return fileLst;
	}
}
