package org.freecode.demo.geojson;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * a class representing the Feature object in GeoJSON
 * refer to https://geojson.org/ and https://tools.ietf.org/html/rfc7946 for more details of GeoJSON
 */
@JsonPropertyOrder({"type","geometry","properties"})
public class GeoJsonFeature implements Serializable {
    private final static String type = "Feature";
    private GeoJsonGeometry geometry;
    private Map<String, String> properties;

    public GeoJsonFeature() {}
    public GeoJsonFeature(GeoJsonGeometry geometry, Map<String, String> properties) {
        this.geometry = geometry;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public GeoJsonGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(GeoJsonGeometry geometry) {
        this.geometry = geometry;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    /**
     * @return String output of the custom properties defined in the Feature object
     */
    private String getPropertyJsonString() {
        StringBuilder jsonStr = new StringBuilder();
        jsonStr.append("{");
        if (properties != null && properties.size() > 0) {
            int i  = 0;
            for (Iterator<String> it = properties.keySet().iterator(); it.hasNext();) {
                if (it != null) {
                    if (i > 0) {
                        jsonStr.append(",");
                    }
                    String propertyName = it.next();
                    String propertyValue = properties.get(propertyName);
                    if (propertyValue == null) {
                        propertyValue = "\"\"";
                    }
                    jsonStr.append("\"" + propertyName + "\":" + "\"" + propertyValue + "\"");
                }
                ++i;
            }
        }
        jsonStr.append("}");

        return jsonStr.toString();
    }

    /**
     * @return String output of the Feature object in GeoJSON
     */
    public String toJsonString() {
        return "{" + "\"type\":\"" + type + "\",\"geometry\":" + geometry.toJsonString() + ",\"properties\":" + getPropertyJsonString() + "}";
    }
}

