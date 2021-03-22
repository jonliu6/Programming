package org.freecode.demo.kml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"name", "description", "point", "line", "polygon"})
public class Placemark {
    private String name;
    private String description;
    private Point point;
    private LineString line;
    private Polygon polygon;

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

    @XmlElement(name="Point")
    public Point getPoint() {
        return point;
    }
    public void setPoint(Point point) {
        this.point = point;
    }

    @XmlElement(name="LineString")
    public LineString getLine() {
        return line;
    }
    public void setLine(LineString line) {
        this.line = line;
    }

    @XmlElement(name="Polygon")
    public Polygon getPolygon() {
        return polygon;
    }
    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }
}
