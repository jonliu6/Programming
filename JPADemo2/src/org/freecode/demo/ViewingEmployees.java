package org.freecode.demo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Persistence;

@Stateless
public class ViewingEmployees {
	
	private List<Employee> allEmployees = null;
	@EJB
	EmployeeFactory empFactory;
	
//	private EmployeeFactory getEmployeeFactory()
//	{
//		empFactory = new EmployeeFactory();
//		return empFactory;
//	}
	
	public List<Employee> getAllEmployees()
	{
		if ( allEmployees == null )
		{
			allEmployees = empFactory.findAllEmployees();
		}
		
		return allEmployees;
	}
	
}

