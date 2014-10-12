package org.freecode.demo;

import java.util.List;

import javax.ejb.EJB;

public class ViewingEmployees {
	
	private List<Employee> allEmployees = null;
	
	private EmployeeFactory empFactory;
	
	private EmployeeFactory getEmployeeFactory()
	{
		empFactory = new EmployeeFactory();
		return empFactory;
	}
	
	public List<Employee> getAllEmployees()
	{
		if ( allEmployees == null )
		{
			allEmployees = getEmployeeFactory().findAllEmployees();
		}
		
		return allEmployees;
	}
	
}

