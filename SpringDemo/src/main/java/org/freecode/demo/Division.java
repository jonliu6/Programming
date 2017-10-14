package org.freecode.demo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // optional annotation
public class Division {
    Integer divId;
    String divName;
	public Integer getDivId() {
		return divId;
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Division initialized");
	}
	
	@PreDestroy
	public void dispose() {
		System.out.println("Division destroyed");
	}
	
	@Autowired
	/**
	 * this method demonstrates Spring Expression Language #{} for static method
	 * @param divId
	 */
	public void setDivId(@Value("#{T(java.lang.Math).random() * 1000}") Integer divId) {
		this.divId = divId;
	}
	public String getDivName() {
		return divName;
	}
	
	public String getSchoolPrefix() {
		return "MTN";
	}
	
	public static String getDivPrefix() {
		return  "Grade";
	}
	
	@Autowired
	/**
	 * In Spring Expression Language, use #{T(package.class).methodName()} for invoking static method
	 * @param divName
	 */
	public void setDivName(@Value("#{T(org.freecode.demo.Division).getDivPrefix() + ' 8'}") String divName) {
		this.divName = divName;
	}
	@Override
	public String toString() {
		return "Division [divId=" + divId + ", divName=" + divName + "]";
	}
    
    
}
