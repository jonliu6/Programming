package org.freecode.demo.jobs;

import org.quartz.Job;
import org.quartz.Scheduler;

public abstract class AbstractJob implements Job{

	protected Scheduler theScheduler;
	
	public void setScheduler( Scheduler aScheduler )
	{
		theScheduler = aScheduler;
	}
}
