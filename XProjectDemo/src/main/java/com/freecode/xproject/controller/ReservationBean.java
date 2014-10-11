package com.freecode.xproject.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.freecode.xproject.model.Reservation;
import com.freecode.xproject.model.ReservationJPAFactory;

@ManagedBean(name = "reservationBean")
@SessionScoped
public class ReservationBean {
	private Reservation theReservation;
	private String theReservationID;
	private String theRestaurantID;
	private final int theBatchSize = 10;
	private int first = 0;
	private int last = 0;
	private int theTotalRows = 0;
	private int rowPerPage = 0;
	private List<Reservation> theReservationList;
	private boolean sortByStartInAscending = true;
	private boolean sortByEndInAscending = true;
	private boolean sortByNumOfPeopleInAscending = true;

	@EJB
	private ReservationJPAFactory reservationFactory;

	public ReservationBean() {

	}

	@PostConstruct
	public void init() {
		theReservation = new Reservation();
		theReservation.setRestaurantId(theRestaurantID);
		findReservations(theRestaurantID);
	}

	public void load() {
		findReservations(theRestaurantID);
	}

	public Reservation getReservation() {
		return theReservation;
	}

	public void setReservation(Reservation aReservation) {
		this.theReservation = aReservation;
	}

	public String getReservationID() {
		return theReservationID;
	}

	public void setReservationID(String aReservationID) {
		this.theReservationID = aReservationID;
	}

	public String getRestaurantID() {
		return theRestaurantID;
	}

	public void setRestaurantID(String aRestaurantID) {
		this.theRestaurantID = aRestaurantID;
	}

	public List<Reservation> getReservationList() {
		return theReservationList;
	}

	public void setReservationList(List<Reservation> aReservationList) {
		this.theReservationList = aReservationList;
	}

	public List<Reservation> findReservations(String aRestaurantID) {
		theReservationList = reservationFactory
				.findByRestaurantId(aRestaurantID);
		if (theReservationList != null) {
			theTotalRows = theReservationList.size();
		}

		return theReservationList;
	}
	
//	public String getReservationStart()
//	{
//		String startString = "";
//		if ( theReservation != null && theReservation.getStart() != null )
//		{
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//			startString = df.format( theReservation.getStart() );
//		}
//		return startString;
//	}

	public String book() {
		theReservation.setReservedAt(new Date());
		reservationFactory.persistReservation(theReservation);
		findReservations(theRestaurantID); // refresh restaurant list in the
											// managed bean
		return "listReservations";
	}

	public String cancelReservation(Reservation aReservation) {
		reservationFactory.deleteReservation(aReservation);
		findReservations(aReservation.getRestaurantId()); // refresh list in the
															// managed bean
		return "listReservations.xhtml?faces-redirect=true&restaurantID="
				+ aReservation.getRestaurantId();
	}

	public TimeZone getLocalTimeZone() {
		return TimeZone.getTimeZone("America/Los_Angeles");
	}

	public void sortByStart() {
		if (sortByStartInAscending) {
			Collections.sort(theReservationList, new Comparator<Reservation>() {
				public int compare(Reservation r1, Reservation r2) {
					return r1.getStart().compareTo(r2.getStart());
				}
			});
			sortByStartInAscending = false;
		} else {
			Collections.sort(theReservationList, new Comparator<Reservation>() {
				public int compare(Reservation r1, Reservation r2) {
					return r2.getStart().compareTo(r1.getStart());
				}
			});
			sortByStartInAscending = true;
		}
	}

	public void sortByEnd() {
		if (sortByEndInAscending) {
			Collections.sort(theReservationList, new Comparator<Reservation>() {
				public int compare(Reservation r1, Reservation r2) {
					return r1.getEnd().compareTo(r2.getEnd());
				}
			});
			sortByEndInAscending = false;
		} else {
			Collections.sort(theReservationList, new Comparator<Reservation>() {
				public int compare(Reservation r1, Reservation r2) {
					return r2.getEnd().compareTo(r1.getEnd());
				}
			});
			sortByEndInAscending = true;
		}
	}

	public void sortByNumOfPeople() {
		if (sortByNumOfPeopleInAscending) {
			Collections.sort(theReservationList, new Comparator<Reservation>() {
				public int compare(Reservation r1, Reservation r2) {
					return r1.getNumOfPeople().compareTo(r2.getNumOfPeople());
				}
			});
			sortByNumOfPeopleInAscending = false;
		} else {
			Collections.sort(theReservationList, new Comparator<Reservation>() {
				public int compare(Reservation r1, Reservation r2) {
					return r2.getNumOfPeople().compareTo(r1.getNumOfPeople());
				}
			});
			sortByNumOfPeopleInAscending = true;
		}
	}
}
