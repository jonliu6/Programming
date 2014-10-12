package com.freecode.xproject.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.freecode.xproject.model.Restaurant;
import com.freecode.xproject.model.RestaurantJPAFactory;

@ManagedBean(name = "restaurantBean")
@SessionScoped
public class RestaurantBean {
	private Restaurant theRestaurant;
	private String theRestaurantID;
	private final int theBatchSize = 10;
	private int first = 0;
	private int last = 0;
	private int theTotalRows = 0;
	private int rowPerPage = 0;
	private List<Restaurant> theRestaurantList;
	private boolean sortByIdInAscending = true;
	private boolean sortByNameInAscending = true;
	private boolean sortByAddressInAscending = true;
	private boolean sortByCuisineInAscending = true;

	@EJB
	private RestaurantJPAFactory restaurantFactory;

	public RestaurantBean() {

	}

	@PostConstruct
	public void init() {
		theRestaurant = new Restaurant();
		findRestaurants();
	}

	public Restaurant getRestaurant() {
		return theRestaurant;
	}

	public void setRestaurant(Restaurant aRestaurant) {
		this.theRestaurant = aRestaurant;
	}

	public String getRestaurantID() {
		return theRestaurantID;
	}

	public void setRestaurantID(String aRestaurantID) {
		this.theRestaurantID = aRestaurantID;
	}

	public List<Restaurant> getRestaurantList() {
		if (theRestaurantList == null) {
			theRestaurantList = findRestaurants();
		}
		if (theRestaurantList != null) {
			theTotalRows = theRestaurantList.size();
		}
		return theRestaurantList;
	}

	public void setRestaurantList(List<Restaurant> aRestaurantList) {
		this.theRestaurantList = aRestaurantList;
	}

	public String createRestaurant() {
		restaurantFactory.persistRestaurant(theRestaurant);
		findRestaurants(); // refresh restaurant list in the managed bean
		return "listRestaurants";
	}

	public List<Restaurant> findRestaurants() {
		theRestaurantList = restaurantFactory.findRestaurants();
		if (theRestaurantList != null) {
			theTotalRows = theRestaurantList.size();
		}

		return theRestaurantList;
	}

	public String dropRestaurant(Restaurant aRestaurant) {
		restaurantFactory.deleteRestaurant(aRestaurant);
		findRestaurants(); // refresh list in the managed bean
		return "listRestaurants";
	}

	public List<String> getDefaultCuisines() {
		List<String> cuisines = new ArrayList<String>();
		FacesContext context = FacesContext.getCurrentInstance();
//		FacesMessage message = MessageFactory.getMessage(context, "restaurantCuisine");
//		String defaultCuisine = message.getDetail();
		ResourceBundle res = context.getApplication().getResourceBundle(context, "msgs");
		String defaultCuisine = res.getString( "restaurantCuisine" );
		String[] cuisineArray = defaultCuisine.split(",");
		for (String aCuisine : cuisineArray) {
			cuisines.add( aCuisine );
		}
//		cuisines.add("Chinese");
//		cuisines.add("Mexican");
//		cuisines.add("European");

		return cuisines;
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

	public void sortById() {
		if (sortByIdInAscending) {
			Collections.sort(theRestaurantList, new Comparator<Restaurant>() {
				public int compare(Restaurant r1, Restaurant r2) {
					return r1.getId().compareTo(r2.getId());
				}
			});
			sortByIdInAscending = false;
		} else {
			Collections.sort(theRestaurantList, new Comparator<Restaurant>() {
				public int compare(Restaurant r1, Restaurant r2) {
					return r2.getId().compareTo(r1.getId());
				}
			});
			sortByIdInAscending = true;
		}
	}

	public void sortByName() {
		if (sortByNameInAscending) {
			Collections.sort(theRestaurantList, new Comparator<Restaurant>() {
				public int compare(Restaurant r1, Restaurant r2) {
					return r1.getName().compareTo(r2.getName());
				}
			});
			sortByNameInAscending = false;
		} else {
			Collections.sort(theRestaurantList, new Comparator<Restaurant>() {
				public int compare(Restaurant r1, Restaurant r2) {
					return r2.getName().compareTo(r1.getName());
				}
			});
			sortByNameInAscending = true;
		}
	}

	public void sortByAddress() {
		if (sortByAddressInAscending) {
			Collections.sort(theRestaurantList, new Comparator<Restaurant>() {
				public int compare(Restaurant r1, Restaurant r2) {
					return r1.getAddress().compareTo(r2.getAddress());
				}
			});
			sortByAddressInAscending = false;
		} else {
			Collections.sort(theRestaurantList, new Comparator<Restaurant>() {
				public int compare(Restaurant r1, Restaurant r2) {
					return r2.getAddress().compareTo(r1.getAddress());
				}
			});
			sortByAddressInAscending = true;
		}
	}

	public void sortByCuisine() {
		if (sortByCuisineInAscending) {
			Collections.sort(theRestaurantList, new Comparator<Restaurant>() {
				public int compare(Restaurant r1, Restaurant r2) {
					if (r1.getCuisine() == null) {
						return -1;
					} else if (r2.getCuisine() == null) {
						return 1;
					} else {
						return r1.getCuisine().compareTo(r2.getCuisine());
					}
				}
			});
			sortByCuisineInAscending = false;
		} else {
			Collections.sort(theRestaurantList, new Comparator<Restaurant>() {
				public int compare(Restaurant r1, Restaurant r2) {
					if (r2.getCuisine() == null) {
						return -1;
					} else if (r1.getCuisine() == null) {
						return 1;
					} else {
						return r2.getCuisine().compareTo(r1.getCuisine());
					}
				}
			});
			sortByCuisineInAscending = true;
		}
	}
}
