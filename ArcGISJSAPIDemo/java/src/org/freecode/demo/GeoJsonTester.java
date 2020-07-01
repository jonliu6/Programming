package org.freecode.demo;

import org.freecode.demo.geojson.GeoJsonCoordinate;
import org.freecode.demo.geojson.GeoJsonFeature;
import org.freecode.demo.geojson.GeoJsonFeatureCollection;
import org.freecode.demo.geojson.GeoJsonGeometry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a sample to test GeoJson POJO classes
 */
public class GeoJsonTester {
    public static void main(String[] args) {
        // Add a Point Feature
        GeoJsonFeatureCollection gjsFeatureCollection = new GeoJsonFeatureCollection();
        GeoJsonCoordinate gjsPointCoordinate1 = new GeoJsonCoordinate(-118.195941, 50.987736438);
        GeoJsonGeometry gjsPointGeometry = new GeoJsonGeometry(GeoJsonGeometry.POINT, gjsPointCoordinate1);
        Map<String, String> gjsPointProperties = new HashMap<String, String>();
        gjsPointProperties.put("name", "TO# 111111-1");
        gjsPointProperties.put("type", "restoration");
        GeoJsonFeature gjsPointFeature = new GeoJsonFeature(gjsPointGeometry, gjsPointProperties);
        gjsFeatureCollection.getFeatures().add(gjsPointFeature);

        // Add a LineString Feature
        List<GeoJsonCoordinate> gjsLineCoordinates = new ArrayList<GeoJsonCoordinate>();
        GeoJsonCoordinate gjsP1 = new GeoJsonCoordinate(-118.047311795786,51.0152625086301);
        GeoJsonCoordinate gjsP2 = new GeoJsonCoordinate(-117.52310971093,51.3055139500868);
        GeoJsonCoordinate gjsP3 = new GeoJsonCoordinate(-118.088613944753,51.0106685891173);
        gjsLineCoordinates.add(gjsPointCoordinate1);
        gjsLineCoordinates.add(gjsP1);
        gjsLineCoordinates.add(gjsP2);
        gjsLineCoordinates.add(gjsP3);
        GeoJsonGeometry gjsLineGeometry = new GeoJsonGeometry(GeoJsonGeometry.LINE, gjsLineCoordinates);
        Map<String, String> gjsLineProperties = new HashMap<String, String>();
        gjsLineProperties.put("name", "Crew #1234");
        gjsLineProperties.put("status", "online");
        GeoJsonFeature gjsLineFeature = new GeoJsonFeature(gjsLineGeometry, gjsLineProperties);
        gjsFeatureCollection.getFeatures().add(gjsLineFeature);

        // Add a Polygon Feature
        List<Object> gjsPolygonCoordinate1 = new ArrayList<Object>();
        gjsP1 = new GeoJsonCoordinate(-118.060924441177,51.0089925272291);
        gjsPolygonCoordinate1.add(gjsP1);
        gjsP2 = new GeoJsonCoordinate(-118.047311795786,51.0152625086301);
        gjsPolygonCoordinate1.add(gjsP2);
        gjsP3 = new GeoJsonCoordinate(-117.519348658032,51.2703819318938);
        gjsPolygonCoordinate1.add(gjsP3);
        GeoJsonCoordinate gjsP4 = new GeoJsonCoordinate(-117.520825069389,51.3038026609952);
        gjsPolygonCoordinate1.add(gjsP4);
        GeoJsonCoordinate gjsP5 = new GeoJsonCoordinate(-117.52310971093,51.3055139500868);
        gjsPolygonCoordinate1.add(gjsP5);
        GeoJsonCoordinate gjsP6 = new GeoJsonCoordinate(-117.530732282686,51.3041194941384);
        gjsPolygonCoordinate1.add(gjsP6);
        GeoJsonCoordinate gjsP7 = new GeoJsonCoordinate(-117.873476445288,51.1391460292046);
        gjsPolygonCoordinate1.add(gjsP7);
        GeoJsonCoordinate gjsP8 = new GeoJsonCoordinate(-118.088613944753,51.0106685891173);
        gjsPolygonCoordinate1.add(gjsP8);
        GeoJsonCoordinate gjsP9 = new GeoJsonCoordinate(-118.060924441177,51.0089925272291);
        gjsPolygonCoordinate1.add(gjsP9);
        List<Object> gjsPolygonCoordinates = new ArrayList<Object>();
        gjsPolygonCoordinates.add(gjsPolygonCoordinate1);
        GeoJsonGeometry gjsPolygonGeometry = new GeoJsonGeometry(GeoJsonGeometry.POLYGON, gjsPolygonCoordinates);
        Map<String, String> gjsPolygonProperties = new HashMap<String, String>();
        gjsPolygonProperties.put("name", "Outage Area");
        gjsPolygonProperties.put("type", "Power Down");
        GeoJsonFeature gjsPolygonFeature = new GeoJsonFeature(gjsPolygonGeometry, gjsPolygonProperties);
        gjsFeatureCollection.getFeatures().add(gjsPolygonFeature);

        System.out.println(gjsFeatureCollection.toJsonString());
    }
}
