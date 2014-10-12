package org.freecode.demo;

public class Part {

	private int partNo;
	private String partName;
	
	
	public Part(int partNo, String partName) {
		this.partNo = partNo;
		this.partName = partName;
	}
	
	@Override
	public String toString() {
		return "Part [partNo=" + partNo + ", partName=" + partName + "]";
	}
	
	
}
