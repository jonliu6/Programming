package org.freecode.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Spring Boot application for Async tasks from online course Spring Boot Microservices by Janavi Ravi
 *
 */
@SpringBootApplication
@EnableAsync
public class AsyncApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(AsyncApp.class, args).close(); // once Async application starts close it
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3); // 2 threads in pool by default
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("DemoThread-");
        executor.initialize();

        return executor;
    }
}
