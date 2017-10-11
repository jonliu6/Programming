package org.freecode.demo;

public class Division {
    Integer divId;
    String divName;
	public Integer getDivId() {
		return divId;
	}
	public void setDivId(Integer divId) {
		this.divId = divId;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	@Override
	public String toString() {
		return "Division [divId=" + divId + ", divName=" + divName + "]";
	}
    
    
}
