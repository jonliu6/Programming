package org.freecode.demo.aop;

import org.freecode.demo.model.KnowledgeBaseDAO;
import org.freecode.demo.model.KnowledgePoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration 
@ComponentScan("org.freecode.demo.aop") // equivalent to <context:component-scan base-package="org.freecode.demo.aop"></context:component-scan>
@EnableAspectJAutoProxy // equivalent to <aop:aspectj-autoproxy />
/**
 * This is Java Configuration class, can be replaced by applicationContext.xml
 * 
 */
public class AppConfig {

//	@Bean(name="knowledgePoint") // equivalent to <bean /> in applicationContext.xml
//	public KnowledgePoint getKnowledgePoint() {
//		return new KnowledgePoint();
//	}
//	
//	@Bean(name="knowledgeBase")
//	public KnowledgeBaseDAO getKnowledgeBaseDAO() {
//		return new KnowledgeBaseDAO();
//	}
}
