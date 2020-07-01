package org.freecode.demo.geojson;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * a class representing the Geometry attribute of a "Feature" object in GeoJSON
 * refer to https://geojson.org/ and https://tools.ietf.org/html/rfc7946 for more details of GeoJSON
 */
public class GeoJsonGeometry implements Serializable {
    public final static String POINT = "Point";
    public final static String LINE = "LineString";
    public final static String POLYGON = "Polygon";
    private String type;
    private Object coordinates;

    public GeoJsonGeometry() {}

    public GeoJsonGeometry(String type, Object coordinates){
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Object coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * This recursive function returns the GeoJSON string of the coordinates attribute.
     * for Point, coordinates include an array with longitude and latitude as elements. e.g. [-120.1, 50.9]
     * for LineString, coordinates include an array of Point elements. e.g. [[-120.1, 50.9], [-120.9, 50.1],...]
     * for Polygon, coordinates include a 3-dimension array of LineString elements. e.g. [[[-120.1, 50.9], [-120.9, 50.1],[-120.9, 50.1],[-120.1, 50.9]]].
     * The elements need to be ordered for a polygon; or there may be holes. By default, the end point should be same as the start point.
     * @param values - a pair of longitude/latitude coordinate or an array of longitude/latitude coordinates
     * @return the GeoJSON string of the coordinates attribute
     */
    private String getCoordinateJsonString(Object values) {
        StringBuilder jsonStr = new StringBuilder();
        if (values != null) {
            if (values instanceof GeoJsonCoordinate) {
                jsonStr.append(((GeoJsonCoordinate) values).toJsonString());
            }
            else if (values instanceof List) {
                int i = 0;
                jsonStr.append("[");
                for (Iterator<Object> it = ((List<Object>) values).iterator(); it.hasNext();) {
                    if (i > 0) {
                        jsonStr.append(",");
                    }
                    jsonStr.append(getCoordinateJsonString(it.next()));
                    ++i;
                }
                jsonStr.append("]");
            }
        }
        return jsonStr.toString();
    }

    /**
     * @return String output of the Geometry attribute in GeoJSON
     */
    public String toJsonString() {
        return "{" + "\"type\":" + "\"" + type + "\"" + ",\"coordinates\":" + getCoordinateJsonString(coordinates) + "}";
    }
}
