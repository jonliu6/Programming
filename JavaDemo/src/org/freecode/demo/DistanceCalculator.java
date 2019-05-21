package org.freecode.demo;

import java.text.DecimalFormat;

public class DistanceCalculator {
	private static double RADIUS_EARTH_IN_KILOMETERS = 6371.0d; // the "Earth radius" varies from 6356.752 km at the poles to 6378.137 km at the equator
	private static String DISTANCE_FORMAT = "#.###";
	
    public static void main(String[] args) {
        DistanceCalculator distCalc = new DistanceCalculator();
        // Location 1: Central Park Place @49.2300059,-123.0050796
        // Location 2: Metrotown Station @49.2274788,-123.0176254
        // Location 3: Lougheed Town Centre @49.247453,-122.8987135
        double lat1 = 49.2274788;
        double lng1 = -123.0176254;
        double lat2 = 49.247453;
        double lng2 = -122.8987135;
        System.out.println("Distance: " + distCalc.formatDistance(distCalc.calculateDistanceForTwoLatLngVals(lat1, lng1, lat2, lng2)) + " km");
        System.out.println("Distance (alternative): " + distCalc.formatDistance(distCalc.calculateDistanceForTwoLatLngVals2(lat1, lng1, lat2, lng2)) + " km");
        
    }
    
    public String formatDistance(double distVal) {
    	DecimalFormat df = new DecimalFormat(DISTANCE_FORMAT);
    	return df.format(distVal);
    }
    
    public double calculateDistanceForTwoLatLngVals(double lat1, double lng1, double lat2, double lng2) {
    	double dLat = Math.toRadians(lat2 - lat1);
    	double dLng = Math.toRadians(lng2 - lng1);
    	// Haversine formula https://en.wikipedia.org/wiki/Haversine_formula. NOTE: Math.sin(dLat/2) * Math.sin(dLat/2) and Math.sin(dLng/2) * Math.sin(dLng/2) must between 0 and 1
    	double distance = 2 * RADIUS_EARTH_IN_KILOMETERS * Math.asin( Math.sqrt( Math.sin(dLat/2) * Math.sin(dLat/2) + 
    			        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng/2) * Math.sin(dLng / 2) ) );
    	
    	return distance;
    }
    
    public double calculateDistanceForTwoLatLngVals2(double lat1, double lng1, double lat2, double lng2) {
    	double dLat = Math.toRadians(lat2 - lat1);
    	double dLng = Math.toRadians(lng2 - lng1);
    	double tmpVal = Math.sin(dLat/2) * Math.sin(dLat/2) + 
		                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng/2) * Math.sin(dLng / 2);
    	// Haversine formula, http://www.movable-type.co.uk/scripts/latlong.html
    	double distance = 2 * RADIUS_EARTH_IN_KILOMETERS * Math.atan2(Math.sqrt(tmpVal), Math.sqrt(1-tmpVal));
    	
    	return distance;
    }
    
}
