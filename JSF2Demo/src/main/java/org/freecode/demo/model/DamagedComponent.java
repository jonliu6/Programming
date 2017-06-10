package org.freecode.demo.model;

import java.util.UUID;

public class DamagedComponent {
    private String code;
    private String primaryComponent;
    private String secondaryComponent;
    private Boolean relatedToEquipmentFailure = Boolean.FALSE;
    
    public DamagedComponent() {
        
    }
    
    public DamagedComponent(String aCode, String aPrimaryComponent, String aSecondaryComponent) {
        code = aCode;
        primaryComponent = aPrimaryComponent;
        secondaryComponent = aSecondaryComponent;
        relatedToEquipmentFailure = Boolean.FALSE;
    }
    
    public DamagedComponent(String aCode, String aPrimaryComponent, String aSecondaryComponent, Boolean forFailure) {
        code = aCode;
        primaryComponent = aPrimaryComponent;
        secondaryComponent = aSecondaryComponent;
        relatedToEquipmentFailure = forFailure;
    }
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getPrimaryComponent() {
        return primaryComponent;
    }
    public void setPrimaryComponent(String primaryComponent) {
        this.primaryComponent = primaryComponent;
    }
    public String getSecondaryComponent() {
        return secondaryComponent;
    }
    public void setSecondaryComponent(String secondaryComponent) {
        this.secondaryComponent = secondaryComponent;
    }
    public Boolean getRelatedToEquipmentFailure() {
        return relatedToEquipmentFailure;
    }
    public void setRelatedToEquipmentFailure(Boolean relatedToEquipmentFailure) {
        this.relatedToEquipmentFailure = relatedToEquipmentFailure;
    }

    @Override
    public String toString() {
        return "DamagedComponent [code=" + code + ", primaryComponent="
                + primaryComponent + ", secondaryComponent="
                + secondaryComponent + "]";
    }
}
