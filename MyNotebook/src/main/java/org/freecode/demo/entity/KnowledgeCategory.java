package org.freecode.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="knowledgeCategory")
@Table(name="t_knowledge_category")
public class KnowledgeCategory implements Serializable {
	@Id
	@Column(name="id")
    private int categoryId;
	
	@Column(name="name")
    private String categoryName;
	
	@Column(name="parent_id")
    private int categoryParentId;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryParentId() {
		return categoryParentId;
	}

	public void setCategoryParentId(int categoryParentId) {
		this.categoryParentId = categoryParentId;
	}
        
}
