package com.freecode.xproject.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.freecode.xproject.helper.MySQLHelper;
import com.freecode.xproject.model.City;
import com.freecode.xproject.model.CityJPAFactory;

@ManagedBean(name = "cityBean")
@SessionScoped
public class CityBean {
	private City theCity;
	private String theCityID;
	private final int theBatchSize = 10;
	private int first = 0;
	private int last = 0;
	private int theTotalRows = 0;
	private int rowPerPage = 0;
	private List<City> theCityList;
	private boolean sortByIdInAscending = true;
	private boolean sortByNameInAscending = true;
	private boolean sortByCountryNameInAscending = true;

	@EJB
	private CityJPAFactory cityFactory;

	public CityBean() {

	}

	@PostConstruct
	public void init() {
		theCity = new City();
		findCities();
	}

	public void load() {
		theCity = findCityById(theCityID);
	}

	public City getCity() {
		return theCity;
	}

	public void setCity(City aCity) {
		this.theCity = aCity;
	}

	public String getCityID() {
		return theCityID;
	}

	public void setCityID(String aCityID) {
		this.theCityID = aCityID;
	}

	public List<City> getCityList() {
		if ( theCityList == null )
		{
			theCityList = findCities();
		}
		if ( theCityList != null )
		{
			theTotalRows = theCityList.size();
		}
		return theCityList;
	}

	public void setCityList(List<City> aCityList) {
		theCityList = aCityList;
	}

	public String createCity() {
		persistCity(theCity);
		findCities(); // refresh city list in the managed bean
		return "listCities";
	}

	/**
	 * POJO MySQLHelp version
	 * 
	 * @return
	 */
	public String modifyCity() {
		updateCity(theCity);
		return "listCities";
	}

	/**
	 * JPA version of edit
	 * 
	 * @return
	 */
	public String editCity() {
		cityFactory.editCity(theCity);
		return "listCities";
	}

	public City persistCity(City aCity) {
		City persistedCity = null;
		MySQLHelper sqlHelper = new MySQLHelper();
		sqlHelper.openConnection();
		int rowCount = sqlHelper
				.executeUpdate("insert into xcity (CityId, CityName, CountryName) values ('"
						+ aCity.getID()
						+ "','"
						+ aCity.getName()
						+ "','"
						+ aCity.getCountryName() + "')");
		sqlHelper.closeConnection();
		sqlHelper = null;

		persistedCity = findCityById(aCity.getID());
		return persistedCity;
	}

	/**
	 * calling JPAFactory
	 * 
	 * @return
	 */
	public List<City> findCities() {
		// List<City> foundCities = cityFactory.findCities();
		theCityList = cityFactory.findCities();
		if (theCityList != null) {
			theTotalRows = theCityList.size();
		}
		return theCityList;
	}
	
	public List<String> findCityNames()
	{
		return cityFactory.findCityNames();
	}

	public List<City> findAllCities() {
		List<City> allCities = null;
		// @Todo find all cities
		MySQLHelper sqlHelper = new MySQLHelper();
		sqlHelper.openConnection();
		ResultSet rs = sqlHelper.executeQuery("select CountryName, CityName, CityID from xcity");

		allCities = buildCitiesByResultSet(rs);
		if (allCities != null) {
			theTotalRows = allCities.size();
		}
		rs = null;
		sqlHelper.closeConnection();
		sqlHelper = null;

		return allCities;
	}

	public City findCityById(String aCityId) {
		City aCity = null;
		MySQLHelper sqlHelper = new MySQLHelper();
		sqlHelper.openConnection();
		ResultSet rs = sqlHelper
				.executeQuery("select * from xcity where CityID='" + aCityId
						+ "'");
		try {
			if (rs.first()) {
				aCity = buildCityByResultSet(rs);
			}
		} catch (SQLException se) {
			throw new RuntimeException(
					"SQLException caught when findCityById()");
		}
		rs = null;
		sqlHelper.closeConnection();
		sqlHelper = null;
		return aCity;
	}

	public City updateCity(City aCity) {
		String cityId = aCity.getID();
		City oldCity = findCityById(cityId);
		if (oldCity != null) {
			MySQLHelper sqlHelper = new MySQLHelper();
			sqlHelper.openConnection();
			int rowCount = sqlHelper
					.executeUpdate("update xcity set CityName='"
							+ aCity.getName() + "', CountryName='"
							+ aCity.getCountryName() + "' where CityId='"
							+ aCity.getID() + "'");
			sqlHelper.closeConnection();
			sqlHelper = null;
		}

		City newCity = findCityById(cityId);
		return newCity;
	}

	public String dropCity(City aCity) {
		removeCityById(aCity.getID());
		findCities(); // refresh city list in the managed bean
		return "listCities";
	}

