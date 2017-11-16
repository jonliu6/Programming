package org.freecode.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.freecode.demo.model.City;
import org.freecode.demo.model.CityDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

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
    	try {
    		System.out.println(cd.findCityByName("UnknownCity"));
    	}
    	catch (DataAccessException dae) {
    		System.out.println(dae.getClass() + " caught: \n" + dae.getMessage());
    	}
    	
    	System.out.println("Sample 7: batch adding cities");
    	cities = new ArrayList<City>();
    	cid = rand.nextInt(10000);
    	City c1 = new City("C" + cid, "BigCity", "China");
    	cities.add(c1);
    	City c2 = new City("C" + cid + 1, "SmallCity", "China");
    	cities.add(c2);
    	City c3 = new City("C" + cid, "BigCity 2", "China");
    	cities.add(c3);
    	int[] numAdded = cd.batchAddCities(cities);
    	System.out.println(numAdded.length + " cities added.");
    	
    	((ClassPathXmlApplicationContext) context).close();
    }
}
