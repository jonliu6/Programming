package org.freecode.demo.geojson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeoJsonFeatureCollection implements Serializable {
    private final static String type = "FeatureCollection";
    private List<GeoJsonFeature> features = new ArrayList<GeoJsonFeature>();

    public String type() {
        return type;
    }

    public List<GeoJsonFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<GeoJsonFeature> features) {
        this.features = features;
    }

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

    public String toJsonString() {
        return "{" + "\"type\":" + "\"" + type + "\",\"features\":" + getFeatureJsonString() + "}";
    }
}
