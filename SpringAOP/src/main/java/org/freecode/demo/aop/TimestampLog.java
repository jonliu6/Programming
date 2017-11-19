package org.freecode.demo.aop;

import java.util.Calendar;

public class TimestampLog {

	public void printOutSystemTime() {
		Calendar c = Calendar.getInstance();
		if (c != null) {
			System.out.println("Current Time ==> " +
		                       c.get(Calendar.HOUR_OF_DAY) + ":" + 
		                       c.get(Calendar.MINUTE) + ":" +
					           c.get(Calendar.SECOND) + "." + 
		                       c.get(Calendar.MILLISECOND));
		}
	}
}
