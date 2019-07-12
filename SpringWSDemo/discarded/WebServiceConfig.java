package org.freecode.demo.webservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//@EnableWs
//@Configuration
//@ComponentScan(basePackages="org.freecode.demo")
public class WebServiceConfig extends WsConfigurerAdapter {

//	@Bean(name="notebookService")
//	/**
//	 * This definition is equivalent to the below XML block in <servlet-name>-servlet.xml
//	 * <sws:dynamic-wsdl id="note" portTypeName="Note" locationUri="http://localhost:8080/ws/noteService">
//	 *   <sws:xsd location="Note.xsd" />
//	 * </sws:dynamic-wsdl>
//	 * @return
//	 */
//	public DefaultWsdl11Definition noteWsdl(XsdSchema notebookSchema) {
//		DefaultWsdl11Definition noteDef = new DefaultWsdl11Definition();
//		noteDef.setPortTypeName("NotePort");
//		noteDef.setLocationUri("/noteService");
//		noteDef.setTargetNamespace("http://demo.freecode.org/schemas");
//		noteDef.setSchema(notebookSchema);
//		return noteDef;
//	}
//	
//	@Bean
//	public XsdSchema notebookSchema() {
//		return new SimpleXsdSchema(new ClassPathResource("/Notebook.xsd"));
//	}
}
