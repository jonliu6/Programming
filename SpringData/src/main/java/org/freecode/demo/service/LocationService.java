package org.freecode.demo.service;

import java.util.List;

import org.freecode.demo.model.Restaurant;
import org.freecode.demo.model.RestaurantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("locationService")
public class LocationService {

	@Autowired
	private RestaurantDAO restDao;
	
	public List<Restaurant> findAllRestaurants() {
		return restDao.findAllRestaurants();
	}
}
