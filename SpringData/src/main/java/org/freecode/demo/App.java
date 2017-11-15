package org.freecode.demo;

import java.util.List;
import java.util.Random;

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
    	
    	cities = cd.findCitiesByCountry("Canada");
    	System.out.println("Sample 2: list all cities of a country");
    	for (City c : cities) {
    		if (c != null) {
    			System.out.println(c);
    		}
    	}
    	
    	System.out.println("Sample 3: find a city by name");
    	System.out.println(cd.findCityByName("Vancouver"));
    	
    	System.out.println("Sample 4: add a city");
    	Random rand = new Random();
    	int cid = rand.nextInt(1000);
//    	cd.addCity("t" + cid, "UnknownCity", "Nowhere");
    	City newCity = new City("t" + cid, "UnknownCity", "Nowhere");
    	cd.addCity2(newCity);
    	System.out.println(cd.findCityByName("UnknownCity"));
    	
    	System.out.println("Sample 5: update a city");
    	newCity.setCountryName("Canada");
    	cd.updateCity(newCity);
    	System.out.println(cd.findCityByName("UnknownCity"));
    	
    	System.out.println("Sample 6: remove a city");
    	cd.removeCity(newCity.getCityId());
    	// System.out.println(cd.findCityByName("UnknownCity"));
    	
    	((ClassPathXmlApplicationContext) context).close();
    }
}
