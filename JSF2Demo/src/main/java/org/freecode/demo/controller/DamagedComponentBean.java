package org.freecode.demo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.freecode.demo.model.DamagedComponent;
import org.freecode.demo.model.DamagedComponentFactory;

@ManagedBean(name="damagedComponentBean")
@SessionScoped
public class DamagedComponentBean implements Serializable {
    private String selectedPrimaryCode;
    private String selectedSecondaryCode;
    private DamagedComponentFactory theFactory;
    private Collection<DamagedComponent> selectedDamagedComponents;
    private List<String> receivedDamagedComponentCodes;
    private Boolean relatedToEquipmentFailure;
    
    public DamagedComponentBean() {
        theFactory = new DamagedComponentFactory();
        theFactory.initializeDamagedComponents();
        theFactory.populateDamagedComponentMap();
    }
    
    public String getSelectedPrimaryCode() {
        return selectedPrimaryCode;
    }
    public void setSelectedPrimaryCode(String selectedPrimaryCode) {
        this.selectedPrimaryCode = selectedPrimaryCode;
    }
    public String getSelectedSecondaryCode() {
        return selectedSecondaryCode;
    }
    public void setSelectedSecondaryCode(String selectedSecondaryCode) {
        this.selectedSecondaryCode = selectedSecondaryCode;
    }
    
    public List<SelectItem> getPrimaryComponents() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        items.add(new SelectItem("","[None]"));
        if ( theFactory.getPrimaryCodes() != null && theFactory.getPrimaryCodes().size() > 0 ) {
            for ( String code : theFactory.getPrimaryCodes() ) {
                SelectItem anItem = new SelectItem(code, code);
                items.add(anItem);
            }
        }
        
        return items;
    }
    
    public List<SelectItem> getSecondaryComponents() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        items.add(new SelectItem("","[None]"));
        if ( selectedPrimaryCode != null ) {
            Collection<String> secondaryCodes = theFactory.getSecondaryCodes(selectedPrimaryCode, relatedToEquipmentFailure);
            for ( String code : secondaryCodes ) {
                SelectItem anItem = new SelectItem(code, code);
                items.add(anItem);
            }
        }
        return items;
    }
    
    public void addDamagedComponent() {
        if ( selectedPrimaryCode != null && selectedSecondaryCode != null ) {
            DamagedComponent aDamagedComponent = theFactory.getDamagedComponent(selectedPrimaryCode, selectedSecondaryCode);
            if ( aDamagedComponent != null ) {
                if ( selectedDamagedComponents == null ) {
                    selectedDamagedComponents = new ArrayList<DamagedComponent>();
                }
                selectedDamagedComponents.add(aDamagedComponent);
            }
        }
    }
    
    public void removeDamagedComponent() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String aCode = (String) params.get("selectedDamagedComponentCode");
        if ( selectedDamagedComponents != null && selectedDamagedComponents.size() > 0 && aCode != null && aCode.length() > 0 ) {
            DamagedComponent selectedOne = theFactory.getDamagedComponent(aCode);
            if ( selectedOne != null ) {
                selectedDamagedComponents.remove(selectedOne);
            }
        }
    }
    
    public Collection<DamagedComponent> getSelectedDamagedComponents() {
        return selectedDamagedComponents;
    }
    
    public String getSelectedDamagedComponentCodes() {
        StringBuilder savedCodes = new StringBuilder("");
        if ( selectedDamagedComponents != null && selectedDamagedComponents.size() > 0 ) {
            for (Iterator<DamagedComponent> it = selectedDamagedComponents.iterator(); it.hasNext();) {
                DamagedComponent dc = it.next();
                if ( dc != null ) {
                    if ( savedCodes.length() > 0 ) {
                        savedCodes.append(";");
                    }
                    savedCodes.append(dc.getCode());
                }
            }
        }
        else {
            savedCodes = new StringBuilder("");
        }
        
        return savedCodes.toString();
    }
    
    public List<SelectItem> getAllDamagedComponentCodes() {
        List<SelectItem> allCodes = new ArrayList<SelectItem>();
        List<DamagedComponent> allComponents = theFactory.getAllDamagedComponents();
        if ( allComponents != null && allComponents.size() > 0 ) {
            for (Iterator<DamagedComponent> it = allComponents.iterator(); it.hasNext();) {
                DamagedComponent dc = it.next();
                if ( dc != null ) {
                    SelectItem anItem = new SelectItem(dc.getCode(), dc.getCode());
                    allCodes.add(anItem);
                }
            }
        }
        
        
        return allCodes;
    }

    public List<String> getReceivedDamagedComponentCodes() {
        return receivedDamagedComponentCodes;
    }

    public void setReceivedDamagedComponentCodes( List<String> receivedCodes) {
        receivedDamagedComponentCodes = receivedCodes;
    }
    
    public String getReceivedDamagedComponents() {
        StringBuilder receivedDamagedComponents = new StringBuilder("");
        // get the damaged component codes
        if ( receivedDamagedComponentCodes != null && receivedDamagedComponentCodes.size() > 0 ){
            for (Iterator<String> it = receivedDamagedComponentCodes.iterator(); it.hasNext();) {
                String aCode= it.next();
                if ( aCode != null && aCode.length() > 0) {
                    if ( receivedDamagedComponents.length() > 0 ) {
                        receivedDamagedComponents.append(";");
                    }
                    receivedDamagedComponents.append( aCode );
                }
            }
        }
        else {
            receivedDamagedComponents = new StringBuilder("");
        }
        
        // append the details of damaged components
        if ( receivedDamagedComponents != null && receivedDamagedComponents.length() > 0 ) {
            Collection<DamagedComponent> damagedComponentCollection = theFactory.getDamagedComponents(receivedDamagedComponents.toString(), ";");
            if ( damagedComponentCollection != null && damagedComponentCollection.size() > 0 ) {
                receivedDamagedComponents.append("<br/>");
                for (Iterator<DamagedComponent> it = damagedComponentCollection.iterator(); it.hasNext();) {
                    DamagedComponent aDC = it.next();
                    if ( aDC != null ) {
                        receivedDamagedComponents.append(aDC.getPrimaryComponent() + " - " + aDC.getSecondaryComponent() + "<br/>");
                    }
                }
            }
        }
        
        return receivedDamagedComponents.toString();
    }

    public Boolean getRelatedToEquipmentFailure() {
        return relatedToEquipmentFailure;
    }

    public void setRelatedToEquipmentFailure(Boolean isRelated) {
        this.relatedToEquipmentFailure = isRelated;
    }
    
    
}
