package org.freecode.demo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * This class is the Java-based configuration for Spring applications
 * EnableWebMvc is mvc:annotation-driven in *-servlet.xml
 */
@EnableWebMvc
@ComponentScan(basePackages="org.freecode.demo.controller")
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	/**
	 * equivalent to the definition in *-servlet.xml 
	 */
	public InternalResourceViewResolver getIRVR() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/view/");
		irvr.setSuffix(".jsp");
		return irvr;
	}
	
	@Override
	/**
	 * replace the mvc:resources defined in *-servlet.xml
	 */
	public void addResourceHandlers(ResourceHandlerRegistry reg) {
		reg.addResourceHandler("/staticResources/**").addResourceLocations("/resources/");
	}

}
