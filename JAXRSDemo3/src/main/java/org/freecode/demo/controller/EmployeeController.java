package org.freecode.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.freecode.demo.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

// Spring 4.0 to replace Controller annotation
//@RestController
// Spring MVC Controller
@Controller
// @RequestMapping("/employee")
public class EmployeeController {

//	@RequestMapping(method=RequestMethod.GET, value="/employee", headers="Accept=*/*", produces="application/json")
	@RequestMapping(method=RequestMethod.GET, value="/employee")
	public @ResponseBody Collection<Employee> findAllEmployees()
//	public ModelAndView findAllEmployees()
	{
		Collection<Employee> employees = generateDefaultEmployees();
		return employees;
//		return new ModelAndView("employee", "employee", employees);
	}
	
	// @RequestMapping(method=RequestMethod.GET, value="/employee/{id}", produces={"text/xml", "application/json"})
	// by default, the Employee object returns as XML because of @XmlRootElement. Use produces="application/json" for JSON or comment out @XmlRootElement
	@RequestMapping(method=RequestMethod.GET, value="/employee/{id}")
	@ResponseBody
	public Employee findEmployeeById( @PathVariable String id )
	{
		Collection<Employee> employees = generateDefaultEmployees();
		Employee found = null;
		for ( Iterator<Employee> it = employees.iterator(); it.hasNext(); )
		{
			Employee temp = it.next();
			if ( id.equalsIgnoreCase( temp.getEmployeeId() ) )
			{
				found = temp;
				break;
			}
		}
		
		return found;
	}
	
	private Collection<Employee> generateDefaultEmployees()
	{
		Collection<Employee> employees = new ArrayList<Employee>();
		Calendar cal = new GregorianCalendar(2010, Calendar.MONTH, 1);
		Employee emp1 = new Employee("0001", 4, cal.getTime());
		employees.add(emp1);
		
		cal = new GregorianCalendar(2012, Calendar.MONTH, 10);
		Employee emp2 = new Employee("0002", 2, cal.getTime());
		employees.add(emp2);
		
		cal = new GregorianCalendar(2011, Calendar.MONTH, 8);
		Employee emp3 = new Employee("0003", 3, cal.getTime());
		employees.add(emp3);
		
		return employees;
	}
}
