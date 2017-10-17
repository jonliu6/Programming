package org.freecode.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoWireApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("autowire_beans.xml"); // in the root of the classes folders
		Product prod = (Product) context.getBean("productWiredByType");
		System.out.println("Product wired by Type: " + prod);
		
		prod = (Product) context.getBean("productWiredByNamme");
		System.out.println("Product wired by Name: " + prod);
		
		prod = (Product) context.getBean("productWiredByConstructor");
		System.out.println("Product wired by Constructor: " + prod);
		
		prod = (Product) context.getBean("productWiredByDefault");
		System.out.println("Product wired by Constructor: " + prod);
		
		((ClassPathXmlApplicationContext) context).close();
	}
}
