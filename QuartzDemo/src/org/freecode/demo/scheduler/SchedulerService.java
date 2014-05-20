package org.freecode.demo.scheduler;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerService {

	private static SchedulerService theInstance;
	private Scheduler theScheduler;
	private Logger log = Logger.getLogger(this.getClass());
	
	static
	{
	    theInstance = new SchedulerService();
	}
	
	private SchedulerService()
	{
		
	}
	
	public static SchedulerService getInstance()
	{
		return theInstance;
	}
	
	public Scheduler getScheduler() {
		if ( theScheduler == null )
		{
			try
			{
			    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			    theScheduler = schedulerFactory.getScheduler();
			}
			catch ( SchedulerException se )
			{
				
			}
		}
		return theScheduler;
	}

	public void setScheduler(Scheduler aScheduler) {
		this.theScheduler = aScheduler;
	}
	
	
}
