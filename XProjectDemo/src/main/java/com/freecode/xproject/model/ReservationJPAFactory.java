package com.freecode.xproject.model;

import java.util.ArrayList;
import java.util.Date;
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
public class ReservationJPAFactory {
	@PersistenceContext(unitName = "XProjectDemoPersistenceUnit", type = PersistenceContextType.TRANSACTION)
	private EntityManager em;
	
	public List<Reservation> findByRestaurantId( String aRestaurantId ) {
		final Query query = em.createNamedQuery("findByRestaurantId");
		List<Reservation> reservations = query.setParameter("rid", aRestaurantId).getResultList();
		if (reservations == null) {
			reservations = new ArrayList<Reservation>();
		}
		return reservations;
	}

	public Reservation updateReservation(Reservation aReservation) {
		Reservation updatedReservaton = em.merge(aReservation);
		em.flush();
		return updatedReservaton;
	}

	public void persistReservation(Reservation aReservation) {
//		em.getTransaction().begin();
		em.persist(aReservation);
		em.flush();
//		em.getTransaction().commit();
	}
	
	public void deleteReservationById(String anId)
	{
		;
	}
	
	public void deleteReservation(Reservation aReservation) {
		UserTransaction aTransaction = null;
		try {
//			aTransaction = (UserTransaction) new InitialContext()
//					.lookup("java:comp/UserTransaction");
//			if (aTransaction != null) {
//				aTransaction.begin();
//			em.find(Restaurant.class, arg1);
//			    em.detach(  );
				em.remove(em.merge(aReservation));
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

	public Reservation makeaReservation(String restaurantId,
			Date aStart, Date anEnd, String reservedBy) {
		Reservation aReservation = new Reservation();
		aReservation.setRestaurantId( restaurantId);
		aReservation.setStart( aStart );
		aReservation.setEnd( anEnd );
		aReservation.setReservedBy(reservedBy);
//		Restaurant aRestaurant = new Restaurant();
//		aRestaurant.setCityId(cityId);
//		aRestaurant.setId(restaurantId);
//		aRestaurant.setAddress(address);
		return aReservation;
	}
}
