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
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml"); // in the root of the classes folders
		Product prod = (Product) context.getBean("product");
//		System.out.println( prod.getDefaultLabel() );
		
//		Part part = (Part) context.getBean("part");
		
		System.out.println( prod );
//		System.out.println( part );
		
		ProductPart aPart = (ProductPart) context.getBean("partWithVendorList");
		System.out.println( "Part vendors: " + aPart.getVendors() );
		
		ProductPart anotherPart = (ProductPart) context.getBean("partWithPName");
		System.out.println( "Part by pNames: " + anotherPart);
		
		ProductPart thirdPart = (ProductPart) context.getBean("partFromProperties");
		System.out.println( "Part created from properties: " + thirdPart);
		
		Product switchProduct = (Product) context.getBean("productSwitch");
		System.out.println( "Switch product: " + switchProduct);
		
		((ClassPathXmlApplicationContext) context).close();
	}

}
