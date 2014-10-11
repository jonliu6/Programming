package org.freecode.demo;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EmployeeFactoryTest {

	public static void main(String[] args)
	{
		Properties p = new Properties();
		
		EmployeeFactory empFactory = null;
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		p.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		p.put(Context.PROVIDER_URL,"remote://localhost:4447"); 
		Context context = null;
		try
		{
			context = new InitialContext( p );
			
			empFactory = (EmployeeFactory) context.lookup("java:app/JPADemo2.jar/EmployeeFactoryImpl!org.freecode.demo.EmployeeFactory");
		}
		catch (NamingException ne)
		{
			ne.printStackTrace();
		}
		
		List<Employee> employees = empFactory.findAllEmployees();
		System.out.println( employees.size() );
	}
}
