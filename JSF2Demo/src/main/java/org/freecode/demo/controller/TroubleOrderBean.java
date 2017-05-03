package org.freecode.demo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.freecode.demo.model.DataService;
import org.freecode.demo.model.TroubleOrder;

@ManagedBean(name="troubleOrderBean")
@SessionScoped
public class TroubleOrderBean implements Serializable {
    private List<TroubleOrder> troubleOrders;
    private Integer orderCount; // for demo JDBC purpose
    private DataService dataService;
    private int first = 0;
    private int rows = 10;
    private List<String> filters = null;
    private String newFilter = null;
    
    public TroubleOrderBean() {
        dataService = new DataService();
        troubleOrders = dataService.findAllActiveOrders();
        orderCount = dataService.findOrderCount();
    }
    
    public List<TroubleOrder> getTroubleOrders() {
        return troubleOrders;
    }
    
    public Integer getOrderCount() {
        return orderCount;
    }

    public int getFirst() {
    	return first;
    }
    
    public void setFirst(int idx) {
    	first = idx;
    }
    
    public int getRows() {
    	return rows;
    }
    
    public String getNewFilter() {
		return newFilter;
	}

	public void setNewFilter(String newFilter) {
		this.newFilter = newFilter;
	}

	public void addFilter() {
    	if ( filters == null ) {
    		filters = new ArrayList<String>();
    	}
    	if ( newFilter != null ) {
    	  filters.add(newFilter);
    	  newFilter = null;
    	}
    	
    	System.out.println(filters);
    	System.out.println("new Filter: " + newFilter);
    }
	
	private boolean sortAscending = true;
	
	public void sortOrderLabel() {
		if (sortAscending) {
			Collections.sort(troubleOrders, new Comparator<TroubleOrder>() {
			    public int compare(TroubleOrder o1, TroubleOrder o2) {
			    	return o1.getReferenceLabel().compareTo(o2.getReferenceLabel());
			    }
			});
		}
		else {
			Collections.sort(troubleOrders, new Comparator<TroubleOrder>() {
			    public int compare(TroubleOrder o1, TroubleOrder o2) {
			    	return o2.getReferenceLabel().compareTo(o1.getReferenceLabel());
			    }
			});
		}
		sortAscending = !sortAscending;
	}
}
