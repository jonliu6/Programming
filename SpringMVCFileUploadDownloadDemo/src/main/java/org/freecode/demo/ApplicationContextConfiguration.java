package org.freecode.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Spring configuration file holding bean definitions for Spring Application, not specific for Spring MVC
 * replaced WEB-INF/application-context.xml
 * replaced by SpringMvcConfigurer
 */
//@Configuration
public class ApplicationContextConfiguration {
//    @Bean(name="viewResolvers")
//    public InternalResourceViewResolver getViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/"); // set to web root folder. webapp in this case
//        viewResolver.setSuffix(".jsp");
//
//        return viewResolver;
//    }
//
//    @Bean(name="/")
//    // handle the mapping to the web root folder
//    public Controller getMessageDisplayController() {
//        return new MessageDisplayController();
//    }
}
