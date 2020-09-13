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
