package org.freecode.demo.controller;

import java.io.Serializable;

// import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.freecode.demo.model.DBService;

// @RequestScoped
@SessionScoped
@ManagedBean(name="testBean")
public class TestManagedBean implements Serializable{
	
	@Inject
	private DBService dbs;
	private String systemTime;

	public String getSystemTime() {
		if (systemTime == null) {
			try {
				systemTime = dbs.findSystemTime();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				FacesContext.getCurrentInstance().addMessage("getSystemTime", new FacesMessage("Error was caught when retrieving the system time."));
			}
			
		}
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}
	
}
