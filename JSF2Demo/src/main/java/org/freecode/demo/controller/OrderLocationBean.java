package org.freecode.demo.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.freecode.demo.model.DataService;
import org.freecode.demo.model.OrderLocation;

import com.google.gson.Gson;

@ManagedBean(name="orderLocation")
@SessionScoped
public class OrderLocationBean implements Serializable{

    private DataService dataService;
    
    private List<OrderLocation> orderLocations;
    
    private int selectedOrderId;
    private OrderLocation selectedOrderLocation;
    
    public OrderLocationBean() {
        dataService = new DataService();
        orderLocations = dataService.findAllOrderLocations();
    }

    public List<OrderLocation> getOrderLocations() {
        return orderLocations;
    }
    
    public void setOrderLocations(List<OrderLocation> orderLocations) {
        this.orderLocations = orderLocations;
    }
    
    public String getOrderLocationsInJSON() {
        String returnString = "";
        Gson gs = new Gson();
        if ( orderLocations != null && orderLocations.size() > 0 ) {
            returnString = gs.toJson(orderLocations);
        }
        
        return returnString;
    }
    
    public void selectOrderIdListener(ActionEvent ae) {
        selectedOrderId = (Integer) ae.getComponent().getAttributes().get("orderId");
        
        if ( orderLocations != null && orderLocations.size() > 0 ) {
            for (OrderLocation ol : orderLocations) {
                if ( ol != null && ol.getOrderId() == selectedOrderId ) {
                    selectedOrderLocation = ol;
                    break;
                }
            }
        }
        
    }
    
    public String getOrderLocationInJSONById() {
        String jsonString = "";
        if ( selectedOrderLocation != null ) {
            Gson gs = new Gson();
            jsonString = gs.toJson(selectedOrderLocation);
        }
        
        return jsonString;
    }
}
