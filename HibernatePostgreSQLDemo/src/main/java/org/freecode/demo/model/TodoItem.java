package org.freecode.demo.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cacheable
@Cache(region="TodoItem", usage=CacheConcurrencyStrategy.READ_WRITE)
@Entity(name="TodoItem")
@Table(name="Todo_Item")
public class TodoItem implements Serializable{

	@Id
	@Column(name="Id")
	private Long id;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Description")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
}
