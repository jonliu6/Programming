package org.freecode.demo;

import java.util.List;

import org.freecode.demo.model.TodoItem;
import org.freecode.demo.model.User;
import org.freecode.demo.util.HibernateHelper;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
		
		System.exit(0);
	}
	
	private static void queryTest(SessionFactory sf) {
    	// Session s = sf.getCurrentSession();
    	Session s = sf.openSession();
//		Transaction tx = null;
        try {
			
//        	tx = s.beginTransaction();
        	Filter filter = s.enableFilter("itemTitleFilter");
        	filter.setParameter("titleParam", "%9%");
        	
        	List<TodoItem> todoList = s.createQuery("FROM TodoItem").list(); // .setCacheable(true).
			if ( todoList != null && todoList.size() > 0 ) {
				System.out.println("Hibernate: ");
				for (TodoItem i : todoList) {
					if (i != null) {
						System.out.println(i.getTitle() + ": " + i.getDescription() + "\n##################################################");
					}
				}
			}
			
			s.disableFilter("itemTitleFilter");
//			tx.commit();
			
			// list user with todo items
			s.flush();
			s.clear();
			User u = (User) s.createQuery("FROM todoUser WHERE login = :loginId").setParameter("loginId", "jdoe").uniqueResult(); // use the entity names
			System.out.println(u);
			List<TodoItem> items = u.getTodoItems();
			if (items != null) {
				System.out.println("#####TODOs: ");
				for (int i = 0, len = items.size(); i < len; ++i) {
					System.out.println("\t" + items.get(i));
				}
			}
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
