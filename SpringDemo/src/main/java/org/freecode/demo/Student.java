package org.freecode.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Student {
	
	@Autowired(required=false) // this autowiring is optional; when no bean defined, go ahead with manually instantiation
	private Division div;

	private String studentId;
	
	private String studentName;
	
    public String getStudentId() {
		return studentId;
	}
    
    @Autowired
	/**
	 * this autowired setter also demonstrates Spring Express Language #{T(package.class).methodName()} calling static method
	 * and componentName/className.methodName() calling object method
	 * @param studentId
	 */
	public void setStudentId(@Value("#{division.getSchoolPrefix() + T(java.lang.Math).round(T(java.lang.Math).random() * 100000000)}") String studentId) {
		this.studentId = studentId;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	@Autowired
	/**
	 * this autowired setter also demonstrates Spring Expression Language #{} with string concatenation
	 * @param studentName
	 */
	public void setStudentName(@Value("#{'John' + ' Smith'}") String studentName) {
		this.studentName = studentName;
	}
	
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", div=" + div + "]";
	}
	
	
}
