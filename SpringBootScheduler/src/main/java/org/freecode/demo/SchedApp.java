package org.freecode.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot Microservices from skillport.com by Janani Ravi
 * @EnableScheduling will run task as schedule at intervals with task executor on background thread
 * application has to run all the time
 */
@SpringBootApplication
@EnableScheduling
public class SchedApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(SchedApp.class, args);
    }
}
