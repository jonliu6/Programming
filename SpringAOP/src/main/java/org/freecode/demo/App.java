package org.freecode.demo;

import java.util.List;

import org.freecode.demo.model.KnowledgeBaseDAO;
import org.freecode.demo.model.KnowledgePoint;
import org.springframework.context.ApplicationContext;
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
        
        List<KnowledgePoint> foundKPs = kb.findKnowledgeByKeyword("Java");
        
        for (KnowledgePoint aKP : foundKPs) {
        	System.out.println(aKP);
        }
    }
}
