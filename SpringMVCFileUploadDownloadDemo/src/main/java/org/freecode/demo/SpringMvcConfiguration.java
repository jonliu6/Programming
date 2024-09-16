package org.freecode.demo;

import java.util.Properties;

import org.freecode.demo.controller.FileUploadController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Define configuration for Spring MVC, replacing ApplicationContextConfiguration
 */
@EnableWebMvc
@ComponentScan(basePackages = "org.freecode.demo")
// classes defined in this package will be automatically instantiated and injected - including @Controller classes.
/**
 * This replaces spring-servlet.xml - which specified as "<servlet>spring</servlet>" in web.xml
 * Alternative XML configuration in spring-servlet.xml:
 * <context:annotation-config></context:annotation-config>
 * <context:component-scan back-package="org.freecode.demo"></context:component-scan>
 * <mvc:annotation-driven />
 * <bean ...></bean>
 */
@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {
    /**
     * InternalResourceViewResolver maps the logic names to physical file names based on prefix and suffix. e.g. home to /home.jsp
     * @return
     * Note: must have @Bean for Spring Inversion of Control
     * this definition replaces the below option in the XML configuration - spring-servlet.xml
     * Alternative XML configuration:
     * <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     *     <property name="prefix" value="/view/"></property>
     *     <property name="suffix" value=".jsp"></property>
     * </bean>
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/view/"); // prefix of Spring Views, in web root folder
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    /**
     * this definition replaces the below option in the XML configuration - spring-servlet.xml
     * Alternative XML configuration:
     * <bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
     *     <property name="exceptionMappings">
     *         <props>
     *             <prop key="java.lang.RuntimeException">runtimeError</prop>
     *         </props>
     *     </property>
     *     <property name="defaultErrorView" value="error"></property>
     * </bean>
     */
    public SimpleMappingExceptionResolver runtimeExceptionResolver() {
        SimpleMappingExceptionResolver exResolver = new SimpleMappingExceptionResolver();
        Properties props = new Properties();
        props.put("java.lang.RuntimeException", "runtimeError"); // when catching RuntimeException, navigate to runtimeError.jsp
        exResolver.setExceptionMappings(props);
        exResolver.setDefaultErrorView("error"); // default to error.jsp

        return exResolver;
    }

    @Bean
    /**
     * Apache Commons-fileupload is replaced in Spring Framework 6.x
     * <bean id="multipartResolver" class="import org.springframework.web.multipart.commons.CommonsMultipartResolver">
     *     <property name="maxUploadSize" value="10485760"></property> <!-- 10MB -->
     *     <property name="maxInMemorySize" value="1048576" /> <!-- 1MB -->
     * </bean>
     * Alternative XML Configuration (e.g. <servlet-name>-servlet.xml)
     * <bean id="multipartResolver" class="org.springframework.web.multipart.support.StandardServletMultipartResolver">
     * </bean>
     */
    public MultipartResolver multipartResolver() {
    	StandardServletMultipartResolver mPartResolver = new StandardServletMultipartResolver();
        return mPartResolver;
    }    
}
