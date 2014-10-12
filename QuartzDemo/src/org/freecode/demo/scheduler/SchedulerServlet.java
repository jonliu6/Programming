package org.freecode.demo.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.freecode.demo.jobs.DADJob;
import org.freecode.demo.jobs.OutageJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerServlet extends HttpServlet {

	private Logger log = Logger.getLogger(SchedulerServlet.class);
	// private SchedulerFactory schedulerFactory;
	private Scheduler scheduler;
	private String schedulerID;
	
	public void init() throws ServletException
	{
		try
		{
		    scheduler = getScheduler();
		    if ( scheduler != null )
		    {
		    	schedulerID = scheduler.getSchedulerName() + scheduler.getSchedulerInstanceId();
		    }
			
		    log.info("schduler.start() is invoked in init() from {" + schedulerID + "}...");
		    // System.out.println("schduler.start() is invoked in init()...");
		    scheduler.start();
		    
		    // read from properties and set up job details
		    loadJobInformation();
		    
		    prepareOutageJob();
		    prepareDadJob();
		    
		}
		catch ( SchedulerException se )
		{
			se.printStackTrace();
		}
		
	}
	
	public Scheduler getScheduler()
	{
		return SchedulerService.getInstance().getScheduler();
	}
	
	/**
	 * for loading job information from properties or database
	 */
	public void loadJobInformation()
	{
		log.info("loading job information from {" + schedulerID + "}...");		
	}
	
	/**
	 * 1st job executes from now for another 20 times with 15 seconds internal
	 */
	public void prepareOutageJob()
	{
		JobDetail job1 = new JobDetail("Outage Job", "CROW", OutageJob.class);
		job1.getJobDataMap().put("ServerNameAndPort", "localhost:8080");
		job1.getJobDataMap().put("FrequencyInSeconds", 3);
		Trigger trigger1 = new SimpleTrigger("Outage Job Trigger", "CROW", new Date(), null, 20, 15L * 1000L);
		
		try
		{
			job1.getJobDataMap().put("SchedulerID", "{" + schedulerID + "}");
		    scheduler.scheduleJob(job1,  trigger1);
		}
		catch( SchedulerException se )
		{
			se.printStackTrace();
		}
	}
	
	/**
	 * 2nd job executes from now for another 15 times with 10 seconds internal
	 */
	public void prepareDadJob()
	{
		JobDetail job2 = new JobDetail("DAD Job", "GIS", DADJob.class);
	    Calendar calStart = new GregorianCalendar();
	    calStart.setTime(new Date());
	    calStart.add(Calendar.SECOND, 3);
	    Trigger trigger2 = new SimpleTrigger("DAD Job Trigger", "GIS", calStart.getTime(), null, 15, 10L * 1000L);
	    
	    try
		{
		    scheduler.scheduleJob(job2,  trigger2);
		}
		catch( SchedulerException se )
		{
			se.printStackTrace();
		}
	}
}
