package org.freecode.demo.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 
 * This class is used for get Trouble Order with location information
 */
public class OrderLocation implements Serializable{
    private String referenceId;
    private int orderId;
    private int incidentId;
    private String region;
    private String municipality;
    private List<Coordinate> area;
    private Coordinate minCoord;
    private Coordinate maxCoord;
    private Coordinate centerCoord;
    
    public String getReferenceId() {
        return referenceId;
    }
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getIncidentId() {
        return incidentId;
    }
    public void setIncidentId(int incidentId) {
        this.incidentId = incidentId;
    }
    public List<Coordinate> getArea() {
        return area;
    }
    public void setArea(List<Coordinate> area) {
        this.area = area;
    }
    public Coordinate getMinCoord() {
        return minCoord;
    }
    public void setMinCoord(Coordinate minCoord) {
        this.minCoord = minCoord;
    }
    public Coordinate getMaxCoord() {
        return maxCoord;
    }
    public void setMaxCoord(Coordinate maxCoord) {
        this.maxCoord = maxCoord;
    }
    public Coordinate getCenterCoord() {
        return centerCoord;
    }
    public void setCenterCoord(Coordinate centerCoord) {
        this.centerCoord = centerCoord;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getMunicipality() {
        return municipality;
    }
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

}
