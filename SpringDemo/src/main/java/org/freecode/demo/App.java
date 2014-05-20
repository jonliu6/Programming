package org.freecode.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
// import org.springframework.context.support.FileSystemXmlApplicationContext;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// create spring bean container - application context
		// load beans configuration file from root folder in the file system
//		ApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");
		// load beans configuration file from classpath
		ApplicationContext context = new ClassPathXmlApplicationContext("org/freecode/demo/beans/beans.xml");
		Product prod = (Product) context.getBean("product");
//		System.out.println( prod.getDefaultLabel() );
		
//		Part part = (Part) context.getBean("part");
		
		System.out.println( prod );
//		System.out.println( part );
		
		((ClassPathXmlApplicationContext) context).close();
	}

}
