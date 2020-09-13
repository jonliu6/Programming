package org.freecode.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Define configuration for Spring MVC, replacing ApplicationContextConfiguration
 */
@EnableWebMvc
@ComponentScan(basePackages = "org.freecode.demo")
// classes defined in this package will be automatically instantiated and injected - including @Controller classes.
@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {
    /**
     * InternalResourceViewResolver maps the logic names to physical file names based on prefix and suffix. e.g. home to /home.jsp
     * @return
     * Note: must have @Bean for Spring Inversion of Control
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/message/"); // prefix of Spring Views, in web root folder
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
}
