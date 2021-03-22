package org.freecode.demo.kml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="outerBoundaryIs")
public class OuterBoundaryIs {
    private LinearRing lineRing;

    @XmlElement(name="LinearRing")
    public LinearRing getLineRing() {
        return lineRing;
    }
    public void setLineRing(LinearRing lineRing) {
        this.lineRing = lineRing;
    }
}
