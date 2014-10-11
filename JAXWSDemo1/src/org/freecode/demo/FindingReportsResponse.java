
package org.freecode.demo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for findingReportsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findingReportsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="findingReportsResponse" type="{http://www.bchydro.com/GTRM}reportTransferObject" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findingReportsResponse", propOrder = {
    "findingReportsResponse"
})
public class FindingReportsResponse {

    @XmlElement(nillable = true)
    protected List<ReportTransferObject> findingReportsResponse;

    /**
     * Gets the value of the findingReportsResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the findingReportsResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFindingReportsResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReportTransferObject }
     * 
     * 
     */
    public List<ReportTransferObject> getFindingReportsResponse() {
        if (findingReportsResponse == null) {
            findingReportsResponse = new ArrayList<ReportTransferObject>();
        }
        return this.findingReportsResponse;
    }

}
