package org.freecode.demo;

import java.util.List;

import org.freecode.demo.model.City;
import org.freecode.demo.model.CityDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
    	CityDAO cd = context.getBean("cityDAO", CityDAO.class);
    	List<City> cities = cd.findCities();
    	
    	for (City c : cities) {
    		if (c != null) {
    			System.out.println(c);
    		}
    	}
    }
}
