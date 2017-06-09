package org.freecode.demo;

import java.awt.HeadlessException;
import java.util.List;

import org.freecode.demo.model.TodoItem;
import org.freecode.demo.util.HibernateHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * This is a demo using Hibernate Session Factory
 * 
 */
public class App {
	

	public static void main(String[] args) {
		
		SessionFactory sf = HibernateHelper.getSessionFactory();
		if (sf != null) {
			queryTest(sf);
		}
	}
	
	private static void queryTest(SessionFactory sf) {
    	// Session s = sf.getCurrentSession();
    	Session s = sf.openSession();
//		Transaction tx = null;
        try {
			
//        	tx = s.beginTransaction();
        	List<TodoItem> todoList = s.createQuery("FROM TodoItem").setCacheable(true).list();
			if ( todoList != null && todoList.size() > 0 ) {
				System.out.println("Hibernate: ");
				for (TodoItem i : todoList) {
					if (i != null) {
						System.out.println(i.getTitle() + ": " + i.getDescription() + "\n##################################################");
					}
				}
			}
//			tx.commit();
        }
        catch (HibernateException he) {
//        	tx.rollback();
        	he.printStackTrace();
        }
        finally {
			s.close();
			System.out.println("Entity fetch count from DB: " + HibernateHelper.getSessionFactory().getStatistics().getEntityFetchCount());
			System.out.println("2-level cache hit count from DB: " + HibernateHelper.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());
		}
	}
	
	
}
