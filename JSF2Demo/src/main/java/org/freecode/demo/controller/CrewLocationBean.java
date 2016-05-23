package org.freecode.demo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.freecode.demo.model.CrewLocation;
import org.freecode.demo.model.DataService;
import org.richfaces.component.UIExtendedDataTable;

import com.google.gson.Gson;

@ManagedBean(name="crewLocation")
@SessionScoped
public class CrewLocationBean implements Serializable {
    private List<CrewLocation> crewLocations;
    private DataService dataService;
    private Float selectedLatitude;
    private Float selectedLongitude;
    private String selectedCrewId;
    private Collection<Object> selection;
    private String selectedCrewsInJSON;
    private List<CrewLocation> selectedCrews = new ArrayList<CrewLocation>();
    
    public Float getSelectedLatitude() {
        return selectedLatitude;
    }

    public void setSelectedLatitude(Float selectedLatitude) {
        this.selectedLatitude = selectedLatitude;
    }

    public Float getSelectedLongitude() {
        return selectedLongitude;
    }

    public void setSelectedLongitude(Float selectedLongitude) {
        this.selectedLongitude = selectedLongitude;
    }
    
    public String getSelectedCrewId() {
        return selectedCrewId;
    }

    public void setSelectedCrewId(String selectedCrewId) {
        this.selectedCrewId = selectedCrewId;
    }

    public CrewLocationBean() {
        dataService = new DataService();
        crewLocations = dataService.findCrewLocations();
    }
    
    public List<CrewLocation> getCrewLocations() {
        return crewLocations;
    }
    
    public Collection<Object> getSelection() {
        return selection;
    }

    public void setSelection(Collection<Object> selection) {
        this.selection = selection;
    }

    public String getSelectedCrewsInJSON() {
        StringBuilder sb = new StringBuilder();
        Gson gs = new Gson();
        if ( selectedCrews != null && selectedCrews.size() > 0 ) {
            sb.append( gs.toJson(selectedCrews) );
        }
        
        return sb.toString();
    }
    
    public void clearSelection() {
        setSelection(null);
    }
    
    public void selectionListener(AjaxBehaviorEvent event){
        UIExtendedDataTable dataTable = (UIExtendedDataTable)event.getComponent();
        
        Object originalKey = dataTable.getRowKey();
        if ( selectedCrews == null ) {
            selectedCrews = new ArrayList<CrewLocation>();
        }
        selectedCrews.clear();
        for (Object selectionKey: selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()){
                selectedCrews.add((CrewLocation)dataTable.getRowData());
            }
        }
        dataTable.setRowKey(originalKey);
    }

    public String viewingOnMap() {
        FacesContext context = FacesContext.getCurrentInstance();
        this.selectedLatitude = new Float(context.getExternalContext().getRequestParameterMap().get("crewLatitude"));
        this.selectedLongitude = new Float(context.getExternalContext().getRequestParameterMap().get("crewLongitude"));
        this.selectedCrewId = new String(context.getExternalContext().getRequestParameterMap().get("crewId"));
        
        return "viewingCrewOnMap";
    }
    
}
