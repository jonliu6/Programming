package org.freecode.demo.client;

import org.freecode.demo.model.Employee;
import org.springframework.web.client.RestTemplate;

public class RestClient {

	public static void main(String[] args)
	{
		// RestClient restCli = new RestClient();
		RestTemplate temp = new RestTemplate();
		Employee emp = temp.getForObject("http://localhost:8080/JAXRS/rest/employee/0002", Employee.class );
		
		System.out.print("Employee: " + emp.getEmployeeId() + " started on " + emp.getStartOn() + " for " + emp.getYearInService() + " years.");
	}
}
