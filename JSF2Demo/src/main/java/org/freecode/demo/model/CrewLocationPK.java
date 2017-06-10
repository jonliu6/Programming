package org.freecode.demo.model;

import java.io.Serializable;

/**
 * composite key for CrewLocation
 *
 */
public class CrewLocationPK implements Serializable {
    private Long crewId;
    private String bchMDTNo;

    public Long getCrewId() {
        return crewId;
    }

    public void setCrewId(Long crewId) {
        this.crewId = crewId;
    }

    public String getBchMDTNo() {
        return bchMDTNo;
    }

    public void setBchMDTNo(String bchMDTNo) {
        this.bchMDTNo = bchMDTNo;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            if (obj == this) {
                return true;
            } else {
                if (obj instanceof CrewLocationPK) {
                    return ((CrewLocationPK) obj).getCrewId().equals(
                            this.crewId)
                            && ((CrewLocationPK) obj).getBchMDTNo().equals(
                                    this.bchMDTNo);
                } else {
                    return false;
                }
            }
        }
    }

}
