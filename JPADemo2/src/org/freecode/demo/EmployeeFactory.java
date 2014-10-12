package org.freecode.demo;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;

@Local
public interface EmployeeFactory {
	public List<Employee> findAllEmployees() throws EJBException;
}
