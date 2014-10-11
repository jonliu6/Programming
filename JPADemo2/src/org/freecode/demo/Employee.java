package org.freecode.demo;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name="Employee")
@Table(name="Employee")
@NamedQueries({@NamedQuery(name="findAllEmployees", query="SELECT e FROM Employee e")})
public class Employee implements Serializable {
	
	@Id
	@Column(name="Employee_Id", nullable=false)
    public String employeeID;
    
	@Column(name="Service_In_Years", nullable=true)
    public Integer serviceInYears;
    
	@Column(name="Start_Date", nullable=true)
    public Date startDate;
  
}

