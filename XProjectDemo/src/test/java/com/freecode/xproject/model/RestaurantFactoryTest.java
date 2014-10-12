package com.freecode.xproject.model;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RestaurantFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateRestaurant()
	{
		RestaurantJPAFactory factory = new RestaurantJPAFactory();
		Restaurant aRestaurant = factory.createRestaurant("abc", "r001", "xxx Street, abc");
		Assert.assertNotNull( aRestaurant );
		System.out.println(aRestaurant.getAddress());
	}
}
