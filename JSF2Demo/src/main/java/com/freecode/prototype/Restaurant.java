package com.freecode.prototype;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "restaurantBean")
//@RequestScoped
//@ApplicationScoped
@SessionScoped
public class Restaurant {
  private String name;
  private String address;
public String getName() {
	return name;
}
public void setName(String aName) {
	this.name = aName;
}
public String getAddress() {
	return address;
}
public void setAddress(String anAddress) {
	this.address = anAddress;
}
  
  
}
