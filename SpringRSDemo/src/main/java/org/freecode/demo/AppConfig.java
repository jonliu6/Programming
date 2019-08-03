package org.freecode.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Application Configuration by Spring Framework
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="org.freecode.demo")
public class AppConfig 
{
    
}
