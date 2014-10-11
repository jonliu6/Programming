package com.freecode.xproject.model;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CityJPAFactory {
	@PersistenceContext(unitName = "XProjectDemoPersistenceUnit", type = PersistenceContextType.TRANSACTION)
	private EntityManager em;

	public List<City> findCities() {
		// final Query query =
		// em.createQuery("SELECT c.theCountryName, c.theName, c.theID FROM CityEntity c ORDER BY c.theCountryName, c.theName");
		final Query query = em.createNamedQuery("findCities");
		List<City> cities = query.getResultList();
		if (cities == null) {
			cities = new ArrayList<City>();
		}
		return cities;
	}

	public List<String> findCityNames() {
      final Query query = em.createNamedQuery( "City.findAllNames" );
      List<String> names = query.getResultList();
      if ( names == null)
      {
    	  names = new ArrayList<String>();
      }
      
      return names;
	}

	public City editCity(City aCity) {
		return em.merge(aCity);
	}
}
