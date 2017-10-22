package org.freecode.demo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

// use configuration in web.xml, so the parameters can be changed easily thru build and package cycle
// @WebServlet(name="FileUploadServlet", urlPatterns="/uploadFile")
// @MultipartConfig(maxFileSize=25000000, maxRequestSize=30000000)
/**
 * can use configuration in web.xml descriptor as well
 * <multipart-config>
 *     <location>c:/Temp</location>
 *     <file-size-threshold>1048576</file-size-threshold>
 *     <max-file-size>2097152</max-file-size>
 *     <max-request-size>4194304</max-request-size>
 * </multipart-config>
 */
public class FileUploadServlet extends HttpServlet{

	private static final long serialVersionUID = -1114715443324524124L;
	private static final int BUFFER_SIZE = 8192;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		// Create path components to save the file
	    final String path = req.getParameter("destination");
	    final Part filePart = req.getPart("file");
	    final String fileName = getFileName(filePart);

	    OutputStream out = null;
	    InputStream filecontent = null;
	    final PrintWriter writer = resp.getWriter();

	    try {
	        out = new FileOutputStream(new File(path + File.separator +fileName));
	        filecontent = filePart.getInputStream();

//	        int read = 0;
//	        final byte[] bytes = new byte[4096];
//
//	        while ((read = filecontent.read(bytes)) != -1) {
//	            out.write(bytes, 0, read);
//	        }
	        copyFileStreamB(filecontent, out, BUFFER_SIZE);
	        
	        writer.println("New file " + fileName + " created at " + path);
	        System.out.printf( "File %s being uploaded to %s ", fileName, path);
	    }
	    catch (FileNotFoundException fne) {
	        writer.println("You either did not specify a file to upload or are "
	                + "trying to upload a file to a protected or nonexistent "
	                + "location.");
	        writer.println("<br/> ERROR: " + fne.getMessage());

	        System.out.printf("Problems during file upload. Error: %s", fne.getMessage());
	    } 
	    finally {
//	        if (out != null) {
//	            out.close();
//	        }
//	        if (filecontent != null) {
//	            filecontent.close();
//	        }
	        if (writer != null) {
	        	try {
	        		writer.close();
	        	}
	        	catch (Exception ex) {
	        		ex.printStackTrace();
	        	}
	        }
	    }
	}
	
	private void copyFileStreamB(InputStream is, OutputStream os, int bufferSize) {
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

	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    System.out.printf ("Part Header = %s", partHeader);
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
}
