
package org.freecode.demo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.freecode.demo package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindingReports_QNAME = new QName("http://www.bchydro.com/GTRM", "findingReports");
    private final static QName _FindingReportsResponse_QNAME = new QName("http://www.bchydro.com/GTRM", "findingReportsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.freecode.demo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindingReports_Type }
     * 
     */
    public FindingReports_Type createFindingReports_Type() {
        return new FindingReports_Type();
    }

    /**
     * Create an instance of {@link ReportTransferObject }
     * 
     */
    public ReportTransferObject createReportTransferObject() {
        return new ReportTransferObject();
    }

    /**
     * Create an instance of {@link FindingReportsResponse }
     * 
     */
    public FindingReportsResponse createFindingReportsResponse() {
        return new FindingReportsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindingReports_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bchydro.com/GTRM", name = "findingReports")
    public JAXBElement<FindingReports_Type> createFindingReports(FindingReports_Type value) {
        return new JAXBElement<FindingReports_Type>(_FindingReports_QNAME, FindingReports_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindingReportsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bchydro.com/GTRM", name = "findingReportsResponse")
    public JAXBElement<FindingReportsResponse> createFindingReportsResponse(FindingReportsResponse value) {
        return new JAXBElement<FindingReportsResponse>(_FindingReportsResponse_QNAME, FindingReportsResponse.class, null, value);
    }

}
