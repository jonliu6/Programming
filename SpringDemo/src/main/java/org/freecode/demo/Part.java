package org.freecode.demo;

import java.util.List;

public class Part {

	private int partNo;
	private String partName;
	private List<String> vendors;
	
	public Part() {
		
	}
	
	public int getPartNo() {
		return partNo;
	}

	public void setPartNo(int partNo) {
		this.partNo = partNo;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public Part(int partNo, String partName) {
		this.partNo = partNo;
		this.partName = partName;
	}
	
	public List<String> getVendors() {
		return vendors;
	}
    
	public void setVendors(List<String> vendors) {
		this.vendors = vendors;
	}



	@Override
	public String toString() {
		return "Part [partNo=" + partNo + ", partName=" + partName + "]";
	}
	
	
}
