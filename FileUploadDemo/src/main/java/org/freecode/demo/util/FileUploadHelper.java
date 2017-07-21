package org.freecode.demo.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.Part;

public class FileUploadHelper {
	private int bufferSize = 8192;
	
	public String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    // System.out.printf ("Part Header = %s", partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	        	String fullName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        	if (fullName.indexOf("\\") > -1) {
	        		Path p = Paths.get(fullName);
	        		return p.getFileName().toString();
	        	} else {
	        		return fullName;
	        	}
	        }
	    }
	    
	    return null;
	}
	
	public void saveInputStream(InputStream is, String fullFileName) throws IOException {
		if (fullFileName != null && is != null) {
			Path fullPath = Paths.get(fullFileName);
			if (fullPath != null) {
				Path parentPath = fullPath.getParent();
				if (! Files.exists(parentPath)) {
					Files.createDirectories(parentPath);
				}
				
				FileOutputStream os = new FileOutputStream(fullPath.toString());
				copyFileStreamB(is, os);
			}
		}
	}

	private void copyFileStreamB(InputStream is, OutputStream os) {
		if (is != null && os != null && bufferSize > 0) {
			final byte[] buffer = new byte[bufferSize];
			int numOfBytes = 0;
			try {
				while ((numOfBytes = is.read(buffer)) != -1) {
					os.write(buffer, 0, numOfBytes);
				}
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			finally {
				if (os != null) {
					try {
						os.close();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					finally {
						os = null;
					}
				}
				if (is != null) {
					try {
						is.close();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					finally {
						is = null;
					}
				}
			}
		}
	}
}
