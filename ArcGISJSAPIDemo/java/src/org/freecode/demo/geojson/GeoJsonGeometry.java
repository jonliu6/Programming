package org.freecode.demo.geojson;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

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

    public String toJsonString() {
        return "{" + "\"type\":" + "\"" + type + "\"" + ",\"coordinates\":" + getCoordinateJsonString(coordinates) + "}";
    }
}
