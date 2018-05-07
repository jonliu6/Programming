package org.freecode.demo.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository("restaurantDao")
public class RestaurantDAO {

	@PersistenceContext(name="xprojdb")
	private EntityManager em;
	
	public List<Restaurant> findAllRestaurants() {
		CriteriaQuery<Restaurant> cq = em.getCriteriaBuilder().createQuery(Restaurant.class);
		Root<Restaurant> root = cq.from(Restaurant.class);
		return em.createQuery(cq).getResultList();
	}
}
