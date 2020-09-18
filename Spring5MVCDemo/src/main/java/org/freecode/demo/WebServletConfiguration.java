package org.freecode.demo;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Spring MVC specific configuration, replacing some configuration in web.xml
 * WebApplicationInitializer allows configure Servlet configuration programmatically.
 * will be automatically identified by Spring Bootstrap process
 * Alternative XML Configuration can be defined in web.xml like below
 * <listener>
 *     <listener-class>
 *         org.springframework.web.context.ContextLeaderListener
 *     </listener-class>
 *     <servlet>
 *         <servlet-name>spring</servlet-name> <!-- looking for MVC configuration in spring-servlet.xml -->
 *         <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
 *         <load-on-startup>1</load-on-startup>
 *     </servlet>
 *     <servlet-mapping>
 *         <servlet-name>spring</servlet-name>
 *         <url-pattern>/</url-pattern>
 *     </servlet-mapping>
 * </listener>
 */
public class WebServletConfiguration implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext ctx) throws ServletException {
        AnnotationConfigWebApplicationContext webAppCtx = new AnnotationConfigWebApplicationContext();
         webAppCtx.register(SpringMvcConfiguration.class);
        // option 2: register ApplicationContextConfiguration
//        webAppCtx.register(ApplicationContextConfiguration.class);
        // option 3: use XML configuration for application context
//        XmlWebApplicationContext xmlWebCtx = new XmlWebApplicationContext();
//        xmlWebCtx.setConfigLocation("/WEB-INF/application-context.xml");
        webAppCtx.setServletContext(ctx);

        ServletRegistration.Dynamic servlet = ctx.addServlet("dispatcher", new DispatcherServlet(webAppCtx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/"); //<url-mapping /> of DispatcherServlet
    }
}
