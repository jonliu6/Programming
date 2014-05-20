package org.freecode.demo.entity;

import java.util.List;
import java.util.Set;

public class StudentClass {
	private String className;
	private int classId;
	private Set<Student> students;
	private List<StudyGroup> studyGroups;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	public List<StudyGroup> getStudyGroups() {
		return studyGroups;
	}
	public void setStudyGroups(List<StudyGroup> studyGroups) {
		this.studyGroups = studyGroups;
	}
	
	

}
