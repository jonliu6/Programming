package org.freecode.demo.geojson;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

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

    public String toJsonString() {
        return "{" + "\"type\":\"" + type + "\",\"geometry\":" + geometry.toJsonString() + ",\"properties\":" + getPropertyJsonString() + "}";
    }
}

