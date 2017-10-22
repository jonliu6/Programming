package org.freecode.demo.model;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import oracle.spatial.geometry.JGeometry;

@Stateless
public class DemoDAO {
    private Logger log = Logger.getLogger(this.getClass().getName());
    
    @PersistenceContext(unitName="POWERON")
    private EntityManager em;
    
    private DataSource ds; // used for JDBC to PowerOn
    private DataSource ds_ors; // used for JDBC to ORS
    
    public DemoDAO() {
        InitialContext ctx;
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:/PoweronDEV"); // view from JBoss admin console
            ds_ors = (DataSource) ctx.lookup("java:/ORSUAT");
        } catch (NamingException e) {
            log.log(Level.SEVERE, "Could not initialize StatusReportManagerBean");
            e.printStackTrace();
        }
    }
    
    public List<TroubleOrder> findAllActiveOrders() {
        return em.createNamedQuery("TroubleOrder.findAllActive").getResultList(); 
    }
    
    public List<CrewLocation> findCrewLocations() {
        return em.createNamedQuery("CrewLocation.FindAllCrewLocations").getResultList();
    }
    
    public Integer findOrderCount() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TroubleOrder> troubleOrders = null;
        Integer total = null;
        String sqlStr = "SELECT ORDER_ID, REFERENCE_LABEL, TYPE, PRIORITY, STATUS FROM PO_RM_BCH_TO_VIEW_NO_ARCH WHERE UPPER(STATUS) NOT IN ('FIELD_COMPLETE','CLOSED', 'ARCHIVED','CANCELLED') ";
        
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sqlStr);
            rs = ps.executeQuery();
            if ( rs != null ) {
                troubleOrders = new ArrayList();
                while (rs.next()) {
                    TroubleOrder to = new TroubleOrder();
                    to.setOrderId(rs.getInt("Order_Id"));
                    to.setReferenceLabel(rs.getString("Reference_Label"));
                    to.setType(rs.getString("Type"));
                    to.setPriority(rs.getInt("Priority"));
                    to.setStatus(rs.getString("Status"));
                    
                    troubleOrders.add(to);
                }
                rs.close();
            }
        }
        catch ( SQLException sqle ) {
            sqle.printStackTrace();
        }
        finally {
            rs = null;
            try {
                if ( ps != null ) {
                    ps.close();
                }
                
                if ( conn != null ) {
                    conn.close();
                }
            }
            catch ( SQLException sqle ) {
                sqle.printStackTrace();
            }
            finally {
                ps = null;
                conn = null;
            }
        }
        
        if ( troubleOrders != null ) {
            total = troubleOrders.size();
        }
        
        return total;
    }
    
    public List<OrderLocation> findAllOrderLocations() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlStr = "SELECT q.Order_Reference_Label, q.ORDER_ID, q.OUTAGE_INCIDENT_ID, q.Region, q.Municipality, " +
                        "   SDO_CS.TRANSFORM(AREA, 8307) AS Area, p.MIN_X, p.MIN_Y, p.MAX_X, p.MAX_Y, p.CENTRE_X, p.CENTRE_Y  FROM ORS_OUTAGE_POLYGON_VW p " +
                        "   INNER JOIN ORS_UNPLANNED_OUTAGES q ON p.ORDER_ID=q.ORDER_ID WHERE p.area IS NOT NULL";
        List<OrderLocation> orderLocations = null;
        List<Coordinate> areaCoords = null;
        
        try {
            conn = ds_ors.getConnection();
            ps = conn.prepareStatement(sqlStr);
            rs = ps.executeQuery();
            if ( rs != null ) {
                orderLocations = new ArrayList();
                while (rs.next()) {
                    OrderLocation ol = new OrderLocation();
                    ol.setReferenceId(rs.getString("Order_Reference_Label"));
                    ol.setOrderId(rs.getInt("ORDER_ID"));
                    ol.setIncidentId(rs.getInt("OUTAGE_INCIDENT_ID"));
                    ol.setRegion(rs.getString("Region"));
                    ol.setMunicipality(rs.getString("Municipality"));
                    Coordinate minCoord = new Coordinate(rs.getDouble("MIN_Y"), rs.getDouble("MIN_X"));
                    ol.setMinCoord(minCoord);
                    Coordinate maxCoord = new Coordinate(rs.getDouble("MAX_Y"), rs.getDouble("MAX_X"));
                    ol.setMaxCoord(maxCoord);
                    Coordinate centerCoord = new Coordinate(rs.getDouble("CENTRE_Y"), rs.getDouble("CENTRE_X"));
                    ol.setCenterCoord(centerCoord);
                    
                    System.out.println(ol.getReferenceId());
                    
                    // STRUCT area = (STRUCT) rs.getObject("Area"); // not know why ClassCastException in new JDBC lib and JDK, cannot cast oracle.sql.STRUCT to oracle.sql.STRUCT
                    byte[] area = rs.getBytes("Area"); 
                    
                    if (area != null ) {
                        JGeometry geo = JGeometry.load(area); 
                        double[] coordArray = geo.getOrdinatesArray();
                        if ( coordArray != null && coordArray.length > 0 ) {
                            areaCoords = new ArrayList<Coordinate>();
                            for (int i =0; i < coordArray.length; ++i) {
                                double lng = coordArray[i];
                                ++i;
                                double lat = coordArray[i];
                                Coordinate coord = new Coordinate(lat, lng);
                                areaCoords.add(coord);
                                // System.out.println("\t" + coord.getText());
                            }
                        }
                        
                        ol.setArea(areaCoords);
                    }
                    
                    orderLocations.add(ol);
                }
                rs.close();
            }
        }
        catch ( SQLException sqle) {
            sqle.printStackTrace();
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
        finally {
            rs = null;
            try {
                if ( ps != null ) {
                    ps.close();
                }
                
                if ( conn != null ) {
                    conn.close();
                }
            }
            catch ( SQLException sqle ) {
                sqle.printStackTrace();
            }
            finally {
                ps = null;
                conn = null;
            }
        }
        
        return orderLocations;
    }
    
    public String retrieveSysStatRptArchByTime(Date aDate) {
    	StringBuilder sbuf = new StringBuilder();
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(aDate);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	
    	Timestamp ts = new Timestamp(cal.getTimeInMillis());
    	
    	Connection conn = null;
    	CallableStatement cs = null;
    	Clob rptData = null;
    	
    	try {
    		conn = ds.getConnection();
    		cs = conn.prepareCall(" { call Archiver.Po_Bch_Rpt_Archiver.Prc_View_Sys_Status_Csv_Arch(?, ?) } ");
    		cs.setTimestamp(1, ts);
    		cs.registerOutParameter(2, Types.CLOB);
    		cs.execute();
    		rptData = cs.getClob(2);
    		if (rptData != null) {
    			sbuf.append(rptData.getSubString(1, (int) rptData.length())); 
    		}
    	}
    	catch (SQLException sqle) {
    		sqle.printStackTrace();
    	}
    	finally {
    		try {
	    		if (conn != null && conn.isClosed() == false) {
	    			conn.close();
	    		}
    		}
    		catch (SQLException sqle1) {
    			sqle1.printStackTrace();
    		}
    		finally {
    			conn = null;
    		}
    	}
    	
    	
    	return sbuf.toString();
    }
}
