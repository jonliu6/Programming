package org.freecode.demo;

import java.util.List;

import javax.annotation.Resource;

import org.freecode.demo.model.Restaurant;
import org.freecode.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppJPA {

	@Autowired
	@Resource(name="locationService")
	private LocationService locSvc;
	
	public static void main(String[] args) {
		AppJPA app = new AppJPA();
		
		List<Restaurant> restaurants = app.findAllRestaurants();
		if (restaurants != null && restaurants.size() > 0) {
			for ( Restaurant r: restaurants ) {
				if (r != null) {
					System.out.println("Restaurant: (" + r.getId() + ") - " + r.getName());
				}
			}
		}
	}
	
	public List<Restaurant> findAllRestaurants() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // in the root of the classes folders
		locSvc = (LocationService) context.getBean("locationService"); // no need to defined the bean in applicationConfiguration, just autowired by type
		return locSvc.findAllRestaurants();
	}
}
