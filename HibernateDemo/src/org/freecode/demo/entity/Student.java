package org.freecode.demo.entity;

public class Student {
	
	private int studentId;
	private String studentName;
	private StudentClass studentClass;
	
	public Student()
	{
		
	}
	
	public Student( int anId, String aName )
	{
		studentId = anId;
		studentName = aName;
	}
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
		
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public StudentClass getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(StudentClass studentClass) {
		this.studentClass = studentClass;
	}
	
	

}
