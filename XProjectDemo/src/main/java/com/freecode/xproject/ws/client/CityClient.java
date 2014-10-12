package com.freecode.xproject.ws.client;

import javax.ws.rs.core.MediaType;

import com.freecode.xproject.model.City;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CityClient {
	public static void main(String[] args) {
		// test retrieving all cities
		// retrieveAllCities();
		// update a city by object
		// updateCity();
		// update a city by XML string
		updateCity2();
	}

	public static void retrieveAllCities() {
		Client wsClient = Client.create();
		WebResource res = wsClient
				.resource("http://localhost:8989/XProjectDemo/rest/city");
		ClientResponse resp = res.get(ClientResponse.class);
		System.out.println(resp.getStatus());
		System.out.println(resp.getHeaders().get("Content-Type"));
		String entity = resp.getEntity(String.class);
		System.out.println(entity);
	}

	public static void updateCity() {
		City aCity = new City("abc", "AlphaBravoCharlie", "Delta", null);
		Client wsClient = Client.create();
		WebResource res = wsClient
				.resource("http://localhost:8989/XProjectDemo/rest/city");
		ClientResponse resp = res.path(aCity.getID())
				.accept(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, aCity);

		System.out.println(resp.getStatus());
		System.out.println(resp.getHeaders().get("Content-Type"));
		String entity = resp.getEntity(String.class);
		System.out.println(entity);
	}

	public static void updateCity2() {
		String xmlString = "<city>" + "<countryName>Fruit City</countryName>"
				+ "<ID>abc</ID>" + "<name>AppleBananaCoconut</name>"
				+ "</city>";
		Client wsClient = Client.create();
		WebResource res = wsClient
				.resource("http://localhost:8989/XProjectDemo/rest/city");
		ClientResponse resp = res.path("abc").type(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, xmlString);
		System.out.println(resp.getStatus());
		System.out.println(resp.getHeaders().get("Content-Type"));
		String entity = resp.getEntity(String.class);
		System.out.println(entity);
	}
}
