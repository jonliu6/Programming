package org.freecode.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="todoUser")
@Table(name="todo_user")
public class User implements Serializable{

	@Id
	@Column
	@GeneratedValue(generator="user_id_generator", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="user_id_generator", sequenceName="user_id_seq")
	private Integer id;
	
	@Column(name="Login")
	private String login;
	
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@OneToMany(mappedBy="itemOwner") // the value of mappedBy should be the property name in the other class - in this case is itemOwner in TodoItem 
	@Cascade({CascadeType.ALL})
	private List<TodoItem> todoItems;
	
	public User() {
		
	}
	
	public User(String login, String givenName, String surname) {
		this.login = login;
		this.firstName = givenName;
		this.lastName = surname;
		this.todoItems = new ArrayList<TodoItem>();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<TodoItem> getTodoItems() {
		return todoItems;
	}
	public void setTodoItems(List<TodoItem> todoItems) {
		this.todoItems = todoItems;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
