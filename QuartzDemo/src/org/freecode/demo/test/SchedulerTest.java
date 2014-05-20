package org.freecode.demo.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.freecode.demo.jobs.DADJob;
import org.freecode.demo.jobs.OutageJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerTest {
	private SchedulerFactory schedulerFactory;
	private Scheduler scheduler;
	
	public static void main(String[] args)
	{
		SchedulerTest test = new SchedulerTest();
		test.startScheduledJobs();
	}
	
	public void startScheduledJobs()
	{
		try
		{
		    schedulerFactory = new StdSchedulerFactory();
		    scheduler = schedulerFactory.getScheduler();
		    scheduler.start();
		    
		    // execute jobs
		    prepareOutageJob();
		    prepareDadJob();
		    
		}
		catch ( SchedulerException se )
		{
			se.printStackTrace();
		}
	}
	
	public void prepareOutageJob( )
	{
		JobDetail job1 = new JobDetail("Outage Job", "CROW", OutageJob.class);
		job1.getJobDataMap().put("ServerNameAndPort", "localhost:8080");
		job1.getJobDataMap().put("FrequencyInSeconds", 3);
		Trigger trigger1 = new SimpleTrigger("Outage Job Trigger", "CROW", new Date(), null, 3, 15L * 1000L);
		
		try
		{
			job1.getJobDataMap().put("SchedulerID", scheduler.getSchedulerName() + "(" + scheduler.getSchedulerInstanceId() +")");
		    scheduler.scheduleJob(job1,  trigger1);
		}
		catch( SchedulerException se )
		{
			se.printStackTrace();
		}
	}
	
	public void prepareDadJob()
	{
		JobDetail job2 = new JobDetail("DAD Job", "GIS", DADJob.class);
	    Calendar calStart = new GregorianCalendar();
	    calStart.setTime(new Date());
	    calStart.add(Calendar.SECOND, 3);
	    Trigger trigger2 = new SimpleTrigger("DAD Job Trigger", "GIS", calStart.getTime(), null, 2, 30L * 1000L);
	    
	    try
		{
	    	//job2.getJobDataMap().put("SchedulerID", scheduler.getSchedulerName() + "(" + scheduler.getSchedulerInstanceId() + ")" );
		    scheduler.scheduleJob(job2,  trigger2);
		}
		catch( SchedulerException se )
		{
			se.printStackTrace();
		}
	}
	
}
