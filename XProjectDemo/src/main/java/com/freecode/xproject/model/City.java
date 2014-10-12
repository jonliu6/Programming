package com.freecode.xproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name="CityEntity")
@NamedQueries( {@NamedQuery(name="findCities", query="SELECT c FROM CityEntity c ORDER BY c.theCountryName, c.theName"),
		        @NamedQuery(name="City.findAllNames", query="SELECT c.theName FROM CityEntity c ORDER BY c.theName") } )
//the specifying columns may cause issues when get attributes from JSF, especially the ID column name mismatches with getter and setter.
//@NamedQuery(name="findCities", query="SELECT c.theCountryName, c.theName, c.theID FROM CityEntity c ORDER BY c.theCountryName, c.theName")
@Table(name="xcity")
public class City {
	@Id
	@Column(name="CityId", nullable=false)
	private String theID;
	
	@Column(name="CityName")
	private String theName;
	
	@Column(name="CountryName")
	private String theCountryName;
	
//	private String theProvinceCode; -- has to be in table
	
	// for Resource, you need a default constructor
	public City()
	{
		
	}
	
	public City(String anID, String aName, String aCountryName, String aProvinceCode )
	{
		theID = anID;
		theName = aName;
		theCountryName = aCountryName;
//		theProvinceCode = aProvinceCode;
	}
	
	public String getID() {
		return theID;
	}
	public void setID(String anID) {
		this.theID = anID;
	}
	public String getName() {
		return theName;
	}
	public void setName(String aName) {
		this.theName = aName;
	}
	public String getCountryName() {
		return theCountryName;
	}
	public void setCountryName(String aCountryName) {
		this.theCountryName = aCountryName;
	}
//	public String getProvinceCode() {
//		return theProvinceCode;
//	}
//	public void setProvinceCode(String aProvinceCode) {
//		this.theProvinceCode = aProvinceCode;
//	}
	
	public void printOutCity()
	{
		System.out.println("City ID:" + theID + 
				           " \nCity Name:" + theName + 
				           "\nCountry Name:" + theCountryName );
	}
}
