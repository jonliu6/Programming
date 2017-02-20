package org.freecode.demo;

import java.util.List;

import org.freecode.demo.model.TodoItem;
import org.freecode.demo.util.HibernateHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class App {

	public static void main(String[] args) {
		
		SessionFactory sf = HibernateHelper.getSessionFactory();
		if (sf != null) {
			Session s = sf.getCurrentSession();
			Transaction tx = s.beginTransaction();
			List<TodoItem> todoList = s.createQuery("FROM TodoItem").list();
			if ( todoList != null && todoList.size() > 0 ) {
				for (TodoItem i : todoList) {
					if (i != null) {
						System.out.println(i.getTitle() + ": " + i.getDescription() + "\n##################################################");
					}
				}
			}
			s.close();
		}
	}
	
}
