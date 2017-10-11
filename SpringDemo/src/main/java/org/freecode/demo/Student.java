package org.freecode.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Student {

	private Integer studentId;
	
	private String studentName;
	
    private Integer divId;
	public Integer getStudentId() {
		return studentId;
	}
	
	@Autowired
	public void setStudentId(@Value("00001111") Integer studentId) {
		this.studentId = studentId;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	@Autowired
	public void setStudentName(@Value("John Smith") String studentName) {
		this.studentName = studentName;
	}
	public Integer getDivId() {
		return divId;
	}
	
	@Autowired
	public void setDivId(@Value("1234") Integer divId) {
		this.divId = divId;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", divId=" + divId + "]";
	}
	
	
}
