package org.freecode.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 
 * 
 */
public class StaffMeeting {

	@Autowired
	@Qualifier(value="admin") // since teacher and admin are all Staff, qualifier specifics which one to autowire
	private Staff organizer;

	public Staff getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Staff organizer) {
		this.organizer = organizer;
	}
	
	public StaffMeeting() {
		System.out.println("Organizer initialized.");
	}
	
	public StaffMeeting(Staff aStaff) {
		organizer = aStaff;
	}
	
	public void getMeetingOrganizer() {
		if (organizer == null) {
			System.out.println("Meeting does not have an organizer.");
		}
		else {
			System.out.println("Meeting is organized by: " + organizer);
		}
	}
}
