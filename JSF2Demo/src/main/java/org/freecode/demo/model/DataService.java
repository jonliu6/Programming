package org.freecode.demo.model;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@RequestScoped
@Named(value="dataService")
public class DataService {
    //@EJB // not work
    private DemoDAO dataDAO;
    private static String JNDI_NAME = "java:global/JSF2Demo/DemoDAO"; // view from JBoss console or admin console
    private Context ctx;
    
    public DataService() {
        try {
            ctx = new InitialContext();
            dataDAO = (DemoDAO) ctx.lookup(JNDI_NAME);
        }
        catch ( NamingException ne ) {
            ne.printStackTrace();
        }
        
    }
    
    public List<TroubleOrder> findAllActiveOrders() {
        return dataDAO.findAllActiveOrders();
    }
    
    public Integer findOrderCount() {
        return dataDAO.findOrderCount();
    }
    
    public List<CrewLocation> findCrewLocations() {
        return dataDAO.findCrewLocations();
    }
    
    public List<OrderLocation> findAllOrderLocations() {
        return dataDAO.findAllOrderLocations();
    }
    
    public String getSysStatRptData(Date aDate) {
    	return dataDAO.retrieveSysStatRptArchByTime(aDate);
    }
}
