package org.freecode.demo.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateJPAHelper {
	
	private final static EntityManagerFactory entityManagerFactory = createEntityManagerFactory();

	public static EntityManagerFactory createEntityManagerFactory() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresql-demo");
		return emf;
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
}
