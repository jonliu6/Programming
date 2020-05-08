package org.freecode.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App 
{
	/**
	 * This is a Maven SpringBoot application using in-memory h2database and RESTful APIs to demonstrate CRUD.
	 * to run, type "mvn spring-boot:run" in command window.
	 * to test, access the GET method using web browser. http://localhost:9090/notes
	 * the web service port can be defined in application.properties
	 * @param args
	 */
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
