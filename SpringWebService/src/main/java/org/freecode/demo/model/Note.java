package org.freecode.demo.model;

import java.io.Serializable;

public class Note implements Serializable{

	private String title;
	private String category;
	private String subCategory;
	private String description;
	
	public Note() {
		
	}
	
	public Note(String title, String category, String subCategory, String description) {
		this.title = title;
		this.category = category;
		this.subCategory = subCategory;
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}
