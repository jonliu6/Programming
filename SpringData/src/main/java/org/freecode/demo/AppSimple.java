package org.freecode.demo;

import java.util.Random;

import org.freecode.demo.model.City;
import org.freecode.demo.model.CityDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppSimple {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
    	CityDAO cd = context.getBean("cityDAO", CityDAO.class);
    	
    	Random rand = new Random();
    	String cId = "C" + rand.nextInt(10000);
    	City c = new City(cId, "Unknown City", "No Mans Land");
    	int num = cd.addCitySimple(c);
    	
    	System.out.println("The below city was added\n" + cd.findCityById(cId));
	}
}
