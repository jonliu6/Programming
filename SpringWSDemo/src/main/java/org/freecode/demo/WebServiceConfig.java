package org.freecode.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Configuration of Spring Web Service
 * Somehow, Spring Web MVC still looks for <servlet name>-servlet.xml
 *
 */
//@EnableWs
//@Configuration
public class WebServiceConfig 
{
//	@Bean(name="notebookService")
//	public DefaultWsdl11Definition noteWsdl(XsdSchema notebookSchema) {
//		DefaultWsdl11Definition noteDef = new DefaultWsdl11Definition();
//		noteDef.setPortTypeName("notebookPort");
//		noteDef.setLocationUri("/ws");
//		noteDef.setTargetNamespace("http://demo.freecode.org/schemas");
//		noteDef.setSchema(notebookSchema);
//		return noteDef;
//	}
//	
//	@Bean
//	public XsdSchema notebookSchema() {
//		return new SimpleXsdSchema(new ClassPathResource("Notebook.xsd"));
//	}
}
