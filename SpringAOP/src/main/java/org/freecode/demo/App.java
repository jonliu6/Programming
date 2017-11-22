package org.freecode.demo;

import java.util.List;

import org.freecode.demo.aop.AppConfig;
import org.freecode.demo.model.KnowledgeBaseDAO;
import org.freecode.demo.model.KnowledgePoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Driver of Spring AOP samples
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    	// ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        
        // KnowledgeBaseDAO kb = new KnowledgeBaseDAO();
        // For AOP, you need to instantiate the objects from application context to trigger aop-config
        KnowledgeBaseDAO kb = (KnowledgeBaseDAO) ctx.getBean("knowledgeBase");
        kb.initializeKnowledgeBase();
        
        KnowledgePoint kp = new KnowledgePoint("Java printout", "System.out.println(\"Hello, world!\");", "Java");
        kb.addKnowledgePoint(kp);
        kp = new KnowledgePoint("C printout", "printf(\"Hello, world!\")", "C");
        kb.addKnowledgePoint(kp);
        kp = new KnowledgePoint("Python printout", "print(\"Hello, world!\")", "Python");
        kb.addKnowledgePoint(kp);
        kp = new KnowledgePoint("JavaScript printout", "console.log(\"Hello, world!\")", "JavaScript");
        kb.addKnowledgePoint(kp);
        
        try {
            List<KnowledgePoint> foundKPs = kb.findKnowledgeByKeyword("Java");
            for (KnowledgePoint aKP : foundKPs) {
            	System.out.println(aKP);
            }
        }
        catch(Exception ex) {
        	System.out.println("Exception caught when trying to find knowledge by keyword.");
        	System.out.println(ex.getMessage());
        }
        
        try {
        	List<KnowledgePoint> foundKPs = kb.findAll();
        	System.out.println(foundKPs.size() + " in total.");
        }
        catch(Exception ex) {
        	System.out.println("Exception caught when trying to find all.");
        	System.out.println(ex.getMessage());
        }
        
        ((ClassPathXmlApplicationContext)ctx).close();
        // ((AnnotationConfigApplicationContext)ctx).close();
    }
}
