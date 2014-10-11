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
import javax.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RestaurantJPAFactory {
	@PersistenceContext(unitName = "XProjectDemoPersistenceUnit", type = PersistenceContextType.TRANSACTION)
	private EntityManager em;

	public List<Restaurant> findRestaurants() {
		// final Query query =
		// em.createQuery("SELECT c.theCountryName, c.theName, c.theID FROM CityEntity c ORDER BY c.theCountryName, c.theName");
		final Query query = em.createNamedQuery("findRestaurants");
		List<Restaurant> restaurants = query.getResultList();
		if (restaurants == null) {
			restaurants = new ArrayList<Restaurant>();
		}
		return restaurants;
	}

	public Restaurant updateRestaurant(Restaurant aRestaurant) {
		Restaurant updatedRestaurant = em.merge(aRestaurant);
		em.flush();
		return updatedRestaurant;
	}

	public void persistRestaurant(Restaurant aRestaurant) {
//		em.getTransaction().begin();
		em.persist(aRestaurant);
		em.flush();
//		em.getTransaction().commit();
	}
	
	public void deleteRestaurantById(String anId)
	{
		;
	}
	
	public void deleteRestaurant(Restaurant aRestaurant) {
		UserTransaction aTransaction = null;
		try {
//			aTransaction = (UserTransaction) new InitialContext()
//					.lookup("java:comp/UserTransaction");
//			if (aTransaction != null) {
//				aTransaction.begin();
//			em.find(Restaurant.class, arg1);
//			    em.detach( aRestaurant );
				em.remove(em.merge(aRestaurant));
				em.flush();
//				aTransaction.commit();
//			}
			// em.getTransaction().begin();
			// em.getTransaction().commit();

//		} catch (NamingException ne) {
//			ne.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Restaurant createRestaurant(String cityId, String restaurantId,
			String address) {
		Restaurant aRestaurant = new Restaurant();
		aRestaurant.setCityId(cityId);
		aRestaurant.setId(restaurantId);
		aRestaurant.setAddress(address);
		return aRestaurant;
	}
}
