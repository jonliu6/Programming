package org.freecode.demo.jobs;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DADJob extends AbstractJob {

    private Logger log = Logger.getLogger(this.getClass());
	
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		log.info("DAD Job is executed.");
	}
}
