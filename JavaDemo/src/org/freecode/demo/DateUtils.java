package org.freecode.demo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {
	
	public static void main(String[] args) {
		
		DateUtils dateUtils = new DateUtils();
		LocalDate aDay = null;
		if (args != null && args.length > 0) {
			try {
				aDay = LocalDate.parse(args[0]);
			}
			catch (DateTimeParseException dtpe) {
				System.out.println("Usage: java DateUtil <yyyy-mm-dd>");
				System.exit(-1);
			}
		}
		else {
		    aDay = LocalDate.now();		
		}
		
		System.out.println(dateUtils.getNextBusinessDay(aDay));
		
	}
	
	/**
	 * get the next business day, excluding weekends and holidays
	 * @param aDate
	 * @return
	 */
	public LocalDate getNextBusinessDay(LocalDate aDate) {
		
		LocalDate nextBusinessDay = aDate.plusDays(1);
		
		DayOfWeek dayOfWeek = nextBusinessDay.getDayOfWeek();
		if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
			nextBusinessDay = nextBusinessDay.plusDays(2);
		}
		else if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
			nextBusinessDay = nextBusinessDay.plusDays(1);
		}
		
		if (getHolidaysOf2025().contains(nextBusinessDay)) {
			return getNextBusinessDay(nextBusinessDay);
		}
		
		return nextBusinessDay;
	}

	/**
	 * get a holiday list of 2025
	 * @return
	 */
	public List<LocalDate> getHolidaysOf2025() {
		List<LocalDate> holidays = new ArrayList<>();
		holidays.add(LocalDate.of(2025, 1, 1));
		holidays.add(LocalDate.of(2025, 2, 17));
		holidays.add(LocalDate.of(2025, 4, 18));
		holidays.add(LocalDate.of(2025, 4, 21));
		holidays.add(LocalDate.of(2025, 5, 19));
		holidays.add(LocalDate.of(2025, 7, 1));
		holidays.add(LocalDate.of(2025, 8, 3));
		holidays.add(LocalDate.of(2025, 9, 1));
		holidays.add(LocalDate.of(2025, 9, 30));
		holidays.add(LocalDate.of(2025, 10, 11));
		holidays.add(LocalDate.of(2025, 11, 11));
		holidays.add(LocalDate.of(2025, 12, 25));
		holidays.add(LocalDate.of(2025, 12, 26));
		return holidays;
	}
}
