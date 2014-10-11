package com.freecode.xproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name="RestaurantEntity")
@NamedQuery(name="findRestaurants", query="SELECT r FROM RestaurantEntity r")
@Table(name="xrestaurant")
public class Restaurant {
	@Id
	@Column(name="RestaurantId", nullable=false)
	private String theId;
	
	@Column(name="RestaurantName")
	private String theName;
	
	@Column(name="CityId")
	private String theCityId;
	
	
	@Column(name="Address")
	private String theAddress;
	
	@Column(name="PostalCode") 
	private String thePostalCode;
	
	@Column(name="Cuisine")
	private String theCuisine;
	
	@Column(name="Website") 
	private String theWebSite;
	
	@Column(name="Description")
	private String theDescription;
	
	@Column(name="hasDetails") 
	private boolean theHasDetails;

	public String getId() {
		return theId;
	}

	public void setId(String anId) {
		this.theId = anId;
	}
	
	public String getName() {
		return theName;
	}
	
	public void setName( String aName )
	{
		theName = aName;
	}

	public String getCityId() {
		return theCityId;
	}

	public void setCityId(String aCityId) {
		theCityId = aCityId;
	}

	public String getAddress() {
		return theAddress;
	}

	public void setAddress(String anAddress) {
		theAddress = anAddress;
	}

	public String getPostalCode() {
		return thePostalCode;
	}

	public void setPostalCode(String aPostalCode) {
		thePostalCode = aPostalCode;
	}

	public String getCuisine() {
		return theCuisine;
	}

	public void setCuisine(String aCuisine) {
		theCuisine = aCuisine;
	}

	public String getWebSite() {
		return theWebSite;
	}

	public void setWebSite(String aWebSite) {
		theWebSite = aWebSite;
	}

	public String getDescription() {
		return theDescription;
	}

	public void setDescription(String aDescription) {
		theDescription = aDescription;
	}

	public boolean getHasDetails() {
		return theHasDetails;
	}

	public void setHasDetails(boolean aHasDetails) {
		theHasDetails = aHasDetails;
	}

}
