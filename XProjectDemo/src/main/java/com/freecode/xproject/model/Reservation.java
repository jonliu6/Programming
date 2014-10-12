package com.freecode.xproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name="RestaurantReservationEntity")
@Table(name="xrestaurantreservation")
@NamedQuery(name="findByRestaurantId", query="SELECT rr FROM RestaurantReservationEntity rr  WHERE rr.theRestaurantId = :rid")
public class Reservation {
	@Id
	@Column(name="ReservationId", nullable=false)
	private String theId;
	
	@Column(name="RestaurantId", nullable=false)
	private String theRestaurantId;
	
	@Column(name="ReservationStart")
	@Temporal(TemporalType.TIMESTAMP)
	private Date theStart;
	
	@Column(name="ReservationEnd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date theEnd;
	
	@Column(name="NumberOfPeople")
	private Integer theNumOfPeople;
	
	@Column(name="ReservedBy")
	private String theReservedBy;
	
	@Column(name="ReservedAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date theReservedAt;
	
	@Column(name="Status")
	private String theStatus;
	
	@Column(name="Comment")
	private String theComments;
	
	public String getId() {
		return theId;
	}
	public void setId(String anId) {
		this.theId = anId;
	}
	public String getRestaurantId() {
		return theRestaurantId;
	}
	public void setRestaurantId(String aRestaurantId) {
		theRestaurantId = aRestaurantId;
	}
	public Date getStart() {
		return theStart;
	}
	public void setStart(Date aStart) {
		theStart = aStart;
	}
	public Date getEnd() {
		return theEnd;
	}
	public void setEnd(Date anEnd) {
		theEnd = anEnd;
	}
	public Integer getNumOfPeople() {
		return theNumOfPeople;
	}
	public void setNumOfPeople(Integer aNumOfPeople) {
		theNumOfPeople = aNumOfPeople;
	}
	public String getReservedBy() {
		return theReservedBy;
	}
	public void setReservedBy(String aReservedBy) {
		theReservedBy = aReservedBy;
	}
	public Date getReservedAt() {
		return theReservedAt;
	}
	public void setReservedAt(Date aReservedAt) {
		theReservedAt = aReservedAt;
	}
	public String getStatus() {
		return theStatus;
	}
	public void setStatus(String aStatus) {
		theStatus = aStatus;
	}
	public String getComments() {
		return theComments;
	}
	public void setComments(String aComments) {
		theComments = aComments;
	}

}
