package org.freecode.demo.geojson;

import java.io.Serializable;

public class GeoJsonCoordinate implements Serializable {
    private Double longitude;
    private Double latitude;

    public GeoJsonCoordinate() {}

    public GeoJsonCoordinate(Double longitude, Double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return longitude + "," + latitude;
    }

    public String toJsonString() {
        return "[" + longitude + "," + latitude + "]";
    }

    public Double[] toArray() {
        Double[] array = new Double[2];
        array[0] = longitude;
        array[1] = latitude;
        return array;
    }
}
