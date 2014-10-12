package com.freecode.xproject.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import com.freecode.xproject.controller.CityBean;
import com.freecode.xproject.model.City;

@Path("/city")
public class CityResource {

	@GET
	@Path("{cityid}")
	// @Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	// not sure why Response didn't work on Generics even using GenericEntity
	// or alternatively, use JResponse below but always return JSON
	// @PathParame, @QueryParam, @FormParam
	public Response getCityById(@PathParam("cityid") String cityId) {
		CityBean factory = new CityBean();
		City aCity = factory.findCityById(cityId);
		return Response.ok((City) aCity).build();
	}

	// cannot use Response because of Generic
	@GET
	// @Path("/allcities")
	@Produces(MediaType.APPLICATION_XML)
	public List<City> getAllCities() {
		CityBean factory = new CityBean();
		List<City> allcities = factory.findAllCities();
		return allcities;
	}

	@POST
	// @Path("{cityid}/{cityname}/{countryname}")
	// URL:
	// http://localhost:8989/XProjectDemo/rest/city?cityid=abc&cityname=AppleBeeCity&countryname=Faraway
	@Produces(MediaType.APPLICATION_XML)
	public Response addCity(@QueryParam("cityid") String cityId,
			@QueryParam("cityname") String cityName,
			@QueryParam("countryname") String countryName) {
		CityBean factory = new CityBean();
		City aCity = new City();
		aCity.setID(cityId);
		aCity.setName(cityName);
		aCity.setCountryName(countryName);
		factory.persistCity(aCity);
		return Response.ok((City) aCity).build();
	}

	@DELETE
	@Path("{cityid}")
	public void deleteCity(@PathParam("cityid") String cityId) {
		CityBean factory = new CityBean();
		factory.removeCityById(cityId);
	}

	@PUT
	@Path("{cityid}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateCity(@PathParam("cityid") String cityId,
			JAXBElement<City> jaxbCity) {
		City aCity = jaxbCity.getValue();
		Response resp = null;
		CityBean factory = new CityBean();
		City newCity = factory.updateCity(aCity);
		if (newCity == null) {
			resp = Response.noContent().build();
		} else {
			resp = Response.ok((City) newCity).build();
		}

		return resp;
	}

}
