package org.freecode.demo.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// @XmlRootElement(name="Employee")
// @XmlAccessorType(XmlAccessType.NONE)
public class Employee {
	
	// @XmlElement(name="employeeId")
	private String employeeId;
	// @XmlElement(name="yearInService")
	private Integer yearInService;
	// @XmlElement(name="startOn")
	private Date startOn;
	
	public Employee()
	{
		
	}
	
	public Employee(String id, Integer years, Date startDay)
	{
		employeeId = id;
		yearInService = years;
		startOn = startDay;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getYearInService() {
		return yearInService;
	}
	public void setYearInService(Integer yearInService) {
		this.yearInService = yearInService;
	}
	public Date getStartOn() {
		return startOn;
	}
	public void setStartOn(Date startOn) {
		this.startOn = startOn;
	}
	
	

}
