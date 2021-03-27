package org.freecode.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        GeoJsonTester tester = new GeoJsonTester();
        GeoJsonFeatureCollection gjsFeatureCollection = tester.generateGeoJsonObject();
        System.out.println("Using org.freecode.demo.geojson.GeoJsonFeatureCollection.toJsonString():");
        System.out.println(gjsFeatureCollection.toJsonString());

        System.out.println("Using com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString():");
        System.out.println(tester.exportObjectToJsonString(gjsFeatureCollection));
    }

    public GeoJsonFeatureCollection generateGeoJsonObject() {
        // Add a Point Feature
        GeoJsonFeatureCollection gjsFeatureCollection = new GeoJsonFeatureCollection();
        GeoJsonCoordinate gjsPointCoordinate1 = new GeoJsonCoordinate(-118.195941, 50.987736438);
        GeoJsonGeometry gjsPointGeometry = new GeoJsonGeometry(GeoJsonGeometry.POINT, gjsPointCoordinate1.toArray());
        Map<String, String> gjsPointProperties = new HashMap<String, String>();
        gjsPointProperties.put("name", "TO# 111111-1");
        gjsPointProperties.put("type", "restoration");
        GeoJsonFeature gjsPointFeature = new GeoJsonFeature(gjsPointGeometry, gjsPointProperties);
        gjsFeatureCollection.getFeatures().add(gjsPointFeature);

        // Add a LineString Feature
        List<Object> gjsLineCoordinates = new ArrayList<>();
        GeoJsonCoordinate gjsP1 = new GeoJsonCoordinate(-118.047311795786,51.0152625086301);
        GeoJsonCoordinate gjsP2 = new GeoJsonCoordinate(-117.52310971093,51.3055139500868);
        GeoJsonCoordinate gjsP3 = new GeoJsonCoordinate(-118.088613944753,51.0106685891173);
        gjsLineCoordinates.add(gjsPointCoordinate1.toArray());
        gjsLineCoordinates.add(gjsP1.toArray());
        gjsLineCoordinates.add(gjsP2.toArray());
        gjsLineCoordinates.add(gjsP3.toArray());
        GeoJsonGeometry gjsLineGeometry = new GeoJsonGeometry(GeoJsonGeometry.LINE, gjsLineCoordinates.toArray());
        Map<String, String> gjsLineProperties = new HashMap<String, String>();
        gjsLineProperties.put("name", "Crew #1234");
        gjsLineProperties.put("status", "online");
        GeoJsonFeature gjsLineFeature = new GeoJsonFeature(gjsLineGeometry, gjsLineProperties);
        gjsFeatureCollection.getFeatures().add(gjsLineFeature);

        // Add a Polygon Feature
        List<Object> gjsPolygonCoordinate1 = new ArrayList<>();
        gjsP1 = new GeoJsonCoordinate(-118.060924441177,51.0089925272291);
        gjsPolygonCoordinate1.add(gjsP1.toArray());
        gjsP2 = new GeoJsonCoordinate(-118.047311795786,51.0152625086301);
        gjsPolygonCoordinate1.add(gjsP2.toArray());
        gjsP3 = new GeoJsonCoordinate(-117.519348658032,51.2703819318938);
        gjsPolygonCoordinate1.add(gjsP3.toArray());
        GeoJsonCoordinate gjsP4 = new GeoJsonCoordinate(-117.520825069389,51.3038026609952);
        gjsPolygonCoordinate1.add(gjsP4.toArray());
        GeoJsonCoordinate gjsP5 = new GeoJsonCoordinate(-117.52310971093,51.3055139500868);
        gjsPolygonCoordinate1.add(gjsP5.toArray());
        GeoJsonCoordinate gjsP6 = new GeoJsonCoordinate(-117.530732282686,51.3041194941384);
        gjsPolygonCoordinate1.add(gjsP6.toArray());
        GeoJsonCoordinate gjsP7 = new GeoJsonCoordinate(-117.873476445288,51.1391460292046);
        gjsPolygonCoordinate1.add(gjsP7.toArray());
        GeoJsonCoordinate gjsP8 = new GeoJsonCoordinate(-118.088613944753,51.0106685891173);
        gjsPolygonCoordinate1.add(gjsP8.toArray());
        GeoJsonCoordinate gjsP9 = new GeoJsonCoordinate(-118.060924441177,51.0089925272291);
        gjsPolygonCoordinate1.add(gjsP9.toArray());
        List<Object> gjsPolygonCoordinates = new ArrayList<Object>();
        gjsPolygonCoordinates.add(gjsPolygonCoordinate1.toArray());
        GeoJsonGeometry gjsPolygonGeometry = new GeoJsonGeometry(GeoJsonGeometry.POLYGON, gjsPolygonCoordinates.toArray());
        Map<String, String> gjsPolygonProperties = new HashMap<String, String>();
        gjsPolygonProperties.put("name", "Outage Area");
        gjsPolygonProperties.put("type", "Power Down");
        GeoJsonFeature gjsPolygonFeature = new GeoJsonFeature(gjsPolygonGeometry, gjsPolygonProperties);
        gjsFeatureCollection.getFeatures().add(gjsPolygonFeature);

        return gjsFeatureCollection;
    }

    public String exportObjectToJsonString(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            // json = mapper.writeValueAsString(obj);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        return json;
    }
}
