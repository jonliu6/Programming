package org.freecode.demo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * This is a sample to test Java-based Configuration using Spring framework 
 */
public class JavaConfigAppDemo {

	public static void main(String[] args) {

		ApplicationContext appCfg = new AnnotationConfigApplicationContext(JavaAppConfig.class); // load Java-based configuration class
        Teacher aTeacher = (Teacher) appCfg.getBean("teacher", Teacher.class);
        aTeacher.work();
        Administrator admin = (Administrator) appCfg.getBean("admin", Administrator.class);
        admin.work();
		
		StaffMeeting mt = (StaffMeeting) appCfg.getBean("staffMeeting", StaffMeeting.class);
		mt.getMeetingOrganizer();
		
		((AnnotationConfigApplicationContext) appCfg).close();
	}

}
