package org.freecode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="CityEntity")
@Table(name="xcity")
public class CityEntity {
	@Id
	@Column(name="CityId", nullable=false)
	private String Id;
	
	@Column(name="CityName")
	private String cityName;
	
	@Column(name="CountryName")
	private String countryName;

	public String getCityId() {
		return Id;
	}

	public void setCityId(String anId) {
		this.Id = anId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String aName) {
		this.cityName = aName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String aCountryName) {
		this.countryName = aCountryName;
	}

}
