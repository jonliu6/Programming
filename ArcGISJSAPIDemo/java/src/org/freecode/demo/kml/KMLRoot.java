package org.freecode.demo.kml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="kml")
public class KMLRoot {
    private KMLDocument document;
    @XmlAttribute
    private String xmlns = "http://www.opengis.net/kml/2.2";
    @XmlElement(name="Document")
    public KMLDocument getDocument() {
        return document;
    }
    public void setDocument(KMLDocument document) {
        this.document = document;
    }
}
