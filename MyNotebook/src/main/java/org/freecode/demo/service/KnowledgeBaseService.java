package org.freecode.demo.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class KnowledgeBaseService {

	private static final EntityManager entityManager = createEntityManager();
	
	private static EntityManager createEntityManager() {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("KnowledgeBaseUnit");
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
