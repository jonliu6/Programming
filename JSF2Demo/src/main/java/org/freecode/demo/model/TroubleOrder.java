/**
 * 
 */
package org.freecode.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author jonliu
 *
 */
@NamedQueries({
    @NamedQuery(name="TroubleOrder.findAllActive", query="SELECT object(to) FROM TroubleOrder AS to WHERE UPPER(STATUS) NOT IN ('FIELD_COMPLETE','CLOSED', 'ARCHIVED','CANCELLED') ")
})
@Entity
@Table(name="PO_RM_BCH_TO_VIEW")
public class TroubleOrder implements Serializable {
    private Integer orderId;    
    /** This is the order's reference label */
    private String referenceLabel;    
    private String type;
    private Integer priority;
    private String status;
    
    @Id
    @Column(name="ORDER_ID")
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    @Column(name="REFERENCE_LABEL")
    public String getReferenceLabel() {
        return referenceLabel;
    }
    public void setReferenceLabel(String referenceLabel) {
        this.referenceLabel = referenceLabel;
    }
    
    @Column(name="TYPE")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name="PRIORITY")
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    
    @Column(name="STATUS")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
