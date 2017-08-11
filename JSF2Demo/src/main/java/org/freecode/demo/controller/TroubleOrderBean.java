package org.freecode.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.freecode.demo.model.DataService;
import org.freecode.demo.model.TroubleOrder;

@ManagedBean(name="troubleOrderBean")
@SessionScoped
public class TroubleOrderBean implements Serializable {
    private List<TroubleOrder> troubleOrders;
    private Integer orderCount; // for demo JDBC purpose
    private DataService dataService;
    private int first = 0;
    private int rows = 10;
    private List<String> filters = null;
    private String newFilter = null;
    
    public TroubleOrderBean() {
        dataService = new DataService();
        troubleOrders = dataService.findAllActiveOrders();
        orderCount = dataService.findOrderCount();
    }
    
    public List<TroubleOrder> getTroubleOrders() {
        return troubleOrders;
    }
    
    public Integer getOrderCount() {
        return orderCount;
    }

    public int getFirst() {
    	return first;
    }
    
    public void setFirst(int idx) {
    	first = idx;
    }
    
    public int getRows() {
    	return rows;
    }
    
    public String getNewFilter() {
		return newFilter;
	}

	public void setNewFilter(String newFilter) {
		this.newFilter = newFilter;
	}

	public void addFilter() {
    	if ( filters == null ) {
    		filters = new ArrayList<String>();
    	}
    	if ( newFilter != null ) {
    	  filters.add(newFilter);
    	  newFilter = null;
    	}
    	
    	System.out.println(filters);
    	System.out.println("new Filter: " + newFilter);
    }
	
	private boolean sortAscending = true;
	
	public void sortOrderLabel() {
		if (sortAscending) {
			Collections.sort(troubleOrders, new Comparator<TroubleOrder>() {
			    public int compare(TroubleOrder o1, TroubleOrder o2) {
			    	return o1.getReferenceLabel().compareTo(o2.getReferenceLabel());
			    }
			});
		}
		else {
			Collections.sort(troubleOrders, new Comparator<TroubleOrder>() {
			    public int compare(TroubleOrder o1, TroubleOrder o2) {
			    	return o2.getReferenceLabel().compareTo(o1.getReferenceLabel());
			    }
			});
		}
		sortAscending = !sortAscending;
	}
	
	/**
     * fields for System Status Report
     */
    private Integer reportYear = 2017;
    private Integer reportMonth = 5;
    private Integer reportDate = 21;
    private Integer reportHour = 18;

	public Integer getReportYear() {
		return reportYear;
	}

	public void setReportYear(Integer reportYear) {
		this.reportYear = reportYear;
	}

	public Integer getReportMonth() {
		return reportMonth;
	}

	public void setReportMonth(Integer reportMonth) {
		this.reportMonth = reportMonth;
	}

	public Integer getReportDate() {
		return reportDate;
	}

	public void setReportDate(Integer reportDate) {
		this.reportDate = reportDate;
	}

	public Integer getReportHour() {
		return reportHour;
	}

	public void setReportHour(Integer reportHour) {
		this.reportHour = reportHour;
	}

	public void downloadReport() {
		if (reportYear != null && reportMonth != null && reportDate != null && reportHour != null) {
			
			
			Calendar cal = new GregorianCalendar();
			cal.set(reportYear, reportMonth, reportDate, reportHour, 0, 0);
			Date reportHour = cal.getTime();
			String reportData = dataService.getSysStatRptData(reportHour);
			
			ServletOutputStream anOutputStream = null;
			final FacesContext fc = FacesContext.getCurrentInstance();
			final ExternalContext ec = fc.getExternalContext();
			HttpServletResponse resp = (HttpServletResponse) ec.getResponse();
						
			try {
				resp.setHeader( "Content-Disposition", "attachment;filename=SystemStatusHistoryReport_" + String.valueOf(reportHour) + ".csv" );
	            // resp.setContentType( "text/csv" );
				resp.setContentType("application/octet-stream");
	            anOutputStream = resp.getOutputStream();
	            
	            anOutputStream.write(reportData.getBytes());
	            anOutputStream.flush();
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
			finally {
				if (anOutputStream != null) {
					try {
						anOutputStream.close();
					}
					catch (IOException ioe1) {
						ioe1.printStackTrace();
					}
					finally {
						anOutputStream = null;
					}
				}
				fc.getResponseComplete();
			}
		}
		
	}
}
