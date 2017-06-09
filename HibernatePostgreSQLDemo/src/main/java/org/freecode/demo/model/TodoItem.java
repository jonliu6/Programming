package org.freecode.demo.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;

@Cacheable
@Cache(region="TodoItem", usage=CacheConcurrencyStrategy.READ_WRITE)
@Entity(name="TodoItem")
@Table(name="Todo_Item")
@FilterDef(name="itemTitleFilter", parameters={
		@ParamDef(name="titleParam", type="string")
})
@Filters({
	@Filter(name="itemTitleFilter", condition="LOWER(title) LIKE :titleParam")
})
public class TodoItem implements Serializable{

	@Id
	@Column(name="Id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_generator") // Oracle uses SEQUENCE strategy
	@SequenceGenerator(name="id_generator", sequenceName="todo_item_id_seq")
	private Long id;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="UserId") // column id associated to another table
	private User itemOwner;
	
	public TodoItem() {}
	public TodoItem(String title, String desc) {
		this.title = title;
		this.description = desc;
	}

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

	public User getItemOwner() {
		return itemOwner;
	}

	public void setItemOwner(User itemOwner) {
		this.itemOwner = itemOwner;
	}
	
	@Override
	public String toString() {
		return "TodoItem [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
		
}
