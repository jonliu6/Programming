package org.freecode.demo.kml;

public class KmlCoordinate {
    private Double longitude;
    private Double latitude;
    public KmlCoordinate() {}
    public KmlCoordinate(Double lng, Double lat) {
        this.longitude = lng;
        this.latitude = lat;
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
        return longitude +", " + latitude;
    }
}
