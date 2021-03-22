package org.freecode.demo.kml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="Folder")
@XmlType(propOrder = {"name", "description", "placemarks"})
public class Folder {
    private String name;
    private String description;
    private List<Placemark> placemarks;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name="Placemark")
    public List<Placemark> getPlacemarks() {
        return placemarks;
    }
    public void setPlacemarks(List<Placemark> placemarks) {
        this.placemarks = placemarks;
    }

    public void addPlacemark(Placemark aMark) {
        if (placemarks == null) {
            placemarks = new ArrayList<>();
        }
        placemarks.add(aMark);
    }
}
