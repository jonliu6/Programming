package org.freecode.demo.geojson;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * a class representing the top object - Feature Collection in GeoJSON
 * refer to https://geojson.org/ and https://tools.ietf.org/html/rfc7946 for more details of GeoJSON
 */
@JsonPropertyOrder({"type", "features"})
public class GeoJsonFeatureCollection implements Serializable {
    private final static String type = "FeatureCollection";
    private List<GeoJsonFeature> features = new ArrayList<GeoJsonFeature>();

    public String getType() {
        return type;
    }

    public List<GeoJsonFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<GeoJsonFeature> features) {
        this.features = features;
    }

    /**
     * @return String output of all the Feature objects of the Feature Collection
     */
    private String getFeatureJsonString() {
        StringBuilder jsonStr = new StringBuilder();
        if (features != null && features.size() > 0) {
            jsonStr.append("[");
            int i = 0;
            for (Iterator<GeoJsonFeature> it = features.iterator(); it.hasNext();) {
                if (i > 0) {
                    jsonStr.append(",");
                }
                jsonStr.append(it.next().toJsonString());
                ++i;
            }
            jsonStr.append("]");
        }
        return jsonStr.toString();
    }

    /**
     * @return String output of the FeatureCollection object in GeoJSON
     * @deprecated by using Jackson data bind com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString()
     */
    @Deprecated
    public String toJsonString() {
        return "{" + "\"type\":" + "\"" + type + "\",\"features\":" + getFeatureJsonString() + "}";
    }
}
