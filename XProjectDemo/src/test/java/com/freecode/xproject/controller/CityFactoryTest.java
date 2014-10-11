package com.freecode.xproject.controller;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;

import com.freecode.xproject.model.City;

public class CityFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test //cannot test because outside Glassfish Web Container
	public void testPersistCity() {
		City aCity = new City();
		aCity.setID("abc00001");
		aCity.setName("Apple Bee City");
		aCity.setCountryName("Faraway");
		City persistedCity = null;

		CityBean factory = new CityBean();
		try {
			persistedCity = factory.persistCity(aCity);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Assert.assertNotNull(persistedCity);
		Assert.assertEquals(aCity.getID(), persistedCity.getID());
		Assert.assertEquals(aCity.getName(), persistedCity.getName());
		Assert.assertEquals(aCity.getCountryName(),
				persistedCity.getCountryName());
	}
}
