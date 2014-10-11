package org.freecode.model;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.freecode.model.CityEntity;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CityFacade {
	@PersistenceContext(unitName = "EJB3DemoPersistenceUnit", type=PersistenceContextType.TRANSACTION)
	private EntityManager em;

	public List<CityEntity> findCities() {
		final Query query = em
				.createQuery("SELECT c FROM CityEntity c ORDER BY c.countryName, c.cityName");
		List<CityEntity> cities = query.getResultList();
		if (cities == null) {
			cities = new ArrayList<CityEntity>();
		}
		return cities;
	}

}
