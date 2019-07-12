package org.freecode.demo;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext appCtx) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(appCtx);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}
	
	@Bean(name="noteWS") // bean name is the wsdl name
	/**
	 * WSDL URL: http://localhost:8080/ws/noteWS.wsdl
	 * @param noteSchema
	 * @return
	 */
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema noteSchema) {
		DefaultWsdl11Definition wsdlDef = new DefaultWsdl11Definition();
		wsdlDef.setPortTypeName("NotePort");
		wsdlDef.setLocationUri("/ws"); // this is SOAP address in the WSDL and must be consistent with the server WSDL location 
		wsdlDef.setTargetNamespace("http://demo.freecode.org/schemas");
		wsdlDef.setSchema(noteSchema);
		
		return wsdlDef;
	}
	
	@Bean
	public XsdSchema noteSchema() {
		return new SimpleXsdSchema(new ClassPathResource("Note.xsd"));
	}
}
