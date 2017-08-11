package org.freecode.demo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.freecode.demo.model.DataService;

public class SystemStatusReportDownloadServlet extends HttpServlet {
	
	private DataService dataService;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		System.out.println("ReportYear: " + String.valueOf(req.getParameter("reportYear")));
		Integer year = Integer.valueOf(String.valueOf(req.getParameter("reportYear")));
		Integer month = Integer.valueOf(String.valueOf(req.getParameter("reportMonth")));
		Integer day = Integer.valueOf(String.valueOf(req.getParameter("reportDate")));
		Integer hour = Integer.valueOf(String.valueOf(req.getParameter("reportHour")));
		
		Calendar cal = new GregorianCalendar();
		cal.set(year, month, day, hour, 0, 0);
		Date reportHour = cal.getTime();
		
		OutputStream anOutputStream = null;
		String reportData = getDataService().getSysStatRptData(reportHour);
					
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
		}
	}

	private DataService getDataService() {
		if (dataService == null) {
			dataService = new DataService();
		}
		
		return dataService;
	}
}
