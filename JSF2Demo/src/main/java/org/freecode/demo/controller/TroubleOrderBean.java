package org.freecode.demo.controller;

import java.io.Serializable;
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

}
