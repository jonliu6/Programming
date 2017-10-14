package org.freecode.demo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This is a Java-based configuration file instead of spring application context file
 * 
 */
@Configuration
@ComponentScan({"demo"}) // use the component scan in the given package
public class JavaAppConfig {

	@Bean(name="teacher") // name used for initialization
	public Teacher getTeacher() {
		return new Teacher();
	}
	
	@Bean(name="admin") // name used for initialization
	public Administrator getAdministrator() {
		return new Administrator();
	}
	
	@Bean(name="staffMeeting")
	public StaffMeeting getStaffMeeting() {
		StaffMeeting meet = new StaffMeeting();
		return meet;
	}
}
