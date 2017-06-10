package org.freecode.demo.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(name="CrewLocation.FindAllCrewLocations", query="SELECT OBJECT(cl) FROM CrewLocation cl WHERE ROWNUM < 50 ")
})
@Entity
@Table(name="poweron.PO_BCH_CREW_GPS")
@IdClass(CrewLocationPK.class)
public class CrewLocation {
    public Long crewId;
    public String bchMDTNo;
    public Float coordX;
    public Float coordY;
    public Float latitude;
    public Float longitude;
    public String streetIntersection;
    public String cityTown;
    
    public CrewLocation() {
        
    }

    @Id
    @Column(name="CREW_ID")
    public Long getCrewId() {
        return crewId;
    }

    public void setCrewId(Long crewId) {
        this.crewId = crewId;
    }
    
    @Id
    @Column(name="BCH_MDT_NO")
    public String getBchMDTNo() {
        return bchMDTNo;
    }

    public void setBchMDTNo(String bchMDTNo) {
        this.bchMDTNo = bchMDTNo;
    }

    @Column(name="X")
    public Float getCoordX() {
        return coordX;
    }

    public void setCoordX(Float coordX) {
        this.coordX = coordX;
    }

    @Column(name="Y")
    public Float getCoordY() {
        return coordY;
    }

    public void setCoordY(Float coordY) {
        this.coordY = coordY;
    }

    @Column(name="SL_LATITUDE")
    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    @Column(name="SL_LONGITUDE")
    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Column(name="STREET_INTERSECTION")
    public String getStreetIntersection() {
        return streetIntersection;
    }

    public void setStreetIntersection(String streetIntersection) {
        this.streetIntersection = streetIntersection;
    }

    @Column(name="CITY_TOWN")
    public String getCityTown() {
        return cityTown;
    }

    public void setCityTown(String cityTown) {
        this.cityTown = cityTown;
    }
    
    
}
