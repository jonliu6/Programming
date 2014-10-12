package org.freecode.demo.entity;

public class StudyGroup {
	private int groupId;
	private String groupName;
	private StudentClass studentClass;
	
	public StudyGroup()
	{
		
	}
	
	public StudyGroup(int anId, String aName )
	{
		groupId = anId;
		groupName = aName;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public StudentClass getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(StudentClass studentClass) {
		this.studentClass = studentClass;
	}
	
	

}
