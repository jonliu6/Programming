package org.freecode.demo;

import org.freecode.demo.kml.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class KmlGeneratorTester {
    public static void main(String[] args) throws Exception {

        KmlGeneratorTester kmlTst = new KmlGeneratorTester();
        kmlTst.generateKMLFile("test_kml.kml");

    }

    public void generateKMLFile(String fileName) throws Exception{
        JAXBContext jctx=JAXBContext.newInstance(KMLRoot.class);
        KMLRoot kml = generateKMLContent();
        Marshaller marshaller = jctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(kml, new File(fileName));
    }

    public KMLRoot generateKMLContent() {
        KMLRoot kml = new KMLRoot();
        KMLDocument doc = new KMLDocument();
        doc.setName("Document Name");
        doc.setDescription("The description of this KML Document");

        Folder pointFolder = new Folder();
        pointFolder.setName("Point Folder");
        pointFolder.setDescription("The description of the point folder");
        Placemark pointMark = new Placemark();
        Point point = new Point();
        pointMark.setName("Point 1");
        pointMark.setDescription("The description Point One");
        KmlCoordinate coord = new KmlCoordinate(-122.0, -49.0);
        point.setCoordinates(coord.toString());
        pointMark.setPoint(point);
        pointFolder.addPlacemark(pointMark);

        Folder lineFolder = new Folder();
        lineFolder.setName("Line Folder");
        lineFolder.setDescription("The description of the line folder");
        Placemark lineMark = new Placemark();
        LineString line = new LineString();
        lineMark.setName("Line 1");
        lineMark.setDescription("The description of Line One");
        KmlCoordinate coord2 = new KmlCoordinate(-123.0, -49.0);
        line.setCoordinates(coord.toString() + "\n" + coord2.toString());
        lineMark.setLine(line);
        lineFolder.addPlacemark(lineMark);

        Folder polygonFolder = new Folder();
        polygonFolder.setName("Polygon Folder");
        polygonFolder.setDescription("The description of the polygon folder");
        Placemark polygonMark = new Placemark();
        Polygon polygon = new Polygon();
        polygonMark.setName("Polygon 1");
        polygonMark.setDescription("The description of Polygon One");
        OuterBoundaryIs outerBoundary = new OuterBoundaryIs();
        LinearRing linearRing = new LinearRing();
        KmlCoordinate coord3 = new KmlCoordinate(-123.0, -50.0);
        KmlCoordinate coord4 = new KmlCoordinate(-122.0, -50.0);
        linearRing.setCoordinates(coord.toString() + "\n" +
                coord2.toString() + "\n" +
                coord3.toString() + "\n" +
                coord4.toString() + "\n" +
                coord.toString() + "\n");
        outerBoundary.setLineRing(linearRing);
        polygon.setOutBoundaryIs(outerBoundary);
        polygonMark.setPolygon(polygon);
        polygonFolder.addPlacemark(polygonMark);

        List<Folder> folders = new ArrayList<Folder>();
        folders.add(pointFolder);
        folders.add(lineFolder);
        folders.add(polygonFolder);
        doc.setFolders(folders);
        kml.setDocument(doc);

        return kml;
    }
}
