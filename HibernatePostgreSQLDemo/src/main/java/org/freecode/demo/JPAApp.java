package org.freecode.demo;

import java.util.List;

import javax.persistence.EntityManager;

import org.freecode.demo.model.TodoItem;
import org.freecode.demo.util.HibernateJPAHelper;

public class JPAApp {
	public static void main(String[] args) {
		EntityManager em = null;
		try {
			em = HibernateJPAHelper.getEntityManagerFactory().createEntityManager();
			if (em != null) {
				System.out.println("Hibernate JPA: ");
				// em.getTransaction().begin();
				List<TodoItem> todoList = em.createQuery("SELECT ti FROM TodoItem ti").getResultList();
				for (TodoItem ti : todoList) {
					if (ti != null) {
						System.out.println(ti.getTitle() + ": " + ti.getDescription() + "\n##################################################");
					}
				}
				// em.getTransaction().commit();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			if (em != null) {
				em.getTransaction().rollback();
			}
		}
	}
}
