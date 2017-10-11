package org.freecode.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationApp {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("annotationContext.xml"); 
		
		Student std = (Student) context.getBean("student");
		
		System.out.println("Student: " + std);
		
		((ClassPathXmlApplicationContext) context).close();
	}

}
