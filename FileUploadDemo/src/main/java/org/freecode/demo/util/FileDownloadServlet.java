package org.freecode.demo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="FileDownloadServlet", urlPatterns="/downloadFile")
public class FileDownloadServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String recordId = req.getParameter("recordId");
		String fileName = req.getParameter("fileName");
		
		if (recordId != null && fileName != null && getRootFolderName() != null) {
			FileUploadHelper downloader = new FileUploadHelper();
			String fullFileName = getRootFolderName() + File.separator + recordId + File.separator + fileName;
			Path fullPath = Paths.get(fullFileName);
			if (Files.exists(fullPath) && Files.isDirectory(fullPath) == false ) { // only downloading when the actual file exists 
				final File srcFile = new File(fullFileName);
				
				resp.setContentType("application/octet-stream");
				resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
				
				try {
				    downloader.loadFileToOutputStream(srcFile, resp.getOutputStream());
				}
				catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
				finally {
					resp.flushBuffer();
				}
				
			}
		}
	}
	
	private String getRootFolderName() {
		Properties p = new Properties();
		String rootFolderName = null;
		try {
			p.load(getServletContext().getResourceAsStream("/WEB-INF/classes/messages.properties"));
			rootFolderName = p.getProperty(FileUploadHelper.ROOT_FOLDER_KEY);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return rootFolderName;
	}
}
