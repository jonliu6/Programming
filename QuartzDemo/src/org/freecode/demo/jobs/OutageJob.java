package org.freecode.demo.jobs;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class OutageJob extends AbstractJob {

	private Logger log = Logger.getLogger(this.getClass());
	
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		JobDataMap jobData = context.getJobDetail().getJobDataMap();
		
		String serverName = jobData.getString("ServerNameAndPort");
		Integer frequency = jobData.getInt("FrequencyInSeconds");
		String schedulerID = jobData.getString("SchedulerID");
		log.info("OutageJob from " + schedulerID + "is executed on " + serverName + " every " + frequency + " seconds.");
		
	}
}
