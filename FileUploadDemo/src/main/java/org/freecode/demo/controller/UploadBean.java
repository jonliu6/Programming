package org.freecode.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.internet.ContentType;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.freecode.demo.util.FileUploadHelper;

@ManagedBean(name="uploadBean")
@SessionScoped
public class UploadBean {
	
	private Part uploadedFile;
	private FileUploadHelper uploader = null;
	private List<String> fileNames;
	private String rootFolderName = null;
	
	@ManagedProperty("#{msg}")
	private ResourceBundle resourceBundle; 
    private String recordId;
	
	@PostConstruct
	public void init() {
		uploader = new FileUploadHelper();
	}
	
	public String getRootFolderName() {
		if (rootFolderName == null || rootFolderName.length() < 1) {
			rootFolderName = getPropertyValue(FileUploadHelper.ROOT_FOLDER_KEY);
		}
		
		return rootFolderName;
	}
	
	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}
	
	public ResourceBundle getResourceBundle() {
		return this.resourceBundle;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public List<String> getFileNames() {
		// String rootPathName = getPropertyValue("file.upload.location");
		if (getRootFolderName() != null && recordId != null) {
			fileNames = uploader.listFileNameByPath(getRootFolderName() + File.separator + recordId);
		}
	
		return fileNames;
	}

	/**
	 * method for uploading a given file
	 */
	public void upload() {
		if (uploadedFile != null) {
			InputStream is = null;
			try {
				is = uploadedFile.getInputStream();
				StringBuilder tgtFileName = new StringBuilder();
				tgtFileName.append(getPropertyValue(FileUploadHelper.ROOT_FOLDER_KEY)).append(File.separator);
				if (recordId != null ) {
					tgtFileName.append(recordId).append(File.separator);
				}
				String fileName = uploader.getFileName(uploadedFile);
                tgtFileName.append(fileName);
                
                if (doesFileNameExist(tgtFileName.toString())) {
                	// The message here will not display on the screen because of the rendering may be done already; the only possible solution could be add a phase listener to do something after the response phase
                	FacesContext.getCurrentInstance().addMessage("File exists", new FacesMessage(FacesMessage.SEVERITY_WARN, "File exists", "File " + fileName + " is overwritten."));
                }
                
                uploader.saveInputStream(is, tgtFileName.toString());
//    				os = new FileOutputStream(tgtFileName);
//    				uploader.copyFileStreamB(is, os);
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
				FacesContext.getCurrentInstance().addMessage("IOException caught", new FacesMessage(FacesMessage.SEVERITY_ERROR, "IOException caught", "File IO problem."));
			}
		}
	}
	
	public void download(String fileName) {
		if (getRootFolderName() != null && recordId != null && fileName != null) {
			String fullFileName = getRootFolderName() + File.separator + recordId + File.separator + fileName;
			Path fullPath = Paths.get(fullFileName);
			if (Files.exists(fullPath) && Files.isDirectory(fullPath) == false ) { // only downloading when the actual file exists 
				// System.out.println("Downloading " + fullFileName);
				final FacesContext fc = FacesContext.getCurrentInstance();
				final ExternalContext ec = fc.getExternalContext();
				final File srcFile = new File(fullFileName);
				
				HttpServletResponse resp = (HttpServletResponse) ec.getResponse();
				resp.setContentType("application/octet-stream");
				resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
				
				try {
				    uploader.loadFileToOutputStream(srcFile, resp.getOutputStream());
				}
				catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
					FacesContext.getCurrentInstance().addMessage("FileNotFoundException caught", new FacesMessage(FacesMessage.SEVERITY_ERROR, "FileNotFoundException caught", "File " + fileName + " not found."));
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
					FacesContext.getCurrentInstance().addMessage("IOException caught", new FacesMessage(FacesMessage.SEVERITY_ERROR, "IOException caught", "File " + fileName + " had an IO problem."));
				}
				finally {
					fc.responseComplete();
				}
				
			}
		}
	}
	
	/**
	 * check if a given file name with path exists
	 * @param fileFullName
	 * @return
	 */
	private Boolean doesFileNameExist(String fileFullName) {
		Boolean doesExist = Boolean.FALSE;
		Path path = Paths.get(fileFullName);
		if (Files.exists(path) && ! Files.isDirectory(path)) {
			doesExist = Boolean.TRUE;
		}
		
		return doesExist;
	}
	
	private String getPropertyValue(String key) {
		String val = null;
		if (resourceBundle != null) {
			val = resourceBundle.getString(key);
		}
				
		return val;
	}
	
//	private ResourceBundle getResourceBundle() {
//		FacesContext fc = FacesContext.getCurrentInstance();
//		if (fc != null) {
//			Application app = fc.getApplication();
//			if (app != null) {
//				String bundleName = app.getMessageBundle();
//				// res = app.getResourceBundle(fc, bundleName);
//				res = ResourceBundle.getBundle(bundleName);
//			}
//		}
//		return res;
//	}
}
