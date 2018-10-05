package org.freecode.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeConverter {

	public static void main(String[] args) {
		//Calendar cal = new GregorianCalendar();
		String gmtTmStr = "2018-01-30 11:45:30";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfPST = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		sdfPST.setTimeZone(TimeZone.getTimeZone("PST"));
		Date aDate = null;
		Date localDate = null;
		try {
			aDate = sdf.parse(gmtTmStr);
			localDate = sdfPST.parse(gmtTmStr);
		}
		catch (ParseException pe) {
			pe.printStackTrace();
		}
		// cal.setTime(aDate);
		System.out.println("UTC: " + aDate + ", PST: " + localDate);
		
		//long ms = cal.getTimeInMillis();
		long ms = aDate.getTime();
		Calendar cal2 = new GregorianCalendar(TimeZone.getTimeZone("PST"));
		cal2.setTimeInMillis(ms);
		System.out.println("PST: " + cal2.getTime());
	}
}
