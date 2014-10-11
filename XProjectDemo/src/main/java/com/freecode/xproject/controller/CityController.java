package com.freecode.xproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.freecode.xproject.model.City;

public class CityController extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		CityBean cityFactory = new CityBean();
		List<City> cities = cityFactory.findAllCities();

		try {
			if (cities != null && cities.size() > 0) {
				for (City aCity : cities) {
					response.getWriter().println(
							aCity.getID() + " " + aCity.getName() + "<br />");
				}
			} else {
				response.getWriter().println(" no cities found.");
			}
		} catch (IOException ioe) {
			throw new RuntimeException("IOException caught when doGet()");
		}

	}
}
