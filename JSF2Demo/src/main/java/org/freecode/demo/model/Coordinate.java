package org.freecode.demo.model;

import java.io.Serializable;

public class Coordinate implements Serializable{
    private double latitude;
    private double longitude;
    
    public Coordinate() {
        
    }
    
    public Coordinate(double lat, double lng) {
        latitude = lat;
        longitude = lng;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getText() {
        return "[" + String.valueOf(latitude) + ", " + String.valueOf(longitude) + "]";
    }
}
