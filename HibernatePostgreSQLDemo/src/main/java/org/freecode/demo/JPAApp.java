package org.freecode.demo;

import java.util.List;

import javax.persistence.EntityManager;

import org.freecode.demo.model.TodoItem;
import org.freecode.demo.model.User;
import org.freecode.demo.util.HibernateJPAHelper;

public class JPAApp {
	public static void main(String[] args) {
		queryTest();
		insertTest();
		queryTest();
		System.exit(0);
	}
	
	private static void queryTest() {
		EntityManager em = null;
		try {
            // em = HibernateJPAHelper.getEntityManagerFactory().createEntityManager();
			em = HibernateJPAHelper.getEntityManager();
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
//			if (em != null) {
//				em.getTransaction().rollback();
//			}
		}
		finally {
			// em.close();
			em = null;
		}
	}
	
	private static void insertTest() {
        // em = HibernateJPAHelper.getEntityManagerFactory().createEntityManager();
		EntityManager em = HibernateJPAHelper.getEntityManager();
		
		if (em != null) {
			try {
                em.getTransaction().begin();
                
                // insert one new user with todo items
                User u = new User("jdoe","Jane","Doe");
                TodoItem itm = new TodoItem("jdItem1","1st Item of Jane");
                itm.setItemOwner(u);
                u.getTodoItems().add(itm);
                itm = new TodoItem("jdItem2","2nd Item of Jane");
                itm.setItemOwner(u);
                u.getTodoItems().add(itm);
                em.merge(u);
                
    			for (int i = 0; i < 100; i++) {
    				itm = new TodoItem();
    				// itm.setId(Long.valueOf(i)); // no need since DB sequence handles
    				itm.setTitle("Title " + i);
    				itm.setDescription("Description " + i);
    				
    				// em.persist(itm);
    				em.merge(itm);
    				
    				if (i % 30 == 0 && i > 0) { // batch insertion
    					em.flush();
    					em.clear();
    					System.out.println("EntityManager flushed and cleared after #" + i + " inserts!");
    				}
    			}
                
				em.getTransaction().commit();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				em.getTransaction().rollback();
			}
			finally {
				// em.close();
				em = null;
			}
		}
	}
}
