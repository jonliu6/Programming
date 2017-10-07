package org.freecode.demo;

public class Switch extends Part{

	private int partNo;
	private String partName;
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
	@Override
	public String toString() {
		return "Switch [partNo=" + partNo + ", partName=" + partName + "]";
	}
	
}
