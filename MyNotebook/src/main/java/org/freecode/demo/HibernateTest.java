package org.freecode.demo;

import java.util.List;

import org.freecode.demo.entity.KnowledgeCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate accesses DB thru Session
 * requiring: hibernate.cfg.xml, Entity class  
 */
public class HibernateTest {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		SessionFactory sf = cfg.buildSessionFactory(sr);
		Session s = sf.openSession();
		
		List<KnowledgeCategory> categories = s.createQuery("FROM knowledgeCategory ORDER BY categoryParentId, id").list();
		if (categories != null) {
			for(KnowledgeCategory cg : categories) {
				System.out.println(cg.getCategoryId() + ": " + cg.getCategoryName());
			}
		}
	}

}
