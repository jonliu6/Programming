package org.freecode.demo.model;

public class KnowledgePoint {
	private String title;
	private String description;
	private String category;
	
	public KnowledgePoint() {
		
	}
	
	public KnowledgePoint(String kpTitle, String kpDescription, String kpCategory) {
		this.title = kpTitle;
		this.description = kpDescription;
		this.category = kpCategory;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "KnowledgePoint [title=" + title + ", description=" + description + ", category=" + category + "]";
	}
	
}