	public City removeCityById(String aCityId) {
		City oldCity = findCityById(aCityId);
		if (oldCity != null) {
			MySQLHelper sqlHelper = new MySQLHelper();
			sqlHelper.openConnection();
			sqlHelper.executeUpdate("delete from xcity where CityId = '"
					+ aCityId + "'");
			sqlHelper.closeConnection();
			sqlHelper = null;
		}

		return oldCity;
	}

	private List<City> buildCitiesByResultSet(ResultSet aResultSet) {
		List<City> allCities = null;
		int rowCount = 0;
		if (aResultSet != null) {
			try {
				// rowCount = aResultSet.getFetchSize(); // fetchSize is the one
				// configured for how many rows want to be fetched, no the size
				// of ResultSet
				// aResultSet.afterLast(); // set cursor to the last row of
				// ResultSet for calculating row count
				// rowCount = aResultSet.getRow() - 1; // get the row count
				// if (rowCount > 0) {
				allCities = new ArrayList<City>();
				aResultSet.beforeFirst(); // reset cursor to the beginning of
											// ResultSet, so the below loop will
											// work
				while (aResultSet.next()) {
					City aCity = new City();
					aCity.setID(aResultSet.getString("CityID"));
					aCity.setName(aResultSet.getString("CityName"));
					aCity.setCountryName(aResultSet.getString("CountryName"));
					allCities.add(aCity);
				}
				// }
			} catch (SQLException se) {
				throw new RuntimeException(
						"SQLException caught when buildCitiesByResultSet()");
			}
		}

		return allCities;
	}

	private City buildCityByResultSet(ResultSet aResultSet) {
		City aCity = null;
		if (aResultSet != null) {
			try {
				aResultSet.first();
				aCity = new City();
				aCity.setID(aResultSet.getString("CityID"));
				aCity.setName(aResultSet.getString("CityName"));
				aCity.setCountryName(aResultSet.getString("CountryName"));
			} catch (SQLException se) {
				throw new RuntimeException(
						"SQLException caught when buildCityByResultSet()");
			}
		}

		return aCity;
	}

	public String goFirst() {
		first = 0;
		return "listCities";
	}

	public String goPrevious() {
		if (first > theBatchSize) {
			first -= theBatchSize;
		} else {
			first = 0;
		}
		last = first + getRowPerPage();
		return "listCities";
	}

	public String goNext() {
		if (first + theBatchSize < theTotalRows) {
			first += theBatchSize;
		}
		last = first + getRowPerPage();
		return "listCities";
	}

	public String goLast() {
		int lastRemaining = theTotalRows % theBatchSize;
		int numberOfPages = theTotalRows / theBatchSize;
		if (numberOfPages > 0) {
			if (lastRemaining > 0) {
				first = theTotalRows - lastRemaining;
			} else {
				first = theTotalRows - theBatchSize;
			}
		} else {
			first = 0;
		}
		return "listCities";
	}

	public int getFirst() {
		return first;
	}

	public int getLast() {
		last = first + getRowPerPage();
		return last;
	}

	public int getRowPerPage() {
		int remainingRows = theTotalRows - first;
		if (remainingRows > theBatchSize) {
			rowPerPage = theBatchSize;
		} else {
			rowPerPage = remainingRows;
		}
		return rowPerPage;
	}

	public int getTotalRows() {
		return theTotalRows;
	}

	public int displayFirst() {
		return first + 1;
	}

	public void sortById() {
		if (sortByIdInAscending) {
			Collections.sort(theCityList, new Comparator<City>() {
				public int compare(City city1, City city2) {
					return city1.getID().compareTo(city2.getID());
				}
			});
			sortByIdInAscending = false;
		} else {
			Collections.sort(theCityList, new Comparator<City>() {
				public int compare(City city1, City city2) {
					return city2.getID().compareTo(city1.getID());
				}
			});
			sortByIdInAscending = true;
		}
	}

	public void sortByName() {
		if (sortByNameInAscending) {
			Collections.sort(theCityList, new Comparator<City>() {
				public int compare(City city1, City city2) {
					return city1.getName().compareTo(city2.getName());
				}
			});
			sortByNameInAscending = false;
		} else {
			Collections.sort(theCityList, new Comparator<City>() {
				public int compare(City city1, City city2) {
					return city2.getName().compareTo(city1.getName());
				}
			});
			sortByNameInAscending = true;
		}
	}

	public void sortByCountryName() {
		if (sortByCountryNameInAscending) {
			Collections.sort(theCityList, new Comparator<City>() {
				public int compare(City city1, City city2) {
					return city1.getCountryName().compareTo(
							city2.getCountryName());
				}
			});
			sortByCountryNameInAscending = false;
		} else {
			Collections.sort(theCityList, new Comparator<City>() {
				public int compare(City city1, City city2) {
					return city2.getCountryName().compareTo(
							city1.getCountryName());
				}
			});
			sortByCountryNameInAscending = true;
		}
	}
}
