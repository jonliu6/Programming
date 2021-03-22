package org.freecode.demo.kml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Polygon{
    private OuterBoundaryIs outBoundaryIs;

    @XmlElement(name="outBoundaryIs")
    public OuterBoundaryIs getOutBoundaryIs() {
        return outBoundaryIs;
    }
    public void setOutBoundaryIs(OuterBoundaryIs outBoundaryIs) {
        this.outBoundaryIs = outBoundaryIs;
    }
}
