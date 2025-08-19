
package org.freecode.demo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sayHelloResponse complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="sayHelloResponse">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="sayHelloResponse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sayHelloResponse", propOrder = {
    "sayHelloResponse"
})
public class SayHelloResponse {

    protected String sayHelloResponse;

    /**
     * Gets the value of the sayHelloResponse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSayHelloResponse() {
        return sayHelloResponse;
    }

    /**
     * Sets the value of the sayHelloResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSayHelloResponse(String value) {
        this.sayHelloResponse = value;
    }

}
