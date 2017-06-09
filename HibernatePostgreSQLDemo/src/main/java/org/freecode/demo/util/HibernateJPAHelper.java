package org.freecode.demo.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateJPAHelper {
	
	// private final static EntityManagerFactory entityManagerFactory = createEntityManagerFactory();
	private static final EntityManager entityManager = createEntityManager();

//	public static EntityManagerFactory createEntityManagerFactory() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresql-demo");
//		return emf;
//	}
	
//	public static EntityManagerFactory getEntityManagerFactory() {
//		return entityManagerFactory;
//	}
	
	private static EntityManager createEntityManager()
	{
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresql-demo");
			return emf.createEntityManager();
		}
		catch (Throwable t) {
			throw new ExceptionInInitializerError(t);
		}
	}
	
	public static EntityManager getEntityManager() {
		return entityManager;
	}
}
