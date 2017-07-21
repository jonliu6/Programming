package org.freecode.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.freecode.demo.util.FileUploadHelper;

@ManagedBean(name="uploadBean")
@SessionScoped
public class UploadBean {

	private Part uploadedFile;
	private FileUploadHelper uploader = null;
	
	@ManagedProperty("#{msg}")
	private ResourceBundle resourceBundle; 
    private String recordId;
	
	@PostConstruct
	public void init() {
		uploader = new FileUploadHelper();
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
	
	public void upload() {
		if (uploadedFile != null) {
			InputStream is = null;
			try {
				is = uploadedFile.getInputStream();
				StringBuilder tgtFileName = new StringBuilder();
				tgtFileName.append(getPropertyValue("file.upload.location")).append(File.separator);
				if (recordId != null ) {
					tgtFileName.append(recordId).append(File.separator);
				}
                tgtFileName.append(uploader.getFileName(uploadedFile));
				uploader.saveInputStream(is, tgtFileName.toString());
//				os = new FileOutputStream(tgtFileName);
//				uploader.copyFileStreamB(is, os);
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
				FacesContext.getCurrentInstance().addMessage("IOException caught", new FacesMessage(FacesMessage.SEVERITY_ERROR, "IOException caught", "File IO problem."));
			}
		}
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
