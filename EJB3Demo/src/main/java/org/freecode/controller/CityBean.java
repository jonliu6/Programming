package org.freecode.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.freecode.model.CityEntity;
import org.freecode.model.CityFacade;

@ManagedBean(name="cityBean")
@SessionScoped
public class CityBean {
  @EJB
  private CityFacade cityFacade;
  private CityEntity cityEntity = new CityEntity();
  
  public List<CityEntity> findCities()
  {
	  return cityFacade.findCities();
  }
}
